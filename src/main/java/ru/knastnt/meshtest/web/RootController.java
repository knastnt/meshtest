package ru.knastnt.meshtest.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class RootController {
//    @GetMapping("/exit")
//    public @ResponseBody String exit(HttpServletRequest request) throws ServletException {
//        request.logout();
//        return "redirect:/exit-success";
//    }

    @GetMapping("/exit-success")
    public @ResponseBody String exitSuccess() {
        return "Приложение закрыто";
    }
}
