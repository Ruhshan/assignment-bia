package xyz.ruhshan.biaassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.ruhshan.biaassignment.entity.StoredImage;

public interface StoreImageRepository extends JpaRepository<StoredImage, String> {
}
