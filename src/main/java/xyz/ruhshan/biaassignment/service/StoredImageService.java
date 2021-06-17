package xyz.ruhshan.biaassignment.service;

import xyz.ruhshan.biaassignment.entity.StoredImage;

import java.util.List;

public interface StoredImageService {
    List<String> findAllAccessionIds();
    StoredImage findById(String accessionID);
}
