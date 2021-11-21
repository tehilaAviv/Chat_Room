package com.example.ex4.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This router is responsible for handling any errors that may occur
 */
@Controller
public class Error implements ErrorController {
    /**
     * This function is responsible for handling any errors that may occur
     * @return error page
     */
   @RequestMapping("/error")
    public String error(){
       return "error";
   }

}
