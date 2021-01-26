package com.example.querydsl.study.core.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class BaseDto {

    private Date createdAt;

    private Date modifiedAt;
}
