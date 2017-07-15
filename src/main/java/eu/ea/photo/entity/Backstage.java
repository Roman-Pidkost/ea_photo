package eu.ea.photo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Backstage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "mediumblob")
    private String mainPhoto;

    private String name;

    private int priority;

    @OneToMany(mappedBy = "backstage")
    private List<BackstagePhoto> backstagePhotos = new ArrayList<>();

}
