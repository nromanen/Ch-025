package com.softserve.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
class CustomErrorController {
 
        @RequestMapping("error404")
        public String error404page() {
                return "errorPages/error404";
        }
       
        @RequestMapping("error403")
        public String error403page() {
                return "errorPages/error403";
        }
        
        @RequestMapping("error400")
        public String error400page() {
                return "errorPages/error400";
        }
 
}