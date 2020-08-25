package ru.knastnt.meshtest.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
    @GetMapping("/exit")
    public String root() {
        return "redirect:exit-success";
    }
}
