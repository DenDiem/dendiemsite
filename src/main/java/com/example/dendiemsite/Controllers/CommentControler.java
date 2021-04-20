package com.example.dendiemsite.Controllers;


import com.example.dendiemsite.Entity.CommentEntity;
import com.example.dendiemsite.Entity.NewsEntity;
import com.example.dendiemsite.Entity.UserEntity;
import com.example.dendiemsite.Model.CommentDTO;
import com.example.dendiemsite.Model.NewsDTO;
import com.example.dendiemsite.Service.CommentService;
import com.example.dendiemsite.Service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class CommentControler {

    @Autowired
    private ApplicationContext appContext;

    /**
     *
     * @param user
     * @param commentEntity
     * @param model
     * @return
     */
    @PostMapping(value = "/add-comment")
    public String addComment(
            @AuthenticationPrincipal UserEntity user,
            @ModelAttribute CommentDTO commentEntity, Model model) {


        model.addAttribute("comment",commentEntity);

        CommentService commentService = appContext.getBean(CommentService.class);
        commentService.createComment(commentEntity.getMessage(),user,commentEntity.getNewsId());

        System.out.println("commentAdded added");


        return "redirect:news/"+ commentEntity.getNewsId();
    }
}