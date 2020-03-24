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
/**
 * <b>Storage Service</b>
 * This class is the service responsible for saving the picture in a file.
 * It also fetches the image and forwards it to the ImageController.
 * @author Cédric_P
 * */
@Service
public class StorageService {
    private final Path rootLocation;

    public StorageService() {
        this.rootLocation = Paths.get("uploads");
    }
    /**
     * @param file is the image uploaded in the Angular form.
     *             If the image already exists in the uploads file, then it replaces the existing image.
     * @throws RuntimeException if an error occur, a RuntimeException is thrown/
     *
     * */
    public void savePicture(MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try (InputStream inputStream = file.getInputStream()){
            Files.copy(inputStream, this.rootLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e){
            throw new RuntimeException("Failed to store file" + fileName, e);
        }
    }
    /**
     * @param fileName this parameter refers to the image name in the uploads file.
     *                 if it exists it is fetched and returned to the ImageController.
     * @throws RuntimeException if the image can't be fetched, then a RuntimeException is thrown
     *
     * */
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
