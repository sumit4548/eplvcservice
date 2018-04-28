package com.eplvc.eplvcservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
/**
 * Created by JavaDeveloperZone on 19-07-2017.
 */
@RestController
public class SpringBooJDBCSessionController{
    @GetMapping("/viewSessionData")                     // it will handle all request for /welcome
    public java.util.Map<String,Integer> start(HttpServletRequest request) {
        Integer integer =(Integer) request.getSession().getAttribute("hitCounter"); 
        if(integer==null){
            integer=new Integer(0);
            integer++;
            request.getSession().setAttribute("hitCounter",integer);  // it will write data to tables
        }else{
            integer++;
            request.getSession().setAttribute("hitCounter",integer);  // it will write data to tables
        }
        java.util.Map<String,Integer> hitCounter=new HashMap<>();
        hitCounter.put("Hit Counter",integer);
        return hitCounter;
    }
}