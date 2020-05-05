package com.example.dendiemsite.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewsDTO {

    private String newsTitle;
    private String newsMessage;
    private String newsDate;
    private String newsImage;
    private String newsUsername;

}
