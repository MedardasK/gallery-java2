//package com.entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.Collection;
//
//
//@Setter
//@NoArgsConstructor
//@Entity
//@Table(name = "ROLE")
//public class Role {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Basic(optional = false)
//    @Column(name = "ROLE_ID",unique=true, nullable = false)
//    private Long id;
//
//    private String name;
//
//    @ManyToMany(mappedBy = "roles")
//    private Collection<User> users;
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    @JsonBackReference
//    public Collection<User> getUsers() {
//        return users;
//    }
//
//}
