package eu.ea.photo.controller;

import eu.ea.photo.converter.EntityToView;
import eu.ea.photo.converter.FormDataToBackstage;
import eu.ea.photo.entity.Backstage;
import eu.ea.photo.entity.BackstagePhoto;
import eu.ea.photo.service.BackstagePhotoService;
import eu.ea.photo.service.BackstageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class BackstageController {

    @Autowired
    private BackstageService backstageService;

    @Autowired
    private BackstagePhotoService backstagePhotoService;

    @PostMapping("/backstage")
    public String savePhoto(@RequestParam("photos")List<MultipartFile> photos,
                            @RequestParam("mainPhoto") MultipartFile mainPhoto,
                            @RequestParam("name") String name,
                            @RequestParam("priority") int priority) {

        Backstage backstage = FormDataToBackstage.convert(mainPhoto, name, priority);
        backstage = backstageService.save(backstage);
        for (MultipartFile photo : photos) {
            backstagePhotoService.save(FormDataToBackstage.convert(photo, backstage));
        }
        return "redirect:/admin";
    }

    @ResponseBody
    @GetMapping("/backstage/delete/{id}")
    public boolean deletePhoto(@PathVariable int id) {
        List<BackstagePhoto> backstagePhotos = backstageService.findOne(id).getBackstagePhotos();
        for (BackstagePhoto backstagePhoto : backstagePhotos) {
            backstagePhotoService.delete(backstagePhoto.getId());
        }
        backstageService.delete(id);
        return true;
    }

    @ResponseBody
    @PostMapping("/backstage/getPhotos")
    public List<String> getPhotos(@RequestParam String name) {
        return EntityToView.convert(backstageService.findOne(name).getBackstagePhotos());
    }
}
