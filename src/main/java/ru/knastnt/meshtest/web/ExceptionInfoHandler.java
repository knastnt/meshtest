package ru.knastnt.meshtest.web;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.knastnt.meshtest.util.ExceptionUtil;
import ru.knastnt.meshtest.util.exception.ErrorInfo;
import ru.knastnt.meshtest.util.exception.NotFoundException;

import javax.servlet.http.HttpServletRequest;


@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.LOWEST_PRECEDENCE - 100)  //Приоритет немного выше самого низкого для всех хэндлеров
public class ExceptionInfoHandler {

    @Order //Самый низкий приоритет
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR) //500
    @ExceptionHandler(Exception.class)
    public ErrorInfo handleError(HttpServletRequest req, Exception e) {
        return new ErrorInfo(ExceptionUtil.getRootCause(e).getMessage());
    }


    @ResponseStatus(value = HttpStatus.FORBIDDEN)  // 403
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorInfo conflict(HttpServletRequest req, DataIntegrityViolationException e) {
        return new ErrorInfo(ExceptionUtil.getRootCause(e).getMessage());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)  // 400
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorInfo validationError(HttpServletRequest req, MethodArgumentNotValidException e) {
        return new ErrorInfo(ExceptionUtil.getErrorResponse(e.getBindingResult()));
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND) //404
    @ExceptionHandler(NotFoundException.class)
    public ErrorInfo notFoundError(HttpServletRequest req, Exception e) {
        return new ErrorInfo(ExceptionUtil.getRootCause(e).getMessage());
    }

}