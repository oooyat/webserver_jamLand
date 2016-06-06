package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Jsavedoc")
public class Jsavedoc {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String uid;
	private int did;
	
	
	@ManyToOne
	private Juser juser;
//	
	@ManyToOne
	private Jdoc jdoc;
		
	private Jsavedoc()
	{
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	

	
}
