package eu.ea.photo.converter;

import eu.ea.photo.entity.Backstage;
import eu.ea.photo.entity.BackstagePhoto;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FormDataToBackstage {
    public static Backstage convert(MultipartFile file, String name, int priority) {
        try {
            byte[] bytes = file.getBytes();
            StringBuilder sb = new StringBuilder();
            sb.append("data:");
            sb.append(file.getContentType().matches("png") ? "image/png" : "image/jpeg");
            sb.append(";base64,");
            sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(bytes, false)));
            return Backstage.builder()
                    .mainPhoto(sb.toString())
                    .priority(priority)
                    .name(name)
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Backstage();
    }

    public static BackstagePhoto convert(MultipartFile file, Backstage backstage) {
        try {
            byte[] bytes = file.getBytes();
            StringBuilder sb = new StringBuilder();
            sb.append("data:");
            sb.append(file.getContentType().matches("png") ? "image/png" : "image/jpeg");
            sb.append(";base64,");
            sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(bytes, false)));
            return BackstagePhoto.builder()
                    .photo(sb.toString())
                    .backstage(backstage)
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BackstagePhoto();
    }

}
