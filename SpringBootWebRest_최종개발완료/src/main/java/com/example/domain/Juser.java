package com.example.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.boot.orm.jpa.EntityScan;

@Entity
@Table(name = "Juser")
public class Juser {
	
	@Id
	private String id;
	
	//@Column(name = "pw")
	private String pw;
	
	//@Column(name = "name")
	private String name;
	
	//@Column(name = "mail")
	private String mail;
	
	
	@OneToMany(mappedBy = "juser")
	private List<Jsavedoc> jsavedocs;
	
	public Juser()
	{
		
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String main) {
		this.mail = main;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
