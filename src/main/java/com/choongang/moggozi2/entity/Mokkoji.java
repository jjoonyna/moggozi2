package com.choongang.moggozi2.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

 
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="mokkoji")
public class Mokkoji {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mokkojino")
    private Integer mokkojiNo;
    
    @Column(name = "usernick")
	private String usernick;
    
    
    @ManyToOne
    @JoinColumn(name="usernick",insertable = false, updatable = false)
    private User user;
	
    @Column(name = "mokkojititle", unique=true)
    private String mokkojiTitle;
    @Column(name="mokkojiperson")
    private Integer mokkojiPerson;
    @Column(name="mokkojihits")
    private Integer mokkojiHits = 0;
    @Column(name="mokkojidate")
    private Timestamp mokkojiDate;
    @Column(name="mokkojiintro")
    private String mokkojiIntro;
    @Column(name="mokkojiimages")
    private String mokkojiImages;
    @Column(name="mokkojistate")
    private String mokkojiState = "y";
    
    @Column(name = "mokkojicategory")
    private String mokkojiCategory;
    
    public Mokkoji(String usernick, String mokkojiTitle, Integer mokkojiPerson,
                 String mokkojiIntro, String mokkojiImages, String mokkojiCategory) {
        this.usernick = usernick;
        this.mokkojiTitle = mokkojiTitle;
        this.mokkojiPerson = mokkojiPerson;
        this.mokkojiIntro = mokkojiIntro;
        this.mokkojiImages = mokkojiImages;
        this.mokkojiCategory = mokkojiCategory;
    }
    
 

}