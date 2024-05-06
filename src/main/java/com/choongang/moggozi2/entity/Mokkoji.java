package com.choongang.moggozi2.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

 
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Mokkoji {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mokkojiNo;
    
	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "usernick", referencedColumnName = "usernick")
    private User usernick;
    
    @Column(name = "mokkojiTitle")
    private String mokkojiTitle;
    
    private Integer mokkojiPerson;
    private Integer mokkojiHits = 0;
    private Timestamp mokkojiDate;
    private String mokkojiIntro;
    private String mokkojiImages;
    private String mokkojiState = "y";
    
    @Column(name = "mokkojiCategory")
    private String mokkojiCategory;
    
    public Mokkoji(User usernick, String mokkojiTitle, Integer mokkojiPerson,
                 String mokkojiIntro, String mokkojiImages, String mokkojiCategory) {
        this.usernick = usernick;
        this.mokkojiTitle = mokkojiTitle;
        this.mokkojiPerson = mokkojiPerson;
        this.mokkojiIntro = mokkojiIntro;
        this.mokkojiImages = mokkojiImages;
        this.mokkojiCategory = mokkojiCategory;
    }
}