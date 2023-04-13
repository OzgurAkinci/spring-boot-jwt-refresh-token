package com.app.spring.security.jwt.controllers.sytem;

import com.app.spring.security.jwt.dto.AdminFormData;
import com.app.spring.security.jwt.security.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(value="/system/management")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @RequestMapping(value="/users", method = RequestMethod.GET)
    public ModelAndView showLoginPage(ModelAndView mv, @RequestParam("currentPage") Optional<Integer> currentPage){
        Integer currentPageParam = currentPage.orElse(1);
        Map<String, Object> categories = userService.findAllWithPaging(currentPageParam, "id", "");
        mv.addObject("usersData", categories);
        mv.setViewName("users");
        return mv;
    }
}
