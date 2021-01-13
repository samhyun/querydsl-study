package com.example.querydsl.study.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class TestEntity {

    @Id
    private long id;
}
