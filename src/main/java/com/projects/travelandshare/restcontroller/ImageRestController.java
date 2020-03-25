package com.projects.travelandshare.restcontroller;

import com.projects.travelandshare.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
@CrossOrigin(origins = "http://localhost:4200")
public class ImageRestController {
    @Autowired
    private StorageService storageService;

    @PostMapping(value = "/upload")
    public ResponseEntity<Object> uploadImage(@RequestParam("MyFile") MultipartFile imageFile){
        try {
            storageService.savePicture(imageFile);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
