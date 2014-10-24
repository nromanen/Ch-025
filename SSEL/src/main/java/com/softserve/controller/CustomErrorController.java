package com.softserve.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
class CustomErrorController {
 
        @RequestMapping("error404")
        public String error404page() {
                return "error404";
        }
       
        @RequestMapping("error403")
        public String error403page() {
                return "error403";
        }
 
}