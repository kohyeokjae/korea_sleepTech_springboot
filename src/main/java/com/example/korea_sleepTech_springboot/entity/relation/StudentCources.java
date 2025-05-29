package com.example.korea_sleepTech_springboot.entity.relation;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class StudentCources {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private LocalDateTime enrollmentDate;
    private int grade;
}

/*
    CREATE TABLE student_cources (
        id BIGINT PRIMARY KEY AUTO_INCREMENT,

        student_id BIGINT,
        course_id BIGINT,

        enrollment_date DATETIME,
        grade INT,

        FOREIGN KEY (student_id) REFERENCES student(id),
        FOREIGN KEY (course_id) REFERENCES course(id),
    );
 */