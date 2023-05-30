package com.example.universityproject.service;


import com.example.universityproject.entity.Question;
import com.example.universityproject.entity.QuestionForm;
import com.example.universityproject.entity.Result;
import com.example.universityproject.repository.QuestionRepo;
import com.example.universityproject.repository.ResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuizService {


    @Autowired
    QuestionForm qForm;
    @Autowired
    QuestionRepo qRepo;
    @Autowired
    Result result;
    @Autowired
    ResultRepo rRepo;

    public QuestionForm getQuestions() {
        List<Question> allQues = qRepo.findAll();
        List<Question> qList = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int rand = random.nextInt(allQues.size());
            qList.add(allQues.get(rand));
            allQues.remove(rand);
        }
        qForm.setQuestions(qList);
        return qForm;
    }

    public int getResult(QuestionForm qForm) {
        int correct = 0;
        for (Question q : qForm.getQuestions()) {
            System.out.println(q.getChose());
            System.out.println(q.getAns());
            if (q.getAns() == q.getChose())
                correct++;
        }
        return correct;
    }

    public void saveScore(Result result) {
        Result saveResult = new Result();
        saveResult.setUsername(result.getUsername());
        saveResult.setTotalCorrect(result.getTotalCorrect());
        rRepo.save(saveResult);
    }

    public List<Result> getTopScore() {
        return rRepo.findAll(Sort.by(Sort.Direction.DESC, "totalCorrect"));
    }
}
