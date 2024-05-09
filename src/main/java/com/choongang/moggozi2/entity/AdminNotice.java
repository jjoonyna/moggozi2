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
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="adminnotice")
public class AdminNotice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="notino")
	private Integer notiNo;		// 글번호
	
	@Column(name="category")
	private String category;		// 카테고리(n = 게시판 / a = 1:1문의)
	
	@ManyToOne
    @JoinColumn(name = "usernick", referencedColumnName = "usernick", insertable = false, updatable = false)
	private User user;
	@Column(name="username")
	private String username;		// 아이디
	@Column(name="usernick")
	private String usernick;		// 닉네임
	@Column(name="notititle")
	private String notiTitle;		// 제목명 
	@Column(name="notiimpt")
	private String notiImpt;		// 중요표시
	@Column(name="noticontent")
	private String notiContent;	// 내용
	@Column(name="notidate")
	private Timestamp notiDate;	// 게시 날짜
	@Column(name="notihit")
	private Integer notiHit;	// 조회수
	@Column(name="notiat")
	private String notiAt;// 게시판 상태("미답변","답변중","완료","보류")

    // 기본 생성자
//    public AdminNotice() {
//        // 기본 생성자 로직
//    }
	
	
//	  // 매개변수를 받는 생성자
//    public AdminNotice(String category, String notiWriter, String notiTitle, String notiImpt,
//            String notiContent, String notiAt, LocalDateTime notiDate) {
//        this.category = category;
//        this.notiWriter = notiWriter;
//        this.notiTitle = notiTitle;
//        this.notiImpt = notiImpt;
//        this.notiContent = notiContent;
//        this.notiHit = 0;
//        this.notiAt = notiAt;
//        this.notiDate = notiDate; // 현재 시간으로 설정
//    }
//
//
//	public AdminNotice(String category, String notiWriter, String notiTitle, String notiContent, String notiAt, LocalDateTime notiDate ) {
//		this.category = "a";
//		this.notiWriter = notiWriter;
//		this.notiTitle = notiTitle;
//		this.notiContent = notiContent;
//		this.notiAt = notiAt;
//		this.notiDate = notiDate;
//	}


}
