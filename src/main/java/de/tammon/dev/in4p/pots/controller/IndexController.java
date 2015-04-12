package de.tammon.dev.in4p.pots.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by tammschw on 12/04/15.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(method = RequestMethod.GET)
    public String welcome(Model model) {
        model.addAttribute("message", "hello World!");
        return "index";
    }
}
