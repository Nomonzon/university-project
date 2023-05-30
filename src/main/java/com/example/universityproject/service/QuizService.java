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

    /*
    getQuestions() - получает список всех вопросов из репозитория QuestionRepo с помощью метода findAll(). Затем случайным образом выбирает 10 вопросов из общего списка и сохраняет их в объекте QuestionForm, который затем возвращается в контроллер.
     */
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

    /*
    getResult() - вычисляет общее количество правильных ответов на основе переданной QuestionForm. Проходит по каждому вопросу в QuestionForm, сравнивает выбранный ответ (getChose()) с правильным ответом (getAns()) и увеличивает счетчик correct, если ответы совпадают. Возвращает общее количество правильных ответов.
     */
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

    /*
    saveScore() - сохраняет результаты тестирования в репозитории ResultRepo. Создает новый объект Result на основе переданного результата и сохраняет его в базе данных.
     */
    public void saveScore(Result result) {
        Result saveResult = new Result();
        saveResult.setUsername(result.getUsername());
        saveResult.setTotalCorrect(result.getTotalCorrect());
        rRepo.save(saveResult);
    }

    /*
    getTopScore() - получает список лучших результатов из репозитория ResultRepo с помощью метода findAll() и сортирует их в порядке убывания количества правильных ответов с помощью Sort.by(). Возвращает отсортированный список результатов.
     */
    public List<Result> getTopScore() {
        return rRepo.findAll(Sort.by(Sort.Direction.DESC, "totalCorrect"));
    }
}
