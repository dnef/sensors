package gtes.controller;

import gtes.entity.User;
import gtes.entity.UserProfile;
import gtes.service.UserProfileService;
import gtes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
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

    @Qualifier("PersistentTokenBasedRememberMeServices")
    public void setPersistentTokenBasedRememberMeServices(PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices) {
        this.persistentTokenBasedRememberMeServices = persistentTokenBasedRememberMeServices;
    }

    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            userName=((UserDetails)principal).getUsername();
        }else{
            userName=principal.toString();
        }
        return userName;
    }


    @GetMapping("/users")
    public String listUser(Model model) {
        model.addAttribute("listUser", this.userService.findAllUsers());
//        model.addAttribute("loggedinuser",getPrincipal());
        model.addAttribute("template", "user");
        return "index";
    }

    @GetMapping("/addFormUser")
    public String formAdd(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
//        model.addAttribute("loggedinuser", getPrincipal());
        return "fragments/user/newUser";
    }

    @RequestMapping("/updateFormUser")
    public String editUser(@RequestParam ("ssoId") String ssoId, Model model) {
        User user = this.userService.findBySSO(ssoId);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
//        model.addAttribute("loggedinuser", getPrincipal());
        return "fragments/user/editUser";

    }


    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User theUser,  BindingResult result) {
        if (result.hasErrors()) {
            return "fragments/user/newUser";
        } else {
//            if(!userService.isUserSSOUnique(theUser.getId(), theUser.getSsoId())){
//                FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{theUser.getSsoId()}, Locale.getDefault()));
//                result.addError(ssoError);
//                return "fragments/user/newUser";
//            }
            userService.saveUser(theUser);
            return "redirect:/user/users";
        }
    }
    @PostMapping("/editUser")
    public String editUser(@Valid @ModelAttribute("user") User theUser, BindingResult result) {
        if (result.hasErrors()) {
            return "fragments/user/editUser";
        } else {
//            if(!userService.isUserSSOUnique(theUser.getId(), theUser.getSsoId())){
//                FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{theUser.getSsoId()}, Locale.getDefault()));
//                result.addError(ssoError);
//                return "fragments/user/newUser";
//            }
            userService.updateUser(theUser);
            return "redirect:/user/users";
        }
    }

    @GetMapping("/delete")
    public String deleteUnit(@RequestParam("ssoId") String ssoId) {
        userService.deleteUserBySSO(ssoId);
        return "redirect:/user/users";
    }

    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles(){
        return userProfileService.findAll();
    }
}
