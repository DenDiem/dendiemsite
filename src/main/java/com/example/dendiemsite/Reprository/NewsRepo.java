package com.example.dendiemsite.Reprository;


import com.example.dendiemsite.Entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepo extends JpaRepository<NewsEntity, Integer>
{
    // List<BookEntity> findAllWhereIsbn(String contains);
    //Optional<BookEntity> findByIsbn(String isbn);
    // boolean existsByIsbn(String isbn);
    //List<BookEntity> findAllWhereIsbnLikeOrTitleLike(String isbn,String title);
    //List<BookEntity> findAllByIsbnLikeOrTitleLike(String isbn, String title);
}