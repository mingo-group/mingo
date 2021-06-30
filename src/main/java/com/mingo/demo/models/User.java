package com.mingo.demo.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String avatar_path;

    @Column
    private Float latitude;

    @Column
    private Float longitude;

    @ManyToMany
    private List<Offer> offers;

}
