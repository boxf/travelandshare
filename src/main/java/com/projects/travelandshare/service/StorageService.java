package com.projects.travelandshare.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageService {
    private final Path rootLocation;
    public StorageService(){
        this.rootLocation = Paths.get("uploads");
    }
    public void savePicture(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try(InputStream inputStream = file.getInputStream()){
            Path target = this.rootLocation.resolve(filename);
            if (!target.toFile().exists()){
                Files.createDirectories(target);
            }
            Files.copy(inputStream, target, StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException e){
            throw new RuntimeException("failed to store file" + filename, e);
        }
    }
}
