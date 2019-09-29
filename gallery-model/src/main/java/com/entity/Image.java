package com.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "IMAGE")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID",unique=true, nullable = false)
    private Long id;

    @Lob
    @NonNull
    private byte[] data;

    private String name;

    private String type;

    private Long size;

    private String description;

    private int height;

    private int width;

    private String date = new SimpleDateFormat("yyyy.MM.dd").format(new Date());

    @JsonManagedReference
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(name = "IMAGE_TAG",
            joinColumns = @JoinColumn(name = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TAG_ID"))
    private Set<Tag> tags = new HashSet<>();

    @JsonManagedReference
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(name = "IMAGE_CATEGORY",
            joinColumns = @JoinColumn(name = "ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID"))
    private Set<Category> categories = new HashSet<>();

    @JsonManagedReference
    @JoinColumn(name = "IMAGE_FULL_ID")
    @OneToOne(cascade = CascadeType.ALL)
    private ImageFull imageFull;

}