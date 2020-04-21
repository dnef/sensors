package gtes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {
@GetMapping("/index")
    public String about(){
    return "index";
}

}
