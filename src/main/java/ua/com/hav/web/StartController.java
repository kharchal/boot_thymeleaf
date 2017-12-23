package ua.com.hav.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * Created by Sunny on 14.10.2017.
 */
@Controller
public class StartController {

    @RequestMapping()
    public String index() {
        System.out.println("index");
        return "index";
    }

    @RequestMapping("/start")
    public String start(Model model) {
        model.addAttribute("time", new Date());
        System.out.println("start...");
        return "start";
    }

    @RequestMapping("/form")
    public String form(@RequestParam Integer factor, Model model) {
        if (factor > 0) {
            model.addAttribute("time", new Date());
        } else {
            model.addAttribute("time", "-----");
        }
        model.addAttribute("factor", factor);
        return "start";
    }
}
