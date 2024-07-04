package com.softserve.itacademy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ModelAndView handleEntityNotFoundException(EntityNotFoundException exception) {
        ModelAndView view = new ModelAndView("error-404", HttpStatus.NOT_FOUND);
        view.addObject("message", exception.getMessage());
        return view;
    }

    @ExceptionHandler(NullEntityReferenceException.class)
    public ModelAndView handleNullEntityReferenceException(NullEntityReferenceException exception) {
        ModelAndView view = new ModelAndView("error-500", HttpStatus.INTERNAL_SERVER_ERROR);
        view.addObject("message", exception.getMessage());
        return view;
    }
}
