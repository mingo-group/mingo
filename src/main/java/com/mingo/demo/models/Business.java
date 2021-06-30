package com.mingo.demo.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private Float latitude;

    @Column
    private Float longitude;

    @Column
    private int businessType;

    @Column
    private String description;

    @OneToMany
    List<Offer> offers;

}
