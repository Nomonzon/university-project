package com.example.universityproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String gerAdminPage() {
        return "admin/admin";
    }

    @GetMapping("/profile")
    public String gerProfilePage() {
        return "admin/profile";
    }

    @GetMapping("/add_category")
    public String getAddCategoryPage() {
        return "admin/add_category";
    }

    @GetMapping("/add_post")
    public String getAddPostPage() {
        return "admin/add_post";
    }

    @GetMapping("/list_of_category")
    public String getListOfCategoryPage() {
        return "admin/list_of_category";
    }


    @GetMapping("/list_of_post")
    public String getListOfPost() {
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


}
