package eu.ea.photo.controller;

import eu.ea.photo.service.BackstageService;
import eu.ea.photo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BackstageService backstageService;

    @GetMapping
    public String getView(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("backstages", backstageService.findAll());
        return "index";
    }
}
