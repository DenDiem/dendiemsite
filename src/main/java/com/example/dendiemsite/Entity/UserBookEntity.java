package com.example.dendiemsite.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
/*@NamedQueries(
        {
                @NamedQuery(query = "SELECT u FROM BookEntity u WHERE u.id = :id", name = BookEntity.FIND_BY_ID)
        })*/
@Table(name = "user_book_table")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserBookEntity
{
    //public static final String FIND_BY_ID = "BookEntity.FIND_BY_ID";
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String userid;
    private Integer bookid;


}