package com.example.dendiemsite.Controllers;

import com.example.dendiemsite.Entity.UserEntity;
import com.example.dendiemsite.Model.Role;
import com.example.dendiemsite.Model.RoleDTO;
import com.example.dendiemsite.Reprository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping
    public String userList(Model model){

        model.addAttribute("users",userRepo.findAll());
        return "user-list";
    }
    @GetMapping("{user}")
    public String userEditForm(@PathVariable UserEntity user,Model model){
        model.addAttribute("userEdit",user);
        List<RoleDTO> roleDTOS = new ArrayList<>();
        Role[] possibleValues = Role.values();

        for (Role role:possibleValues  ) {
            roleDTOS.add(new RoleDTO(role.getAuthority(),user.getRoles().contains(role)));

        }
        model.addAttribute("roles",roleDTOS);

        return "user-edit";
    }
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String,String> form,
            @RequestParam("userId") UserEntity user
    ){
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);
        return "redirect:/user";
    }
}
