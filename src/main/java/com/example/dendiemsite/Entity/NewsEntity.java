package com.example.dendiemsite.Entity;

import com.example.dendiemsite.Model.NewsDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity

@Table(name = "news_table")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NewsEntity
{
    //public static final String FIND_BY_ID = "BookEntity.FIND_BY_ID";
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String newsTitle;
    private String newsMessage;
    private String newsDate;
    private String newsImage;

    @ManyToOne(fetch = FetchType.EAGER)

    @JoinColumn(name = "user_table_id")
    private UserEntity author;


    public NewsEntity(NewsDTO newsDTO, UserEntity ue) {
        this.newsTitle = newsDTO.getNewsTitle();
        this.newsMessage = newsDTO.getNewsMessage();
        this.newsDate = newsDTO.getNewsDate();
        this.newsImage = newsDTO.getNewsImage();
        this.author = ue;
    }
}