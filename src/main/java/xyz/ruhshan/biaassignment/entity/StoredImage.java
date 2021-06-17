package xyz.ruhshan.biaassignment.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class StoredImage {
    @Id
    private String accessionId;
    private String author;
    private String species;
    private String tissue;
    private Double voxelSizeBytes;
    private Double[] dimensions;
}
