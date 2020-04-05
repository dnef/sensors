package gtes.controller;


import gtes.entity.Country;
import gtes.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@RequestMapping("/country")
public class CountryController {
//    private static final Logger log = LogManager.getLogger(CountryController.class.getName());
    @Autowired(required=true)
    private CountryService countryService;

    @Qualifier(value = "countryService")
    public void setCountryService (CountryService cs){
        this.countryService = cs;
    }

    @GetMapping("/countrys")
    public String listCountry(Model model){
//        model.addAttribute("country", new Country());
        model.addAttribute("listCountry",this.countryService.findAll());
        model.addAttribute("template","country");
        return "index";
    }

    @GetMapping("/addFormCountry")
    public String formAdd(Model model){
        Country country = new Country();
        model.addAttribute("country", country);
        return "fragments/country/newCountry";
    }

    @PostMapping("/saveCountry")

    public String saveCountry(@Valid @ModelAttribute("country") Country theCountry, BindingResult result, SessionStatus status){
        if (result.hasErrors()){
            return "fragments/country/newCountry";
        }else {
            countryService.save(theCountry);
            return "redirect:/country/countrys";
        }
    }

    @GetMapping("/delete")
    public String removeCountry(@RequestParam("idCountry") int idCountry){
        this.countryService.deleteById(idCountry);
        return "redirect:/country/countrys";
    }
    @RequestMapping("/updateFormCountry")
    public String editCountry(@RequestParam("idCountry") int idCountry, Model model){
       Country country = countryService.findOne(idCountry);
       model.addAttribute("country",country);
        return "fragments/country/editCountry";
    }

}
