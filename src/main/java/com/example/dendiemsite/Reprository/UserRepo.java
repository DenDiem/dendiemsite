package com.example.dendiemsite.Reprository;



import com.example.dendiemsite.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);
}