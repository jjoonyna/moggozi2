package com.choongang.moggozi2.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="commonboard")
public class CommonBoard{
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name="boardno")
	    private Integer boardNo;

	    @Column(name = "usernick")
	    private String	usernick;

	    @ManyToOne
	    @JoinColumn(name="usernick",insertable = false, updatable = false)
	    private User user;
	    @Column(name="category")
		private String  category;
	    @Column(name="boardsubject")
	    private String  boardSubject;
	    @Column(name="boardcontent")
	    private String  boardContent;
		@CreationTimestamp
		@Column(name="boarddate")
		private Timestamp boardDate;
		@Column(name="boardcnt")
		private Integer boardCnt = 0;
		@Column(name="boardfile")
		private String  boardFile;
		@Column(name="boardstate")
		private String  boardState = "y";
		@Column(name="boardre")
		private Integer boardRe = 0;
	    
}