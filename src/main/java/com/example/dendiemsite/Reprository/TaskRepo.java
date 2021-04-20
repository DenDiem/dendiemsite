package com.example.dendiemsite.Reprository;

import com.example.dendiemsite.Entity.TaskEntity;
import com.example.dendiemsite.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<TaskEntity, Integer> {

}