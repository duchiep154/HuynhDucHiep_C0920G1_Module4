package vn.codegym.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import vn.codegym.services.StudentNameException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(StudentNameException.class)
    public ModelAndView showErrorPage2() {
        return new ModelAndView("student/error");
    }
}
