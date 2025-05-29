package com.example.korea_sleepTech_springboot.entity.relation;

import jakarta.persistence.*;

// 1 대 N 관계: 하나가 여러 개를 참조
// EX) 사용자 한 명이 게시글 여러 개를 작성

// N 대 1 관계: 여러 개가 하나에 속함
// EX) 게시글 여러 개가 하나의 사용자를 가짐
@Entity
public class PostExample {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    // 여러 개의 게시글이 한 명의 사용자를 가질 수 있음
    // : 해당 필드는 FK 참조를 통해 값이 지정
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserExample userExample;
}

/*
    CREATE TABLE post_example (
        id BIGINT PRIMARY KEY AUTO_INCREMENT,
        title VARCHAR(100),
        user_id BIGINT,
        FOREIGN KEY (user_id) REFERENCES user_example(id)
    );
 */