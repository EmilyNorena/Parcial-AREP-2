package edu.eci.arep.proxyService;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class viewController {

    @GetMapping("/index")
    public String home(){
        return "index";
    }
}
