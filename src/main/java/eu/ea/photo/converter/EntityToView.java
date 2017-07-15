package eu.ea.photo.converter;

import eu.ea.photo.entity.BackstagePhoto;
import eu.ea.photo.entity.Category;
import eu.ea.photo.entity.Photo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityToView {
    public static List<String> convert(List<BackstagePhoto> photos) {
        List<String> files = new ArrayList<>();
        for (BackstagePhoto photo : photos) {
            files.add(photo.getPhoto());
        }
        return files;
    }

    public static Map<Integer, List<String>> convertFromCategoriesToString(List<Category> categories) {
        Map<Integer, List<String>> result = new HashMap<>();
        for (Category category : categories) {
            List<String> photos = new ArrayList<>();
            for (Photo photo : category.getPhotos()) {
                photos.add(photo.getPhoto());
            }
            result.put(category.getId(), photos);
        }
        return result;
    }
}
