package com.example.universityproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "questions")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quesId;

    private String title;

    private String optionA;

    private String optionB;

    private String optionC;

    @Column(nullable = true)
    private int ans;

    @Column(nullable = true)
    private int chose;
    @Lob
    @Column(columnDefinition = "BIGINT")
    private String image;
}
