package com.example.universityproject.service;

import com.example.universityproject.entity.Question;
import com.example.universityproject.payload.MessageDto;
import com.example.universityproject.payload.QuestionDto;
import com.example.universityproject.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AdminService {


    @Autowired
    QuestionRepo questionRepo;


    public List<Question> getListOf() {
        return questionRepo.findAll();
    }


    public MessageDto addQuestion(QuestionDto questionDto) {

        Question question = new Question();
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(questionDto.getFile().getOriginalFilename()));
        if (fileName.contains("..")) {
            try {
                question.setImage(Base64.getEncoder().encodeToString(questionDto.getFile().getBytes()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (questionRepo.existsByTitle(question.getTitle())) {
            return new MessageDto("Bunday savol alla qachon kiritilgan", false);
        }
        question.setOptionA(questionDto.getOptionA());
        question.setOptionB(questionDto.getOptionB());
        question.setOptionC(questionDto.getOptionC());
        question.setAns(questionDto.getAns());
        question.setTitle(questionDto.getTitle());
        questionRepo.save(question);
        return new MessageDto("Muvaffaqiyatli kiritildi", true);
    }

    public Question getQuestionById(Long id) {
        Optional<Question> question = questionRepo.findById(id);
        return question.orElse(null);
    }

    public MessageDto editQuestion(Question question, Long id) {
        if (questionRepo.existsByTitleAndQuesIdNot(question.getTitle(), id)) {
            return new MessageDto("Bunday savol ala qachon kiritilgan", false);
        }
        Question questionById = getQuestionById(id);
        if (questionById != null) {
            questionById.setTitle(question.getTitle());
            questionById.setOptionA(question.getOptionA());
            questionById.setOptionB(question.getOptionB());
            questionById.setOptionC(question.getOptionC());
            questionById.setAns(question.getAns());
            questionRepo.save(question);
            return new MessageDto("Muvaffaqiyatli o'zgartirildi", true);
        }
        return new MessageDto("Bu id ga tegishli savol topilmadi", false);
    }

    public MessageDto deleteQuestion(Long id) {
        questionRepo.deleteById(id);
        return new MessageDto("Muvaffaqiyatli o'zgartirildi", true);
    }
}
