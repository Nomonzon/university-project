package com.example.universityproject.repository;


import com.example.universityproject.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {
    boolean existsByTitle(String title);
    boolean existsByTitleAndQuesIdNot(String title, Long quesId);

}