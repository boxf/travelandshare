package com.projects.travelandshare.restcontroller;


import com.projects.travelandshare.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <b>ImageController</b>
 * <p>Will eventually be used to display a stored image.</p>
 * @author Cédric_P
 * */
@Controller
public class ImageController {
    @Autowired
    private StorageService storageService;
/**
 * serveFile will fetch an image to provide it to Angular to be displayed.
 * @param filename is a string referring to the name of the stored image.
 *                 the String permits "Resource" to load the right image.
 * @return returns the Http response. Sends the image in the Http body and declares the method went through.
 * */
    @GetMapping("/file/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename){
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename\"" + file.getFilename() +"\"").body(file);
    }

}
