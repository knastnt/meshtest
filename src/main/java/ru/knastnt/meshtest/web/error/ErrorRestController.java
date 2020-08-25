package ru.knastnt.meshtest.web.error;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.knastnt.meshtest.util.ExceptionUtil;
import ru.knastnt.meshtest.util.exception.ErrorInfo;

@RestController

@RequestMapping(ErrorRestController.REST_URL)
public class ErrorRestController {
    static final String REST_URL = "/error";

    @GetMapping("/last")
    public ErrorInfo.TimedErrorInfo getLast() {
        return ExceptionUtil.checkNotFound(ErrorInfo.getLast(), null);
    }

}
