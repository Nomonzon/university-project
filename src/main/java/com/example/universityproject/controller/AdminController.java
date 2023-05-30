package com.example.universityproject.controller;

import com.example.universityproject.payload.MessageDto;
import com.example.universityproject.payload.QuestionDto;
import com.example.universityproject.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/admin")
    public String gerAdminPage() {
        return "admin/admin";
    }

    @GetMapping("/profile")
    public String getProfilePage() {
        return "admin/profile";
    }

    @GetMapping("/add_post")
    public String getAddPostPage(Model model) {
        model.addAttribute("questionDto", new QuestionDto());
        return "admin/add_post";
    }

    @PostMapping("/add_post")
    public String addPost(@ModelAttribute QuestionDto questionDto, Model model) {
        MessageDto messageDto = adminService.addQuestion(questionDto);
        if (!messageDto.isSuccess()) {
            model.addAttribute(messageDto.getMessage());
            return "admin/404";
        }
        return "redirect:/add_post";
    }

    @GetMapping("/list_of_post")
    public String getListOfPost(Model model) {
        model.addAttribute("questions", adminService.getListOf());
        return "admin/list_of_post";
    }

    @GetMapping("/About")
    public String getAbout() {
        return "admin/about";
    }

    @GetMapping("/contact_us")
    public String getContactUsPage() {
        return "admin/contact_us";
    }

    public static void main(String[] args) {
        String str = "abcxyz.xxyz";
        String[] split = str.split("\\.");
        if (split[1].startsWith("xyz")) {
            System.out.println(false);
        } else System.out.println(true);
    }


}
