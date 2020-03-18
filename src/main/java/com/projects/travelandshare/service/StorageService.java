package com.projects.travelandshare.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
@Service
public class StorageService {
    private final Path rootLocation;

    public StorageService() {
        this.rootLocation = Paths.get("uploads");
    }
    public void savePicture(MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try (InputStream inputStream = file.getInputStream()){
            Files.copy(inputStream, this.rootLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e){
            throw new RuntimeException("Failed to store file" + fileName, e);
        }
    }
    public Resource loadAsResource (String fileName){
        try{
            Path file = this.rootLocation.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()){
                return resource;
            } else{
                throw new RuntimeException("Failed to load image" + fileName);
            }
        } catch(MalformedURLException e){
            throw new RuntimeException("Failed to load file" + fileName);
        }
    }
}
