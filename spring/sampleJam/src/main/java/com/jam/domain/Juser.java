package com.jam.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.boot.orm.jpa.EntityScan;

@Entity
public class Juser {
	
	@Id
	private int id;
	private String pw;
	private String name;
	private String main;
	
	@OneToMany
	private List<JsaveDoc> myJdocs;
	
	private Juser()
	{
		
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = main;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
