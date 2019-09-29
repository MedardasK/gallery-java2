package com.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@NoArgsConstructor
@Entity
@Table(name = "IMAGE_FULL")
public class ImageFull {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IMAGE_FULL_ID", updatable = false, nullable = false)
    private Long id;


    @Lob
    private byte[] data;

    @OneToOne(mappedBy = "imageFull")
    private Image image;

    public Long getId() {
        return id;
    }

    public byte[] getData() {
        return data;
    }

    @JsonBackReference
    public Image getImage() {
        return image;
    }
}
