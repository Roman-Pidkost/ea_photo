package eu.ea.photo.controller;

import eu.ea.photo.converter.EntityToView;
import eu.ea.photo.converter.FormDataToPhoto;
import eu.ea.photo.entity.Category;
import eu.ea.photo.service.BackstageService;
import eu.ea.photo.service.CategoryService;
import eu.ea.photo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private BackstageService backstageService;

    @GetMapping
    public String show(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("photos", photoService.findAll());
        model.addAttribute("backstages", backstageService.findAll());
        return "admin";
    }

    @PostMapping("/photo")
    public String savePhoto(@RequestParam("photos")List<MultipartFile> photos,
                            @RequestParam("tags") String tags,
                            @RequestParam("category") Integer id,
                            @RequestParam("priority") int priority,
                            Model model,
                            SessionStatus status) {
        for (MultipartFile photo : photos) {
            photoService.save(FormDataToPhoto.convert(photo,tags,categoryService.findOne(id), priority));
        }
        status.setComplete();
        return "redirect:/admin";
    }

    @PostMapping("/category")
    public String saveCategory(@RequestParam("name") String name,
                               @RequestParam("priority") int priority,
                               Model model) {
        categoryService.save(Category.builder()
                .name(name)
                .priority(priority)
                .build());
        return "redirect:/admin";
    }

    @ResponseBody
    @PostMapping("/category/getPhotos")
    public Map<Integer, List<String>> getPhotos() {
        return EntityToView.convertFromCategoriesToString(categoryService.findAll());
    }

    @ResponseBody
    @GetMapping("/photo/delete/{id}")
    public boolean deletePhoto(@PathVariable int id) {
        photoService.delete(id);
        return true;
    }

    @ResponseBody
    @GetMapping("/category/delete/{id}")
    public boolean deleteCategory(@PathVariable int id) {
        photoService.delete(id);
        return true;
    }

}
