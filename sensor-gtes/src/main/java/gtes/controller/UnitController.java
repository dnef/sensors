package gtes.controller;

import gtes.entity.Unit;
import gtes.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/unit")
public class UnitController {

    @Autowired
    private UnitService unitService;
    @Qualifier (value = "unitService")
    public void setUnitsService(UnitService unitsService){this.unitService=unitService;}

    @GetMapping ("/units")
    public String listUnits (Model model){
        model.addAttribute("listUnits",this.unitService.findAll());
        model.addAttribute("template", "unit");
        return "index";
    }

    @GetMapping ("/addFormUnit")
    public String formAdd (Model model){
        Unit unit = new Unit();
        model.addAttribute("unit",unit);
        return "fragments/unit/newUnit";
    }

    @RequestMapping ("/updateFormUnit")
    public String editUnit(@RequestParam("idUnit") int idUnit, Model model){
        Unit unit = this.unitService.findOne(idUnit);
        model.addAttribute("unit",unit);
        return "fragments/unit/editUnit";
    }

    @PostMapping ("/saveUnit")
    public String saveUnit (@Valid @ModelAttribute("unit") Unit theUnit, BindingResult result){
        if (result.hasErrors()){
            return "fragments/unit/newUnit" ;
        }else{
            this.unitService.save(theUnit);
            return "redirect:/unit/units";
        }
    }

    @GetMapping("/delete")
    public String deleteUnit(@RequestParam("idUnit") int idUnit){
        this.unitService.deleteById(idUnit);
        return "redirect:/unit/units";
    }
}
