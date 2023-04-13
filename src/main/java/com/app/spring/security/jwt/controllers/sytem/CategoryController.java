package com.app.spring.security.jwt.controllers.sytem;

import com.app.spring.security.jwt.constant.AppConstant;
import com.app.spring.security.jwt.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(value="/system/management")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @RequestMapping(value="/categories", method = RequestMethod.GET)
    public ModelAndView showCategoriesPage(ModelAndView mv, @RequestParam("currentPage") Optional<Integer> currentPage){
        Integer currentPageParam = currentPage.orElse(1);
        Map<String, Object> categories = categoryService.findAllWithPaging(currentPageParam, "id", "");

        mv.addObject("categoriesData", categories);
        mv.setViewName("categories");
        return mv;
    }
}
