package com.choongang.moggozi2.model;

import java.sql.Timestamp;


import lombok.Data;

@Data
public class Mokkozi {
	private Integer mokkojiNo;
    private String usernick;
    private String mokkojiTitle;
    private Integer mokkojiPerson;
    private Integer mokkojiHits;
    private Timestamp mokkojiDate;
    private String mokkojiIntro;
    private String mokkojiImages;
    private String mokkojiState;
    private String mokkojiCategory;
}