package gtes.controller;

import gtes.entity.ModelSensor;
import gtes.entity.Typesens;
import gtes.service.ModelSensorService;
import gtes.service.TypesensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/model")
public class ModelSensorController {
//    private static final Logger log = LogManager.getLogger(ModelSensorController.class.getName());

    @Autowired
    private ModelSensorService modelSensorService;
    @Autowired
    private TypesensService typesensService;
    @Qualifier(value = "modelSensorService")
    public void setModelSensorService(ModelSensorService modelSensorService){
        this.modelSensorService=modelSensorService;
    }
    @Qualifier(value = "typesensService")
    public void setTypesensService( TypesensService typesensService){
        this.typesensService=typesensService;
    }

    public List<Typesens> getTypeList(){
        List<Typesens> typesensList = this.typesensService.findAll();
        return typesensList;
    }

    @GetMapping("/modelsensor")
    public String listModelSensor (Model model){
        model.addAttribute("listModelSensor",this.modelSensorService.findAll());
        model.addAttribute("template","model");
        return "index";
    }
    @GetMapping("/addFormModel")
    public String formAdd (Model model){
        ModelSensor modelSensor = new ModelSensor();
        model.addAttribute("modelsensor",modelSensor);
        model.addAttribute( "typesensList",getTypeList());
        return "fragments/model/newModel";
    }
    @PostMapping("/saveModelSensor")

    public String saveModel(@Valid @ModelAttribute("modelsensor") ModelSensor theModel, BindingResult result, SessionStatus status,Model model){
        if (result.hasErrors()){
            model.addAttribute( "typesensList",getTypeList());
            return "fragments/model/newModel";
        }else {
            modelSensorService.save(theModel);
            return "redirect:/model/modelsensor";
        }
    }

    @GetMapping("/delete")
    public String removeModel(@RequestParam("idModel") int idModel){
        this.modelSensorService.deleteById(idModel);
        return "redirect://model/modelsensor";
    }


    @RequestMapping("/updateFormModel")
    public String editModel(@RequestParam("idModel") int idModel, Model model){
        ModelSensor modelSensor = this.modelSensorService.findOne(idModel);
        model.addAttribute("modelsensor",modelSensor);
        model.addAttribute( "typesensList",getTypeList());
        return "fragments/model/editModel";
    }
}
