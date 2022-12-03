package at.ac.uibk.beerchamps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    public IndexController() {}

    @GetMapping("/")
    public String getIndexView() {
        return "index";
    }
}
