package com.choongang.moggozi2.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class CommonBoard{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardNo;

    @Column(name = "usernick")
    private String	username;
    
    @ManyToOne
    @JoinColumn(name="usernick",insertable = false, updatable = false)
    private User user;
    
	private String  category;
	private String  boardSubject;
	private String  boardContent;
	
	@CreationTimestamp
	private Timestamp boardDate;
	
	private Integer boardCnt = 0;
	private String  boardFile;
    private String  boardState = "y";
	private Integer boardRe = 0;
}