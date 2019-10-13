package com.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Entity
@NoArgsConstructor
@Table(name = "CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CATEGORY_ID", updatable = false, nullable = false)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    @Fetch(value= FetchMode.SELECT)
    private Set<Image> image = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @JsonBackReference
    public Set<Image> getImage() {
        return image;
    }

}
