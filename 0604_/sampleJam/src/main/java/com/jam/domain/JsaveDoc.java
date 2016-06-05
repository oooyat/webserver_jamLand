package com.jam.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "JsaveDoc")
public class JsaveDoc {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String userId;
	private int docId;
	
	
	/*
	 * @ManyToOne
	@JoinColumn(name = "id")
	private Juser juser;
	
	@ManyToOne
	@JoinColumn(name = "docId")
	private Jdoc jdoc;
	 */
	
	
	private JsaveDoc()
	{
		
	}
	
	public String getUser_id() {
		return userId;
	}
	public void setUser_id(String user_id) {
		this.userId = user_id;
	}
	public int getDoc_id() {
		return docId;
	}
	public void setDoc_id(int doc_id) {
		this.docId = doc_id;
	}

	
}
