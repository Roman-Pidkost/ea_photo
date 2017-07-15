package eu.ea.photo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "mediumblob")
    private String photo;

    private int priority;

    private String tags;

    @ManyToOne
    private Category category;
}
