package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Jdocrelation {
	
	@Id
	@GeneratedValue
	private int id;
	
	private int did;
	private String keyword;
	
	@ManyToOne
	private Jdoc jdoc;
//
	@ManyToOne
	private Jkeyword jkeyword;
	

	private Jdocrelation()
	{
		
	}
	
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getDid() {
		return did;
	}



	public void setDid(int did) {
		this.did = did;
	}



	public String getKeyword() {
		return keyword;
	}



	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}



	

	
	
	
}
