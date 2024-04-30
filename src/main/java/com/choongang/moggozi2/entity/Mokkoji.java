package com.choongang.moggozi2.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

 
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Mokkoji {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mokkoji_no;
    private String user_nickname;
    private String mokkoji_title;
    private Integer mokkoji_person;
    private Integer mokkoji_hits = 0;
    private LocalDateTime mokkoji_date = LocalDateTime.now();
    private String mokkoji_intro;
    private String mokkoji_images;
    private String mokkoji_state = "y";
    private String mokkoji_category;

    public Mokkoji(String user_nickname, String mokkoji_title, Integer mokkoji_person,
                 String mokkoji_intro, String mokkoji_images, String mokkoji_category) {
        this.user_nickname = user_nickname;
        this.mokkoji_title = mokkoji_title;
        this.mokkoji_person = mokkoji_person;
        this.mokkoji_intro = mokkoji_intro;
        this.mokkoji_images = mokkoji_images;
        this.mokkoji_category = mokkoji_category;
    }
}