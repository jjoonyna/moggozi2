package com.choongang.moggozi2.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Admin {
	

    @Id
    private String username;
    
    private String usernick;
    
    private String password;

    private String role;
}