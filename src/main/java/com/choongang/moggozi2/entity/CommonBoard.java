package com.choongang.moggozi2.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class CommonBoard{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardNo;
    private String	username;
	private String  category;
	private String  boardSubject;
	private String  boardContent;
	private Timestamp boardDate;
	private Integer boardCnt = 0;
	private String  boardFile;
    private String  boardState = "y";
	private Integer boardRe = 0;
}