package com.choongang.moggozi2.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ReplyNotice {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reply_no;		// 답변번호
	
	@ManyToOne
	private AdminNotice noti_no;	// 글번호
	
	@ManyToOne
	private Admin username;	// 답변 작성자명
	
	private String reply_content;	// 답변 내용
	private Timestamp reply_date;	// 답변 날짜

}
