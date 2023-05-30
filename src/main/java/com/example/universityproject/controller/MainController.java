package com.example.universityproject.controller;


import com.example.universityproject.entity.Question;
import com.example.universityproject.entity.QuestionForm;
import com.example.universityproject.entity.Result;
import com.example.universityproject.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    Result result;
    @Autowired
    QuizService quizService;

    Boolean submitted = false;

    @ModelAttribute("result")
    public Result getResult() {
        return result;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/sign")
    public String sign() {
        return "sign";
    }

    @PostMapping("/quiz")
    public String quiz(@RequestParam String username, Model model, RedirectAttributes redirectAttributes) {
        if (username.equals("")) {
            redirectAttributes.addFlashAttribute("warning", "You must enter your name");
            return "redirect:/";
        }
        submitted = false;
        result.setUsername(username);
        QuestionForm qForm = quizService.getQuestions();
        model.addAttribute("qForm", qForm);
        return "quiz";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute QuestionForm questionForm, Model model) {
        if (!submitted) {
            result.setTotalCorrect(quizService.getResult(questionForm));
            quizService.saveScore(result);
            submitted = true;
        }
        return "result";
    }

    @GetMapping("/score")
    public String score(Model model) {
        List<Result> scoreList = quizService.getTopScore();
        model.addAttribute("sList", scoreList);
        return "scoreboard";
    }

}
