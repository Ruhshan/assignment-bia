package xyz.ruhshan.biaassignment.controller;

import org.springframework.web.bind.annotation.*;
import xyz.ruhshan.biaassignment.entity.StoredImage;
import xyz.ruhshan.biaassignment.service.StoredImageService;

import java.util.List;

@RestController
public class StoredImageController {
    private final StoredImageService storedImageService;

    public StoredImageController(StoredImageService storedImageService) {
        this.storedImageService = storedImageService;
    }

    @GetMapping("/images")
    public List<String> findAllAccessionIds(){
        return storedImageService.findAllAccessionIds();
    }

    @PostMapping("/images")
    public void save(@RequestBody StoredImage storedImage){
        storedImageService.save(storedImage);
    }

    @GetMapping("/accessions/{accessionID}/metadata")
    public StoredImage findMetaData(@PathVariable("accessionID") String accessionId){
        return storedImageService.findById(accessionId);
    }

    @GetMapping("/accessions/{accessionID}/imagesize")
    public Double findImageSize(@PathVariable("accessionID") String accessionId){
        return storedImageService.findImageSize(accessionId);
    }
}
