package gtes.controller;


import gtes.entity.Firm;
import gtes.service.FirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/firm")

public class FirmController {
    @Autowired
    private FirmService firmService;
    @Qualifier (value = "firmService")
    public void setFirmService(FirmService firmService){
        this.firmService=firmService;
    }
//TODO переделать все на шаблон index
    @GetMapping("/firms")
    public String listFirm (Model model){
        model.addAttribute("listFirm",this.firmService.findAll());
        model.addAttribute("template","firm");
//        return "firm";
        return "index";
    }
    @GetMapping("/addFormFirm")
    public String formAdd(Model model){
        Firm firm = new Firm();
        model.addAttribute("firm",firm);
        return "fragments/firm/newFirm";
    }
    @PostMapping("/saveFirm")
    public String saveFirm(@Valid @ModelAttribute("firm") Firm theFirm, BindingResult result){
        if (result.hasErrors()){
            return "fragments/firm/newFirm";
        }else{
            firmService.save(theFirm);
            return  "redirect:/firm/firms";
        }
    }
    @GetMapping("/delete")
    public String removeFirm(@RequestParam("idFirm") int idFirm){
        this.firmService.deleteById(idFirm);
        return "redirect:/firm/firms";
    }
    @RequestMapping("/updateFormFirm")
    public String editFirm(@RequestParam("idFirm") int idFirm, Model model){
        Firm firm = firmService.findOne(idFirm);
        model.addAttribute("firm",firm);
        return "fragments/firm/editFirm";
    }

}
