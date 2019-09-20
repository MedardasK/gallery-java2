package com.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "IMAGE_FULL")
public class ImageFull {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IMAGE_FULL_ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "DATA")
    @Lob
    private byte[] data;

}
