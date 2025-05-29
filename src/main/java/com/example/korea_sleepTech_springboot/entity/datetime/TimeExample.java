package com.example.korea_sleepTech_springboot.entity.datetime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class TimeExample extends BaseTimeEntity{
    @Id
    @GeneratedValue
    private Long id;
}
