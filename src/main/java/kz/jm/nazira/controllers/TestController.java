package kz.jm.nazira.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/admin/nazira")
    public String getNazira(){
        return "nazira";
    }


}
