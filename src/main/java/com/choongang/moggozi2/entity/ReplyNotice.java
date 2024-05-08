package com.choongang.moggozi2.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.choongang.moggozi2.entity.AdminNotice;
import com.choongang.moggozi2.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ReplyNotice {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer replyNo;		// 답변번호
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "notiNo",insertable = false, updatable = false) // username 컬럼과 매핑
	private AdminNotice adminNotice;	// 글번호
	
	@Column(name="notiNo")
	private Integer notiNo;
	
	private String username;		// 아이디
	
	@ManyToOne
    @JoinColumn(name = "usernick", referencedColumnName = "usernick", insertable = false, updatable = false)
	private User user;	// 답변 작성자명
	
	@Column(name="usernick")
	private String usernick;
	
	private String replyContent;	// 답변 내용
	private Timestamp replyDate;	// 답변 날짜
	

}
