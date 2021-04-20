package com.example.dendiemsite.Controllers;

import com.example.dendiemsite.Entity.CommentEntity;
import com.example.dendiemsite.Entity.NewsEntity;
import com.example.dendiemsite.Entity.TaskEntity;
import com.example.dendiemsite.Entity.UserEntity;
import com.example.dendiemsite.Model.NewsDTO;
import com.example.dendiemsite.Service.CommentService;
import com.example.dendiemsite.Service.NewsService;
import com.example.dendiemsite.Service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class NewsController {

    @Autowired
    private ApplicationContext appContext;

    @PostMapping(value = "/add-news")
    public String addNews(
            @AuthenticationPrincipal UserEntity user,
            @ModelAttribute NewsDTO newsModel, Model model) {


        model.addAttribute("newsModel",newsModel);

        NewsService newsService = appContext.getBean(NewsService.class);
        newsService.createNews(newsModel,user);


        System.out.println("News added");


        return "redirect:news-list";
    }
    @GetMapping(value = "/news-list")
    public String newsList(@AuthenticationPrincipal UserEntity u, Model model) {
        NewsService newsService = appContext.getBean(NewsService.class);
        model.addAttribute("allNews", newsService.findAllNews());
        return "dIndex";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping({"/add-news-page", ""})
    public String addNewsPage() {
        return "add-news-page";
    }

    @GetMapping("news/{id}")
    public String userEditForm(Model model, @PathVariable NewsEntity id){
        model.addAttribute("news",id);

        CommentService commentService = appContext.getBean(CommentService.class);
        List<CommentEntity> commentEntities = commentService.findAllComment();
        List<CommentEntity> result = new ArrayList<>();
        Integer myNewsId = id.getId();
        Integer currentNewsId;
        for (CommentEntity commentEntity : commentEntities
        ){
            currentNewsId = commentEntity.getNewsId();
            if (commentEntity.getNewsId().equals(id.getId())){
                result.add(commentEntity);
            }
        }
        model.addAttribute("comments",result);
        return "news-one-page";
    }
}