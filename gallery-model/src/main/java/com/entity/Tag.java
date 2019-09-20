package com.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "TAG")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "TAG_ID",unique=true, nullable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Image> files = new HashSet<>();

    public Tag() {
    }

}
