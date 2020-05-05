package com.example.dendiemsite.Entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "comment_table")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CommentEntity
{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String commentMessage;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_table_id")
    private UserEntity author;


    private Integer newsId;

    public Integer getNewsId() {
        if(newsId==null) return  0;
        return newsId;
    }

    public CommentEntity(String message, UserEntity user, Integer news) {
        this.commentMessage = message;
        this.author = user;
        this.newsId = news;
    }
}