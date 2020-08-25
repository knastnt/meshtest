package ru.knastnt.meshtest.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RootController {
    public static final String EXIT_SUCCESS_URL = "/exit-success";

    @GetMapping(EXIT_SUCCESS_URL)
    public @ResponseBody String exitSuccess() {
        return "Приложение закрыто";
    }
}
