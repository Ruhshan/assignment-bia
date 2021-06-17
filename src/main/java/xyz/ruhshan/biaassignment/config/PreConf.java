package xyz.ruhshan.biaassignment.config;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import xyz.ruhshan.biaassignment.entity.StoredImage;
import xyz.ruhshan.biaassignment.repository.StoredImageRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
@Slf4j
public class PreConf {
    private final StoredImageRepository storeImageRepository;
    private final Resource testDataResource;
    private final Gson gson;
    private final Type storedImageListType;

    public PreConf(StoredImageRepository storeImageRepository, @Value("classpath:testData.json") Resource testDataResource, Gson gson) {
        this.storeImageRepository = storeImageRepository;
        this.testDataResource = testDataResource;
        this.gson = gson;
        this.storedImageListType = new TypeToken<ArrayList<StoredImage>>(){}.getType();
        this.loadTestData();
    }

    private void loadTestData() {

        if(storeImageRepository.count() == 0){

            getTestJsonData().ifPresent((jsonData)->{
                List<StoredImage> storedImageList = gson.fromJson(jsonData, storedImageListType);
                insertInDb(storedImageList);

            });

        }

    }

    private void insertInDb(List<StoredImage> storedImageList){
        storeImageRepository.saveAll(storedImageList);
    }

    private Optional<String> getTestJsonData() {
        try {
            InputStream inputStream = testDataResource.getInputStream();

            String jsonData =  new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines()
                    .collect(Collectors.joining("\n"));

            return Optional.of(jsonData);

        } catch (IOException e) {
            log.error("Unable to read json data from resource {}", e.getMessage());

        }

        return Optional.empty();

    }
}
