package gtes.controller;

import com.google.common.collect.HashMultimap;
import gtes.entity.*;
import gtes.report.PdfSensorView;
import gtes.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@SessionAttributes("hasFilter")
@RequestMapping("/sensor")
public class SensorController {
    @Autowired
    private SensorService sensorService;
    @Autowired
    private ModelSensorService modelSensorService;
    @Autowired
    private UnitService unitService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private FirmService firmService;
    @Autowired
    private TypesensService typesensService;
    @Autowired
    private ArchiveService archiveService;
    @Autowired
    private DisposalService disposalService;
    @Autowired
    private LocationInstallService locationInstallService;
    @Autowired
    private LocationService locationService;

    @Qualifier(value = "sensorService")
    public void setSensorService(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Qualifier(value = "modelSensorService")
    public void setModelSensorService(ModelSensorService modelSensorService) {
        this.modelSensorService = modelSensorService;
    }

    @Qualifier(value = "unitService")
    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }

    @Qualifier(value = "countryService")
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    @Qualifier(value = "firmService")
    public void setFirmService(FirmService firmService) {
        this.firmService = firmService;
    }

    @Qualifier(value = "typesensService")
    public void setTypesensService(TypesensService typesensService) {
        this.typesensService = typesensService;
    }

    @Qualifier(value = "archiveService")
    public void setArchiveService(ArchiveService archiveService) {
        this.archiveService = archiveService;
    }

    @Qualifier(value = "disposalService")
    public void setDisposalService(DisposalService disposalService) {
        this.disposalService = disposalService;
    }

    @Qualifier(value = "locationInstallService")
    public void setLocationInstallService(LocationInstallService locationInstallService) {
        this.locationInstallService = locationInstallService;
    }

    @Qualifier(value = "locationService")
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }


    public List<Typesens> getTypeList() {
        List<Typesens> typesensList = this.typesensService.findAll();
        return typesensList;
    }

    public List<ModelSensor> getModelList() {
        List<ModelSensor> modelSensorList = this.modelSensorService.findAll();
        return modelSensorList;
    }

    public List<Unit> getUnitList() {
        List<Unit> unitList = this.unitService.findAll();
        return unitList;
    }

    public List<Country> getCountryList() {
        List<Country> countryList = this.countryService.findAll();
        return countryList;
    }

    public List<Firm> getFirmList() {
        List<Firm> firmList = this.firmService.findAll();
        return firmList;
    }

    public List<Location> getLocationList() {
        List<Location> locationList = this.locationService.findAll();
        return locationList;
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
    //Альтернативный метод построения заголовка для фильтра
//    public String createHeaderFilter(HashMultimap<String, Object> filterHashMap){
//        if (!filterHashMap.isEmpty()){
//            StringBuilder sb=new StringBuilder();
//            if(filterHashMap.containsKey("modelId")){
//                Object[] model = filterHashMap.get("modelId").toArray();
//                sb.append("Модель СИ: "+this.modelSensorService.findOne(Integer.parseInt(model[0].toString())).getModelName());
//            }
//            if (filterHashMap.containsKey("locationId")){
//                sb.append("Установка: ");
//                Object[] locationMass=filterHashMap.get("locationId").toArray();
//                for (int i=0;i<locationMass.length;i++){
//                    sb.append("'"+this.locationService.findOne(Integer.parseInt(locationMass[i].toString())).getNameLoc()+"' ");
//                }
//
//            }
//            if (filterHashMap.containsKey("sensorNumb")){
//                sb.append("Сер.номер СИ: ");
//                sb.append(filterHashMap.get("sensorNumb"));
//            }
//            if (filterHashMap.containsKey("verification")){
//                if (filterHashMap.get("verification").equals("true")){
//                    sb.append("Тип метрологии: Поверка");
//                }else{
//                    sb.append("Тип метрологии: Калибровка");
//                }
//            }
//            return sb.toString();
//        }
//
//        return null;
//    }

    @GetMapping("/sensors")
    public Object sensorFilter(@RequestParam(value = "model", required = false) Integer modelId,
                               @RequestParam(value = "location", required = false) Integer[] locationId,
                               @RequestParam(value = "number", required = false) String sensorNumb,
                               @RequestParam(value = "verification", required = false) Boolean verification,
                               Model model) throws NullPointerException {
        HashMultimap<String, Object> filterHashMap = HashMultimap.create();
        StringBuilder sb = new StringBuilder();
        try {
            if (modelId != null) {
                filterHashMap.put("modelId", modelId);
                sb.append("*Модель СИ: '" + this.modelSensorService.findOne(modelId).getModelName() + "' ");
            }
            if (locationId != null) {
                sb.append("*Установка: ");
                for (int i = 0; i < locationId.length; i++) {
                    filterHashMap.put("locationId", locationId[i]);
                    sb.append("'" + this.locationService.findOne(locationId[i]).getNameLoc() + "' ");
                }
                sb.append(" ");
            }
            if (sensorNumb != null && sensorNumb.trim().length() != 0) {
                filterHashMap.put("sensorNumb", sensorNumb);
                sb.append("*Сер.номер СИ: '" + sensorNumb + "' ");
            }
            if (verification != null) {
                filterHashMap.put("verification", verification);
                if (verification) {
                    sb.append("*Метрология: 'Поверка'" + " ");
                } else {
                    sb.append("*Метрология: 'Калибровка'" + " ");
                }

            }
            filterHashMap.put("headerFilter", sb.toString());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

//        List<Sensor> listSensor=this.sensorService.findSensorWhere(filterHashMap);
        model.addAttribute("listSensor", this.sensorService.findSensorWhere(filterHashMap));
        model.addAttribute("modelSensorList", getModelList());
        model.addAttribute("localDateNow", LocalDate.now());
        model.addAttribute("template", "sensor");
        model.addAttribute("locationList", getLocationList());
        model.addAttribute("hasFilter", filterHashMap);
        model.addAttribute("headerFilter", sb.toString());
        //Альтернативный метод построения заголовка для фильтра
//            model.addAttribute("headerNameFilter",createHeaderFilter(filterHashMap));
        return "index";
    }

    @GetMapping("/report")
    public ModelAndView sensorPdf(@ModelAttribute("hasFilter") HashMultimap<String, Object> filterHashMap, Model model) {
        List<Sensor> listSensor = this.sensorService.findSensorWhere(filterHashMap);
//        String filter = createHeaderFilter(filterHashMap);
//        model.addAttribute("filter", filter);
        model.addAttribute("listSensorPdf", listSensor);
        model.addAttribute("headerFilter", filterHashMap.get("headerFilter"));
        return new ModelAndView(new PdfSensorView(), "modelPdf", model);
    }

    @GetMapping("addFormSensor")
    public String addForm(Model model) {
        Sensor sensor = new Sensor();
        model.addAttribute("sensor", sensor);
        model.addAttribute("modelSensorList", getModelList());
        model.addAttribute("typesensList", getTypeList());
        model.addAttribute("unitList", getUnitList());
        model.addAttribute("countryList", getCountryList());
        model.addAttribute("firmList", getFirmList());
        model.addAttribute("locationList", getLocationList());
        return "fragments/sensor/newSensor";
    }

    //TODO: При сохранении писать в архив как в LocationInstallController
    @PostMapping("/saveSensor")
    public String saveSensor(@Valid @ModelAttribute("sensor") Sensor theSensor, BindingResult result, Model model) {
        if (result.hasErrors()) {
//           model.addAttribute("sensor",theSensor);
            System.out.println(result.hasErrors());
            model.addAttribute("modelSensorList", getModelList());
            model.addAttribute("typesensList", getTypeList());
            model.addAttribute("unitList", getUnitList());
            model.addAttribute("countryList", getCountryList());
            model.addAttribute("firmList", getFirmList());
            model.addAttribute("locationList", getLocationList());
            return "fragments/sensor/newSensor";
        } else {
            if (theSensor.getDateManufacture().isAfter(theSensor.getDateVerification())) {
                model.addAttribute("modelSensorList", getModelList());
                model.addAttribute("typesensList", getTypeList());
                model.addAttribute("unitList", getUnitList());
                model.addAttribute("countryList", getCountryList());
                model.addAttribute("firmList", getFirmList());
                model.addAttribute("locationList", getLocationList());
                model.addAttribute("messageDate", "Дата выпуска не может быть позже даты поверки");
                return "fragments/sensor/newSensor";
            }
            try {
                //Сохранение изменений в архив
                if (theSensor.getIdSensor() != null) {
                    Sensor sensorOld = sensorService.findOne(theSensor.getIdSensor());
                    if (sensorOld != null) {
                        if (sensorOld.getSensorNumb() != theSensor.getSensorNumb()) {
                            archiveService.save(addArchive(sensorOld.getLocationId(), theSensor.getIdSensor(), "изменение серийного номера с: " + sensorOld.getSensorNumb() +
                                    " на: " + theSensor.getSensorNumb() + " Пользователь: " + getPrincipal()));
                        }
                        if (sensorOld.getLocationId() != theSensor.getLocationId()) {
                            archiveService.save(addArchive(1, theSensor.getIdSensor(), "перемещение с установки: " + sensorOld.getLocationByLocationId().getNameLoc() +
                                    " на склад. Пользователь: " + getPrincipal()));
                        }
                    }
                }
                sensorService.save(theSensor);
                archiveService.save(addArchive(theSensor.getLocationId(), theSensor.getIdSensor(), "Установлен: " + locationService.findOne(theSensor.getLocationId()).getNameLoc() +
                        " Пользователь: " + getPrincipal()));
//            LocalDate now = LocalDate.now();
//            LocalDate verif = theSensor.getDateVerification().plusMonths(theSensor.getIntervalVerification());
//            theSensor.setVerification(now.isBefore(verif));

            } catch (NullPointerException ne) {
                ne.printStackTrace();
            }
            return "redirect:/sensor/sensors";
        }
    }

    //TODO при удалении связь с архивом, архив чистить(каскад) или не выводить удаленные, тогда поле в таблицу - ликвидирован. Нельзя удалить если привязан к установке или архиву - предупреждающее окно.
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/delete")
    public String removeSensor(@RequestParam("idSensor") int idSensor, RedirectAttributes redirectAttributes) {
        //TODO перенести в сервис. передать idSensor и делать все в сервисе
        //List<LocationInstall> installListBySensor = this.locationInstallService.findInstallSensor(idSensor);
        Sensor sensorDel = sensorService.findOne(idSensor);
        if (sensorDel.getLocationId() == null) {
            List<Archive> archiveListBySensor = this.archiveService.findArchiveBySensorId(idSensor);
            if (!archiveListBySensor.isEmpty()) {
                //TODO вставляется только одна строка - locationInstallDel.getSensorId(), здесь в архив утилизация, а это все дело при удалении сенсора
                for (Archive archive : archiveListBySensor) {
                    System.out.println(archive.getIdArchive());
                    Disposal disposalEntity = new Disposal();
                    disposalEntity.setTypeSensor(archive.getSensorBySensorId().getModelSensorByModelId().getTypesensByTypesensId().getNameType());
                    disposalEntity.setModelSensor(archive.getSensorBySensorId().getModelSensorByModelId().getModelName());
                    disposalEntity.setVersionSensor(archive.getSensorBySensorId().getModelSensorByModelId().getModelVersion());
                    disposalEntity.setNumberSensor(archive.getSensorBySensorId().getSensorNumb());
                    disposalEntity.setInventoryNumberSensor(archive.getSensorBySensorId().getInventoryNumb());
                    disposalEntity.setNote(archive.getNote() + " Удаление пользователем: " + getPrincipal());
                    disposalEntity.setDateArchive(archive.getInstallDate());
                    //.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                    disposalEntity.setDateDisposal(LocalDateTime.now());
                    this.disposalService.save(disposalEntity);
                }
            }
            this.sensorService.deleteById(idSensor);
            return "redirect:/sensor/sensors";
        } else {
            redirectAttributes.addFlashAttribute("message", "Датчик закреплен за " + sensorDel.getLocationByLocationId().getNameLoc() + ", удаление возможно только не закрепленного датчика");
            //this.sensorService.deleteById(idSensor);
            return "redirect:/sensor/sensors";
        }
    }


    @RequestMapping("/updateFormSensor")
    public String editModel(@RequestParam("idSensor") int idSensor, Model model) {
        Sensor sensor = this.sensorService.findOne(idSensor);
        model.addAttribute("sensor", sensor);
        model.addAttribute("modelSensorList", getModelList());
        model.addAttribute("unitList", getUnitList());
        model.addAttribute("countryList", getCountryList());
        model.addAttribute("firmList", getFirmList());
        model.addAttribute("locationList", getLocationList());
        return "fragments/sensor/editSensor";
    }
}
