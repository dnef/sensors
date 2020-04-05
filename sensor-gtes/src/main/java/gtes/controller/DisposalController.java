package gtes.controller;

import gtes.entity.Disposal;
import gtes.report.PdfDisposalView;
import gtes.service.DisposalService;
import gtes.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/disposal")
public class DisposalController {
    @Autowired
    DisposalService disposalService;
    @Autowired
    private SensorService sensorService;

    @Qualifier(value = "disposalService")
    public void setDisposalService(DisposalService disposalService) {
        this.disposalService = disposalService;
    }

    @Qualifier(value = "sensorService")
    public void setSensorService(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping("/viewDisposal")
    public Object listDisposal(@RequestParam(value = "number", required = false) String sensorNumb,
                               @RequestParam(value = "report", required = false) boolean report,
                               Model model) {
        HashMap<String, Object> myHashMap = new HashMap<String, Object>();
        if (sensorNumb != null && !sensorNumb.equals("")) {
            myHashMap.put("sensorNumb", sensorNumb);
        }
        List<Disposal> listDisposal = this.disposalService.findDisposalSensorNumber(myHashMap);
        model.addAttribute("listDisposal", listDisposal);
        model.addAttribute("numberSensor", this.disposalService.numberList());
        model.addAttribute("findNumber", sensorNumb);
        model.addAttribute("template", "disposal");
        if (report) {
            return new ModelAndView(new PdfDisposalView(), "disposalList", listDisposal);
        } else {
            return "index";
        }
    }

    @GetMapping("/delete")
    @Secured({"ROLE_ADMIN"})
    public String remove(@RequestParam("idDisposal") int idDisposal) {
        this.disposalService.deleteById(idDisposal);
        return "redirect:/disposal/viewDisposal";
    }
}
