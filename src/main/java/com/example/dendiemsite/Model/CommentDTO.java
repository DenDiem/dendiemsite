package com.example.dendiemsite.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentDTO {


    private String message;
    private String username;
    private Integer newsId;

}
