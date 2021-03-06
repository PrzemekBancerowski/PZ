package com.project.pz.webserver.aspect;

import com.project.pz.webserver.exception.MetricNotFoundException;
import com.project.pz.webserver.exception.MetricOwnerException;
import com.project.pz.webserver.exception.MonitorNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Piotr Sołtysiak on 2016-06-13.
 * Contact: piotrek.soltysiak@gmail.com
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    private Logger logger = Logger.getLogger(GlobalControllerExceptionHandler.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MetricNotFoundException.class)
    @ResponseBody
    String handleUserNotFoundException(MetricNotFoundException ex) {
        return logAndReturn("Nie znaleziono metryki dla monitora: " + ex.getMonitorId() +
                ", sensora:" + ex.getSensorId() + " o id:" + ex.getMetricId(), ex);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    String handleUserNotFoundException() {
        return "Nie odnaleziono użytkownika";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MonitorNotFoundException.class)
    @ResponseBody
    String handleMonitorNotFoundException(MonitorNotFoundException ex) {
        return logAndReturn("Nie odnaleziono monitora", ex);
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(MetricOwnerException.class)
    @ResponseBody
    String handleMetricOwnerException(MetricOwnerException ex) {
        return logAndReturn(ex.getMessage(), ex);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    String handleOtherException(Exception ex) {
        return logAndReturn("Wystąpił nieoczekiwany błąd", ex);
    }


    private String logAndReturn(String message, Throwable ex) {
        logger.error(message, ex);
        return message;
    }

}
