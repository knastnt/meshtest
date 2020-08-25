package ru.knastnt.meshtest.util;


import org.springframework.validation.BindingResult;
import ru.knastnt.meshtest.util.exception.NotFoundException;

import java.util.stream.Collectors;

public class ExceptionUtil {
    public static Throwable getRootCause(Throwable t) {
        Throwable result = t;
        Throwable cause;

        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }

    public static String getErrorResponse(BindingResult result) {
        return result.getFieldErrors().stream()
                        .map(fe -> String.format("[%s] %s", fe.getField(), fe.getDefaultMessage()))
                        .collect(Collectors.joining("; "));
    }

    public static <T> T checkNotFound(T object, String msg) {
        if (object == null) {
            throw new NotFoundException("Не найден объект" + (msg == null ? "" : (" c " + msg)));
        }
        return object;
    }
}