package com.packt.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Erling Molde on 31.10.2016.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping
    public String welcome(Model model, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("greeting", "Welcome to Web Store!");
        redirectAttributes.addFlashAttribute("tagline", "The one and only amazing web store");

        return "redirect:/welcome/greeting";
    }

    @RequestMapping("/welcome/greeting")
    public String greeting() {
        return "welcome";
    }
}