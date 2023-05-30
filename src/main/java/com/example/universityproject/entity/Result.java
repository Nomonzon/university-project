package com.example.universityproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@Entity
@Table(name = "results")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // имя пользователя
    private String username;

    // электронная почта пользователя
    private String email;

    // Количество правильных ответов
    private int totalCorrect = 0;

}
