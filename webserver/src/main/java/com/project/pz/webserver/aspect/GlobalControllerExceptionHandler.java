package com.project.pz.webserver.aspect;

import com.project.pz.webserver.exception.MetricNotFoundException;
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

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MetricNotFoundException.class)
    @ResponseBody
    String handleUserNotFoundException(MetricNotFoundException ex) {
        return "Nie znaleziono metryki dla monitora: " + ex.getMonitorId() +
                ", sensora:" + ex.getSensorId() + " o id:" + ex.getMetricId();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    String handleUserNotFoundException() {
        return "Nie odnaleziono użytkownika";
    }

}
