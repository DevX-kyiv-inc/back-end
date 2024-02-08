package com.acheron.devx.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final AmazonS3 s3;

    @SneakyThrows
    public String saveImage(MultipartFile file, Long id){
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());
        String path=UUID.randomUUID()+file.getOriginalFilename();
        PutObjectRequest request = new PutObjectRequest("devx-int20h",path , file.getInputStream(), objectMetadata);
        PutObjectResult putObjectResult = s3.putObject(request);
        System.out.println(putObjectResult.getContentMd5());
        System.out.println(putObjectResult);
        String presignedUrl = s3.getUrl("devx-int20h",path).toString();
        return presignedUrl;
    }

}
