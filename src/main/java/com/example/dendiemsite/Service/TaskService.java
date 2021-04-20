package com.example.dendiemsite.Service;

import com.example.dendiemsite.Entity.NewsEntity;
import com.example.dendiemsite.Entity.TaskEntity;
import com.example.dendiemsite.Entity.UserEntity;
import com.example.dendiemsite.Model.NewsDTO;
import com.example.dendiemsite.Model.TaskDTO;
import com.example.dendiemsite.Reprository.NewsRepo;
import com.example.dendiemsite.Reprository.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepo repo;

    @Transactional
    public TaskEntity createTask(TaskEntity t) {
        TaskEntity t1 = new TaskEntity(t);
        return repo.saveAndFlush(t);

    }

    @Transactional
    public List<TaskEntity> findAllNews() {

        return repo.findAll();
    }

    public TaskEntity createTask(TaskDTO taskModel, UserEntity user) {
        TaskEntity t1 = new TaskEntity(taskModel,user);
        return repo.saveAndFlush(t1);
    }
}