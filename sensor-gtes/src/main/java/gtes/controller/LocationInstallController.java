package gtes.controller;

import com.google.common.collect.HashMultimap;
import gtes.entity.Archive;
import gtes.entity.Location;
import gtes.entity.LocationInstall;
import gtes.entity.Sensor;
import gtes.report.PdfInstallLocationView;
import gtes.service.ArchiveService;
import gtes.service.LocationInstallService;
import gtes.service.LocationService;
import gtes.service.SensorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/install")
public class LocationInstallController {
    static final Logger logger = LogManager.getLogger(LocationInstallController.class.getName());
    @Autowired
    private LocationInstallService locationInstallService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private SensorService sensorService;
    @Autowired
    private ArchiveService archiveService;

//    @Autowired
//    private ModelSensorService modelSensorService;
//    @Autowired
//    private TypesensService typesensService;


    @Qualifier(value = "locationInstallService")
    public void setLocationInstallService(LocationInstallService locationInstallService) {
        this.locationInstallService = locationInstallService;
    }

    @Qualifier(value = "locationService")
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @Qualifier(value = "sensorService")
    public void setSensorService(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Qualifier(value = "archiveService")
    public void setArchiveService(ArchiveService archiveService) {
        this.archiveService = archiveService;
    }

//    @Qualifier(value = "modelSensorService")
//    public void setModelSensorService(ModelSensorService modelSensorService) {
//        this.modelSensorService = modelSensorService;
//    }
//    @Qualifier(value = "typesensService")
//    public void setTypesensService( TypesensService typesensService){
//        this.typesensService=typesensService;
//    }

    public List<LocationInstall> getInstall() {
        List<LocationInstall> locationInstallList;
        locationInstallList = this.locationInstallService.findAll();
        return locationInstallList;
    }

    public LocationInstall getInstallById(int id) {
        LocationInstall locationInstallListByID = this.locationInstallService.findOne(id);
        return locationInstallListByID;
    }

    public List<Location> getLocations() {
        List<Location> locationList = this.locationService.findAll();
        return locationList;
    }

    public List<Sensor> getSensors() {
        List<Sensor> sensorList = this.sensorService.findAll();
        return sensorList;
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }


    //для сохранения в архив
    public Archive addArchive(int locationId, int sensorId, String note) {

        Archive archive = new Archive();
        archive.setLocationId(locationId);
        archive.setSensorId(sensorId);
        archive.setInstallDate(LocalDateTime.now());
        archive.setNote(note);
        return archive;
    }

    @GetMapping("/locationsSelect")
    public String listLocation(Model model) {
        model.addAttribute("listLocation", getLocations());
        return "fragments/install/selectLocation";
    }

    @GetMapping("/installs")
    public Object listInstall(@RequestParam(value = "idLocation") int idLocation,
                              @RequestParam(value = "report", required = false) boolean report,
                              Model model) {
//        model.addAttribute("listInstall",getInstall());
        HashMultimap<String, Object> filterHashMap = HashMultimap.create();
        filterHashMap.put("locationId", idLocation);
        List<Sensor> listInstall= this.sensorService.findSensorWhere(filterHashMap);
        model.addAttribute("listInstall", listInstall);
        model.addAttribute("localDateNow", LocalDate.now());
        model.addAttribute("idLocation", idLocation);
        model.addAttribute("nameLocation",locationService.findOne(idLocation).getNameLoc());
        model.addAttribute("template", "install");
        if (report) {
            return new ModelAndView(new PdfInstallLocationView(), "installList", listInstall);
        } else {
            return "fragments/install/install";
        }
    }

    @Secured({"ROLE_ADMIN", "ROLE_KIP"})
    @GetMapping("addFormInstall")
    public String addForm(@RequestParam(value = "idLocation") int idLocation, Model model) {
        model.addAttribute("locationId",idLocation);
//        model.addAttribute("locations", getLocations());
        model.addAttribute("location",locationService.findOne(idLocation));
        model.addAttribute("sensors", getSensors());
        return "fragments/install/newInstall";
    }

    @Secured({"ROLE_ADMIN", "ROLE_KIP"})
    @PostMapping("saveInstall")
    public String saveInstall(@RequestParam(value = "idSensor") Integer idSensor, @RequestParam(value = "locationId") Integer locationIdNew,
                              @RequestParam(value = "idSensorOld", required = false) Integer idSensorOld, @RequestParam(value = "locationIdOld", required = false) Integer locationIdOld,
                              Model model) {
        Sensor sensor = sensorService.findOne(idSensor);
        if (idSensor == null || locationIdNew == null) {
            model.addAttribute("sensor", sensor);
            model.addAttribute("locations", getLocations());
            model.addAttribute("sensors", getSensors());
            return "fragments/install/newInstall";
        } else {
//
//            Существует ли сенсор с таким Id на другой установке ?
//            Переносим его на склад и со склада на новую установку
            //
            //на складе датчик или нет id склада -1 склад удалить нельзя
            if (sensor.getLocationId() != 1) {
                archiveService.save(addArchive(1, sensor.getIdSensor(), "перемещен с установки " + sensor.getLocationByLocationId().getNameLoc() +
                        " на склад. Пользователь: " + getPrincipal()));
            }

//                locationInstallService.deleteById(idInstIf);
            //при редактировании установленного датчика на той же установке
            //пишем в архив -> датчик на склад(снят с установки)
            if (idSensor != idSensorOld & locationIdNew == locationIdOld) {
                archiveService.save(addArchive(1, idSensorOld, "перемещен с установки " + sensor.getLocationByLocationId().getNameLoc() +
                        " на склад: " + getPrincipal()));
                Sensor sensorOld = sensorService.findOne(idSensorOld);
                sensorOld.setLocationId(1);
                sensorService.save(sensorOld);
            }
            archiveService.save(addArchive(locationIdNew, sensor.getIdSensor(), "установлен: " + getPrincipal()));//пишем в архив датчик установлен

            sensor.setLocationId(locationIdNew);
            sensorService.save(sensor);
            return "redirect:/install/installs?idLocation=" + sensor.getLocationId();
        }

    }


    @Secured({"ROLE_ADMIN", "ROLE_KIP"})
    @GetMapping("/delete")
    public String removeInstall(@RequestParam("idSensor") int idSensorDismantled) {
        Sensor sensorMoving = sensorService.findOne(idSensorDismantled);
        int changeLocation = sensorMoving.getLocationId();
        if (sensorMoving.getLocationId() == 1) {
            archiveService.save(addArchive(1, sensorMoving.getIdSensor(), "Утилизирован(перемещен со склада): " + getPrincipal()));
            sensorMoving.setLocationId(null);
            sensorService.save(sensorMoving);
            //TODO утилизация - удаление со склада, архив!!! вороос при удалении.
        } else {
            //датчик можно удалить только со склада, если его удаляют с установки то он идет на склад
            archiveService.save(addArchive(1, sensorMoving.getIdSensor(), "Перемещен с "
                    + sensorMoving.getLocationByLocationId().getNameLoc() + " на склад: " + getPrincipal()));
            sensorMoving.setLocationId(1);

            sensorService.save(sensorMoving);
        }

        return "redirect:/install/installs?idLocation=" + changeLocation;
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/updateFormLocationInstall")
    public String editModel(@RequestParam("idSensor") int idSensorMoving, Model model) {
        Sensor sensor = sensorService.findOne(idSensorMoving);
        model.addAttribute("sensor",sensor);
        model.addAttribute("idSensor", sensor.getIdSensor());
        model.addAttribute("locationId", sensor.getLocationId());
        model.addAttribute("locations", getLocations());
        model.addAttribute("sensors", getSensors());
        return "fragments/install/editInstall";
    }
}
