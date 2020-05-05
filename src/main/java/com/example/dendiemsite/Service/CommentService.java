package com.example.dendiemsite.Service;

import com.example.dendiemsite.Entity.CommentEntity;
import com.example.dendiemsite.Entity.NewsEntity;
import com.example.dendiemsite.Entity.UserEntity;
import com.example.dendiemsite.Model.NewsDTO;
import com.example.dendiemsite.Reprository.CommentRepo;
import com.example.dendiemsite.Reprository.NewsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepo repo;
    //private final EntityManager entityManager;
    @Transactional
    public CommentEntity createComment(String message, UserEntity user, Integer newsEntity) {
        CommentEntity comm = new CommentEntity(message,user,newsEntity);

        return repo.saveAndFlush(comm);

    }
    @Transactional
    public List<CommentEntity> findAllComment() {
        //return entityManager.createQuery("FROM  BookEntity ", BookEntity.class).getResultList();
        return repo.findAll();
    }
    @Transactional
    public CommentEntity findByID(int id) {

        Optional<CommentEntity> optionalUser = repo.findById(id);
        return optionalUser.orElse(null);
    }

   /* public List<NewsEntity> findAllWhereIsbnLikeOrTitleLike(String isbn, String title) {
        return  bookRepository.findAllByIsbnLikeOrTitleLike('%' + isbn + '%', '%' + title + '%');
    }*/
}