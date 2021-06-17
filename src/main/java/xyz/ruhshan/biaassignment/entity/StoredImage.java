package xyz.ruhshan.biaassignment.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoredImage {
    @Id
    @Column(unique=true)
    private String accessionID;
    private String author;
    private String species;
    private String tissue;
    private Double voxel_size_bytes;
    private Double[] dimensions;
}
