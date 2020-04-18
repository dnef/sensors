package gtes.controller;

import gtes.entity.Location;
import gtes.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/location")
public class LocationController {
    @Autowired(required = true)
    private LocationService locationService;

    @Qualifier(value = "locationService")
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/locations")
    public String listLocation(Model model){
        model.addAttribute("listLocation", this.locationService.findAll());
        model.addAttribute("template","location");
        return "fragments/location/location";

    }
    @GetMapping("/addFormLoc")
    public String addForm(Model model){
        Location location = new Location();
        model.addAttribute("location",location);
        return "fragments/location/newLocation";
    }
    @PostMapping("/saveLocation")
    public String saveLoc(@Valid @ModelAttribute("location") Location theLocation, BindingResult result){
        if(result.hasErrors()){
            return "fragments/location/newLocation";
        }else{
            this.locationService.save(theLocation);
            return "redirect:/location/locations";
        }
    }
    @GetMapping("/delete")
    public String removeLoc(@RequestParam("idLocation") int idLocation){
        this.locationService.deleteById(idLocation);
        return "redirect:/location/locations";
    }
    @RequestMapping("/updateLocation")
    public String editLoc(@RequestParam("idLocation") int idLocation, Model model){
        Location location = this.locationService.findOne(idLocation);
        model.addAttribute("location", location);
        return "fragments/location/editLocation";
    }

}
