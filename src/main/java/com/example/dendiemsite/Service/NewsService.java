package com.example.dendiemsite.Service;

import com.example.dendiemsite.Entity.NewsEntity;
import com.example.dendiemsite.Entity.UserEntity;
import com.example.dendiemsite.Model.NewsDTO;
import com.example.dendiemsite.Reprository.NewsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepo repo;
    //private final EntityManager entityManager;
    @Transactional
    public NewsEntity createNews(NewsDTO newsDTO, UserEntity user) {
        NewsEntity news = new NewsEntity(newsDTO,user);

        return repo.saveAndFlush(news);

    }
    @Transactional
    public List<NewsEntity> findAllNews() {
        //return entityManager.createQuery("FROM  BookEntity ", BookEntity.class).getResultList();
        return repo.findAll();
    }
    @Transactional
    public NewsEntity findByID(int id) {

        Optional<NewsEntity> optionalUser = repo.findById(id);
        return optionalUser.orElse(null);
    }

   /* public List<NewsEntity> findAllWhereIsbnLikeOrTitleLike(String isbn, String title) {
        return  bookRepository.findAllByIsbnLikeOrTitleLike('%' + isbn + '%', '%' + title + '%');
    }*/
}