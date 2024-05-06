package com.choongang.moggozi2.entity;

import javax.persistence.Entity;
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
	
			@Id
			private String username;		// 아이디
			private String normalname;	//이름 
			private String usernick;	//닉네임
			private String password;		// 비밀번호 
			private String userph;	// 휴대폰번호
			private String userzip;	// 우편번호
			private String useryear;	//출생년동 
			private String useraddress1;	// 기본주소
			private String useraddress2;	// 상세주소
			private String useremail;	// 이메일
			private String usergender;	//남/녀 
			private String role;
}
