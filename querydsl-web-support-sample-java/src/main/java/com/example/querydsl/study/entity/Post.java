package com.example.querydsl.study.entity;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    private String content;

    @ManyToOne(optional = false)
    private User writer;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date modifiedAt;
}

