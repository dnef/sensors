package gtes.controller;


import gtes.entity.Typesens;
import gtes.service.TypesensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/typesens")
public class TypesensController {
    @Autowired
    private TypesensService typesensService;
    @Qualifier (value = "typesensService")
    public void setTypesensService(TypesensService typesensService){
        this.typesensService=typesensService;
    }

    @GetMapping("/typesens")
    public String listTypesens (Model model){
        model.addAttribute("listTypesens",this.typesensService.findAll());
        model.addAttribute("template","typesens");
        return "index";
    }
    @GetMapping("/addFormTypesens")
    public String formAdd(Model model){
        Typesens typesens = new Typesens();
        model.addAttribute("typesens",typesens);
        return "fragments/typesens/newTypesens";
    }
    @RequestMapping("/updateFormTypesens")
    public String editFirm(@RequestParam("idTypesens") int idTypesens, Model model){
        Typesens typesens = typesensService.findOne(idTypesens);
        model.addAttribute("typesens",typesens);
        return "fragments/typesens/editTypesens";
    }
    @PostMapping("/saveTypesens")
    public String saveTypesens(@Valid @ModelAttribute("typesens") Typesens theTypesens, BindingResult result){
        if (result.hasErrors()){
            return "fragments/typesens/newTypesens";
        }else{
            typesensService.save(theTypesens);
            return  "redirect:/typesens/typesens";
        }
    }
    @GetMapping("/delete")
    public String removeTypesens(@RequestParam("idTypesens") int idTypesens){
        this.typesensService.deleteById(idTypesens);
        return "redirect:/typesens/typesens";
    }


}
