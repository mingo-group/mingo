package com.mingo.demo.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String details;

    @ManyToMany
    private List<User> users;

    @ManyToOne
    private Business business;


}