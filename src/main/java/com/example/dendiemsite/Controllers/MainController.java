package com.example.dendiemsite.Controllers;

import com.example.dendiemsite.Entity.UserEntity;
import com.example.dendiemsite.Model.Role;
import com.example.dendiemsite.Reprository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Map;
@RequiredArgsConstructor
@Controller
public class MainController {
    @Autowired
    private UserRepo userRepo;

    @RequestMapping({"/", ""})
    public String index() {
        return "redirect:news-list";
    }

    @GetMapping("/register")
    public String registration() {
        return "register";
    }

    @PostMapping("/register")
    public String addUser(UserEntity user, Map<String, Object> model) {
        UserEntity userFromDb = userRepo.findByUsername(user.getUsername());

        System.out.println(1);
        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "register";
        }

        user.setActive(true);
        if (!user.getUsername().equals("admin")) {
            user.setRoles(Collections.singleton(Role.USER));
        } else {
            user.setRoles(Collections.singleton(Role.ADMIN));
        }

        System.out.println(user);
        System.out.println(11);
        userRepo.save(user);

        return "redirect:/login";
    }
}
