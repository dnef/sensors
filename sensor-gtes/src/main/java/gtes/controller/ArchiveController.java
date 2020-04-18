package gtes.controller;


import gtes.entity.Archive;
import gtes.entity.Location;
import gtes.report.PdfArchiveView;
import gtes.service.ArchiveService;
import gtes.service.LocationService;
import gtes.service.SensorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/archive")
public class ArchiveController {
    static final Logger logger = LogManager.getLogger(ArchiveController.class.getName());
    @Autowired
    private ArchiveService archiveService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private SensorService sensorService;

    @Qualifier(value = "archiveService")
    public void setArchiveService(ArchiveService archiveService) {
        this.archiveService = archiveService;
    }

    @Qualifier(value = "locationService")
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @Qualifier(value = "sensorService")
    public void setSensorService(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    public List<Location> getLocationList() {
        List<Location> locationList = this.locationService.findAll();
        return locationList;
    }

    @GetMapping("/archives")
    public Object listArhives(@RequestParam(value = "location", required = false) Integer locationId,
                              @RequestParam(value = "number", required = false) Integer sensorId,
                              @RequestParam(value = "dateStart", required = false) String dateStartStr,
                              @RequestParam(value = "dateStop", required = false) String dateStopStr,
                              @RequestParam(value = "report", required = false) boolean report,
                              Model model) {
        logger.info("Archive location: {}",locationId,sensorId,dateStartStr,dateStopStr);
        HashMap<String, Object> myHashMap = new HashMap<String, Object>();
        try {
            if (dateStartStr == null || dateStartStr.isEmpty()) {
                dateStartStr = "2000-01-01";
            }
            if (dateStopStr == null || dateStopStr.isEmpty()) {
                dateStopStr = LocalDate.now().toString();
            }
            if (locationId != null) {
                myHashMap.put("locationId", locationId);
            }
            if (sensorId != null) {
                myHashMap.put("sensorId", sensorId);
            }
            if (dateStartStr != null && !dateStartStr.equals("")) {
                LocalDateTime dateStart = LocalDate.parse(dateStartStr).atTime(00, 00);
                myHashMap.put("dateStart", dateStart);
            }
            if (dateStopStr != null && !dateStopStr.equals("")) {
                LocalDateTime dateStop = LocalDate.parse(dateStopStr).atTime(00, 00);
                myHashMap.put("dateStop", dateStop);
            }
        }catch (NullPointerException e){e.printStackTrace();}
        List<Archive> listArchive = this.archiveService.findArchiveWhere(myHashMap);
        model.addAttribute("listArchive", listArchive);
        model.addAttribute("locationList", getLocationList());
        model.addAttribute("listSensor", this.sensorService.findAll());
        model.addAttribute("template", "archive");
        model.addAttribute("location", locationId);
        model.addAttribute("number", sensorId);
        model.addAttribute("dateStart", dateStartStr);
        model.addAttribute("dateStop", dateStopStr);
        if (report) {
            return new ModelAndView(new PdfArchiveView(), "archiveList", listArchive);
        } else {
            return "fragments/archive/archive";
        }
    }
    @GetMapping("/delete")
    @Secured({"ROLE_DBA"})
    public String remove(@RequestParam("idArchive") int idArchive) {
        this.archiveService.deleteById(idArchive);
        return "redirect:/disposal/viewDisposal";
    }


}
