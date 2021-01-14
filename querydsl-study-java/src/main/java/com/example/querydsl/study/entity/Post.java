package com.example.querydsl.study.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @Generated
    private long id;

    private String title;

    private String comment;

    private Date createdAt;
}
