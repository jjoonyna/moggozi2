package com.choongang.moggozi2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.TypeAlias;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


	@Setter
	@Getter
	@Entity
	public class User {
			
			private String username;		// 아이디
			
			@Id
			@Column(name = "usernick") // 컬럼 이름을 명시적으로 지정
			private String usernick;	//닉네임
			
			private String normalname;	//이름 
			private String password;		// 비밀번호 
			
			private String myId; // 새로운 속성 추가
			
			private String userph;	// 휴대폰번호
			private String userzip;	// 우편번호
			private String useryear;	//출생년동 
			private String useraddress1;	// 기본주소
			private String useraddress2;	// 상세주소
			private String useremail;	// 이메일
			private String usergender;	//남/녀 
			private String role;
}
