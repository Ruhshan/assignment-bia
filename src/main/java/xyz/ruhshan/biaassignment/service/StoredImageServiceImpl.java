package xyz.ruhshan.biaassignment.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import xyz.ruhshan.biaassignment.entity.StoredImage;
import xyz.ruhshan.biaassignment.repository.StoredImageRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StoredImageServiceImpl implements StoredImageService {
    private final StoredImageRepository storedImageRepository;

    public StoredImageServiceImpl(StoredImageRepository storedImageRepository) {
        this.storedImageRepository = storedImageRepository;
    }

    @Override
    public List<String> findAllAccessionIds() {
        return storedImageRepository.findAll().stream().map(StoredImage::getAccessionID).collect(Collectors.toList());
    }

    @Override
    public StoredImage findById(String accessionID) {
        return storedImageRepository.findById(accessionID).orElseThrow(()->{
            log.error("Stored image with accessionID {} not found",accessionID);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Stored Image Not found");
        });
    }

    @Override
    public Double findImageSize(String accessionID) {
        StoredImage storedImage = findById(accessionID);
        return Arrays.stream(storedImage.getDimensions()).reduce(storedImage.getVoxel_size_bytes(),(a,b)->a*b);

    }

    @Override
    public void save(StoredImage storedImage) {
        storedImageRepository.save(storedImage);
    }
}
