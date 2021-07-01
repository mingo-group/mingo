package com.mingo.demo.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private User sender;

    @OneToOne
    private User receiver;

//    @Column
//    private enum status

    @Column
    private Date timesent;

}