package com.app.spring.security.jwt.controllers.sytem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/system/auth")
public class SignInController {

    @RequestMapping(value="/signIn", method = RequestMethod.GET)
    public ModelAndView showLoginPage(ModelAndView mv){
        mv.addObject("pageName", "HomePage");
        mv.setViewName("signIn");
        return mv;
    }
}
