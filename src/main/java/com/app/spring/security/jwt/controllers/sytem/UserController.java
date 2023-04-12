package com.app.spring.security.jwt.controllers.sytem;

import com.app.spring.security.jwt.dto.AdminFormData;
import com.app.spring.security.jwt.security.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/system/management")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @RequestMapping(value="/users", method = RequestMethod.GET)
    public ModelAndView showLoginPage(ModelAndView mv){
        mv.addObject("users", userService.findAll());
        mv.addObject("adminFormData", new AdminFormData(null, null, "ROLE_USER", true));
        mv.addObject("pageName", "Dashboard");
        mv.setViewName("users");
        return mv;
    }
}
