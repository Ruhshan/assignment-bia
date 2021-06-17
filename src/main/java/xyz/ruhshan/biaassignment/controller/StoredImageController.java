package xyz.ruhshan.biaassignment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/accessions/{accessionID}/metadata")
    public StoredImage findMetaData(@PathVariable("accessionID") String accessionId){
        return storedImageService.findById(accessionId);
    }

    @GetMapping("/accessions/{accessionID}/imagesize")
    public Double findImageSize(@PathVariable("accessionID") String accessionId){
        return storedImageService.findImageSize(accessionId);
    }
}
