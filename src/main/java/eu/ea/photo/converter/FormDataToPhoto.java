package eu.ea.photo.converter;

import eu.ea.photo.entity.Category;
import eu.ea.photo.entity.Photo;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FormDataToPhoto {
    public static Photo convert(MultipartFile file, String tags, Category category, int priority) {
        try {
            byte[] bytes = file.getBytes();
            StringBuilder sb = new StringBuilder();
            sb.append("data:");
            sb.append(file.getContentType().matches("png") ? "image/png" : "image/jpeg");
            sb.append(";base64,");
            sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(bytes, false)));
            return Photo.builder()
                    .photo(sb.toString())
                    .category(category)
                    .tags(tags)
                    .priority(priority)
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Photo();
    }
}
