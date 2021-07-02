package com.mingo.demo.models;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;

    @Column
    private Timestamp sent;

    @Column
    private String body;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private MessageStatus status;
}
