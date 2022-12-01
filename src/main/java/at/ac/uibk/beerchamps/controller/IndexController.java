package at.ac.uibk.beerchamps.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class IndexController {

    public IndexController() {}

    @GetMapping("/")
    public String getIndexView() {
        return "index";
    }
}
