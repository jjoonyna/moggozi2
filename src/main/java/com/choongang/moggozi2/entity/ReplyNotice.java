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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ReplyNotice {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer replyNo;		// 답변번호
	
	@Column(name = "notiNo") // username 컬럼과 매핑
	private Integer notiNo;	// 글번호
	
	private String username;		// 아이디
	
    @Column(name = "usernick")
	private String usernick;	// 답변 작성자명
	
	private String replyContent;	// 답변 내용
	private Timestamp replyDate;	// 답변 날짜

	
	public ReplyNotice(Integer replyNo, Integer notiNo, String username, String usernick,
						String replyContent, Timestamp replyDate) {
		this.notiNo=notiNo;
		this.replyContent=replyContent;
		this.replyDate=replyDate;
		this.replyNo=replyNo;
		this.username=username;
		this.usernick=usernick;
	}
}
