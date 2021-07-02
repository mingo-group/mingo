package com.mingo.demo.models;

import javax.persistence.*;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private Timestamp start;

    @Column
    private Timestamp end;

    @OneToOne
    private Business business;

    @OneToOne
    private User user;

    @OneToOne
    private Offer offer;

}
