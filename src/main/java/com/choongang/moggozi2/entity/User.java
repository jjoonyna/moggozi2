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
	@Table(name="user")
	public class User {
			
			@Column(name="username")
			private String username;		// 아이디
			
			@Id
			@Column(name = "usernick") // 컬럼 이름을 명시적으로 지정
			private String usernick;	//닉네임
			@Column(name="normalname")
			private String normalname;	//이름 
			@Column(name="password")
			private String password;		// 비밀번호 
			@Column(name="myid")
			private String myId; // 새로운 속성 추가
			@Column(name="userph")
			private String userph;	// 휴대폰번호
			@Column(name="userzip")
			private String userzip;	// 우편번호
			@Column(name="useryear")
			private String useryear;	//출생년동 
			@Column(name="useraddress1")
			private String useraddress1;	// 기본주소
			@Column(name="useraddress2")
			private String useraddress2;	// 상세주소
			@Column(name="useremail")
			private String useremail;	// 이메일
			@Column(name="usergender")
			private String usergender;	//남/녀 
			@Column(name="role")
			private String role;
}
