package com.ivan.thehealthsoftwarecompany.hisv3.controller.request.advice;

import com.ivan.thehealthsoftwarecompany.hisv3.exception.ErrorResponse;
import com.ivan.thehealthsoftwarecompany.hisv3.exception.UserNotFoundException;
import com.ivan.thehealthsoftwarecompany.hisv3.exception.UsernameExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@ControllerAdvice
public class AppControllerAdvice {

    final Logger LOG = LoggerFactory.getLogger(AppControllerAdvice.class);

    @ExceptionHandler(UsernameExistsException.class)
    public ResponseEntity<ErrorResponse> handleUsernameExistsException(UsernameExistsException ex) {
        // Log the exception and return a meaningful response
        LOG.info(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),ex.getMessage(),new Date()) );//"An error occurred probably related with a violation of the data integrity "
    }

    @SuppressWarnings("SameReturnValue")
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(UserNotFoundException ex, RedirectAttributes redirectAttributes) {
        // Log the exception and return a meaningful response
        LOG.info(ex.getMessage());
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        return "redirect:/error";
        //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred probably related with an illegal argument" );
    }


    @SuppressWarnings("SameReturnValue")
    @ExceptionHandler(UserNotFoundException.class)
    public String handleUsernameNotFoundException(UserNotFoundException ex, RedirectAttributes redirectAttributes) {
        LOG.info(ex.getMessage());
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        return "redirect:/error";
    }


}
