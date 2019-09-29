package com.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@NoArgsConstructor
@Entity
@Table(name = "TAG")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "TAG_ID",unique=true, nullable = false)
    private Long id;

    private String name;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private Set<Image> files = new HashSet<>();

    @JsonBackReference
    public Set<Image> getFiles() {
        return files;
    }

}
