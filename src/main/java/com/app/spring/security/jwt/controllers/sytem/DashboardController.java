package com.app.spring.security.jwt.controllers.sytem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/system/management")
public class DashboardController {

    @RequestMapping(value="/dashboard", method = RequestMethod.GET)
    public ModelAndView showLoginPage(ModelAndView mv){
        mv.addObject("pageName", "Dashboard");
        mv.setViewName("dashboard");
        return mv;
    }
}
