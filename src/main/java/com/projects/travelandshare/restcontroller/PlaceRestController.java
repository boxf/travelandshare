package com.projects.travelandshare.restcontroller;

import com.projects.travelandshare.model.dto.CreatePlaceDTO;
import com.projects.travelandshare.model.entity.Place;
import com.projects.travelandshare.service.PlaceDTOService;
import com.projects.travelandshare.service.PlaceService;
import com.projects.travelandshare.service.StorageService;
import com.projects.travelandshare.service.exception.ConflictException;
import com.projects.travelandshare.util.Counties;
import com.projects.travelandshare.util.Types;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * RestController for Place. It permish to get back the information in the model and communicate with the view
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class PlaceRestController {

    /**
     * Dependencies injection of PlaceService
     */
    @Autowired
    private PlaceService placeService;
    @Autowired
    private PlaceDTOService placeDTOService;

    List<Place> placeList;
    public PlaceRestController (PlaceService placeService){
        this.placeService=placeService;
    }


    /**
     * Method for have a list of places by their counties. It works with the url ".../api/place/" + the county
     * @param counties: enumeration of counties
     * @return a list of places on a HTTP page
     * @author Dambrine François
     */
    @RequestMapping("/place/{counties}")
    List<Place> getPlaceByCounty(@PathVariable("counties") Counties counties){
        List<Place> placeList = placeService.findPlaceByCounty(counties);
       return placeList;
    }

    /**
     *
     * Methode for have the all places in the dataBase. It works with the url ".../api/places"
     * @return a list of all the places on a HTTP page
     * @author Dambrine François
     */
    @RequestMapping ("/places")
    public List<Place> getAllPlace() {
        List<Place> placeList = placeService.findAllPlace();
        return placeList;
    }
    @PostMapping(value = "/place")
    public ResponseEntity<Object> addNewPlace(@ModelAttribute CreatePlaceDTO newPlace) throws ConflictException {
        try {
                Place newPlaceEntity = placeDTOService.placeDTOCopyPlaceEntity(newPlace);
                newPlaceEntity = placeService.registerPlace(newPlaceEntity);
                return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ConflictException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }



 /*   @PostMapping(value = "/upload")
    public ImageModel uploadImage(@RequestParam("MyFile") MultipartFile file) throws IOException {
        ImageModel imageModel = new ImageModel(file.getOriginalFilename(), file.getContentType(), compressBytes(file.getBytes()));
        final ImageModel savedImage = imageRepository.save(imageModel);
        System.out.println("Image saved");
        return savedImage;
    }

    @GetMapping(path = { "/get/{imageName}" })
    public ImageModel getImage(@PathVariable("imageName") String imageName) throws IOException {

        final Optional<ImageModel> retrievedImage = imageRepository.findByName(imageName);
        ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
                decompressBytes(retrievedImage.get().getPic()));
        return img;
    }
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }*/

}
