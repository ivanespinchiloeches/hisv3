package com.ivan.thehealthsoftwarecompany.hisv3.controller.request;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController {

    @SuppressWarnings("SameReturnValue")
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        //do something like logging
        return "error";
    }
}
