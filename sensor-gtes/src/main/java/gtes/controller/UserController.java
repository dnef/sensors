package gtes.controller;

import gtes.entity.User;
import gtes.entity.UserProfile;
import gtes.service.UserProfileService;
import gtes.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    static final Logger logger = LogManager.getLogger(UserController.class.getName());
    @Autowired
    private UserService userService;
    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;
    @Autowired
    MessageSource messageSource;

    @Qualifier("userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Qualifier("userProfileService")
    public void setUserProfileService(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }


    private String getPrincipalName() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }


    @GetMapping("/users")
    public String listUser(Model model) {
        model.addAttribute("listUser", this.userService.findAllUsers());
//        model.addAttribute("loggedinuser",getPrincipal());
        model.addAttribute("template", "user");
        return "fragments/user/user";
    }

    @GetMapping("/addFormUser")
    public String formAdd(Model model) {
        User user = new User();
        model.addAttribute("user", user);
//        model.addAttribute("edit", false);
//        model.addAttribute("loggedinuser", getPrincipal());
        return "fragments/user/newUser";
    }

    @RequestMapping("/updateFormUser")
//    @PreAuthorize("#ssoId==authentication.name or hasRole('ADMIN')")
    public String editUser(@RequestParam("ssoId") String ssoId, Model model) {
//        if (getPrincipalName().equals(ssoId) || httpServletRequest.isUserInRole("ADMIN")){
        User user = this.userService.findBySSO(ssoId);
        model.addAttribute("user", user);
//        model.addAttribute("edit", true);
//        model.addAttribute("loggedinuser", getPrincipal());
        return "fragments/user/editUser";
//        }else{
//        return "redirect:403";
//        }
    }
    @Secured({"ROLE_ADMIN"})
    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User theUser, BindingResult result) {
        if (result.hasErrors()) {
            return "fragments/user/newUser";
        } else {
            userService.saveUser(theUser);
            return "redirect:/user/users";
        }
    }

    @PostMapping("/saveEditUser")
    public String saveEditUser(@Valid @ModelAttribute("user") User theUser, BindingResult result) {
        if (result.hasErrors()) {
            return "fragments/user/editUser";
        }
        else {
            userService.updateUser(theUser);
//            if (httpServletRequest.isUserInRole("ADMIN")) {
                return "redirect:/user/users";
//            } else {
//                return "redirect:/index";
//            }
        }
    }


    @GetMapping("/delete")
    public String deleteUnit(@RequestParam("ssoId") String ssoId) {
        userService.deleteUserBySSO(ssoId);
        return "redirect:/user/users";
    }

    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }


    //TODO переделать все на updateFormUser, SecurityConfig изменить (сейчас ROLE_USER не имеют доступ к updateFormUser)
    @RequestMapping("/updateFormPerson")
    @PreAuthorize("#ssoId==authentication.name or hasRole('ADMIN')")
    public String editPerson(@RequestParam("ssoId") String ssoId, Model model) {
        User user = this.userService.findBySSO(ssoId);
        model.addAttribute("user", user);
        return "fragments/user/editPerson";
    }
    @PostMapping("/saveEditPerson")
    public String saveEditPerson(@Validated({User.UpdateUser.class})@ModelAttribute("user") User theUser, BindingResult result) {
        if (result.hasErrors()) {
            return "fragments/user/editPerson";
        } else {
            User userEdit=userService.findById(theUser.getId());
            theUser.setSsoId(userEdit.getSsoId());
            theUser.setUserProfiles(userEdit.getUserProfiles());
            userService.updateUser(theUser);
//            if (httpServletRequest.isUserInRole("ADMIN")) {
//                return "redirect:/user/users";
//            } else {
                return "redirect:/index";
//            }
        }

    }
}