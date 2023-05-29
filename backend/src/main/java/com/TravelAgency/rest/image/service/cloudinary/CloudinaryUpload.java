package com.TravelAgency.rest.image.service.cloudinary;

import com.TravelAgency.rest.image.service.UploadService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.Map;

@AllArgsConstructor
public class CloudinaryUpload implements UploadService {
    private Cloudinary cloudinary;

    @Override
    public Map uploadImage(Object object) {
        try {
            return cloudinary.uploader().upload(object, ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
