package com.mingo.demo.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String interest;

    @ManyToMany
    private List<Category> categories;

}
