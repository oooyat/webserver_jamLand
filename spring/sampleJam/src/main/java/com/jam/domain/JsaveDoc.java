package com.jam.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class JsaveDoc {
	private String user_id;
	private String doc_id;
	
	@ManyToOne
	private Juser juser;
	
	@ManyToOne
	private Jdoc jdoc;
	
	private JsaveDoc()
	{
		
	}
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(String doc_id) {
		this.doc_id = doc_id;
	}

	public Juser getJuser() {
		return juser;
	}

	public void setJuser(Juser juser) {
		this.juser = juser;
	}

	public Jdoc getJdoc() {
		return jdoc;
	}

	public void setJdoc(Jdoc jdoc) {
		this.jdoc = jdoc;
	}
}
