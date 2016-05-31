package com.jam.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class JdocRelation {
	private int docId;
	private String keywordKo;
	
	@ManyToOne
	private Jkeyword jkeyword;
	
	@ManyToOne
	private Jdoc jdoc;

	public int getDoc_id() {
		return docId;
	}

	public void setDoc_id(int doc_id) {
		this.docId = doc_id;
	}

	public String getKeyword_id() {
		return keywordKo;
	}

	public void setKeyword_id(String keywordKo) {
		this.keywordKo = keywordKo;
	}

	public Jkeyword getJkeyword() {
		return jkeyword;
	}

	public void setJkeyword(Jkeyword jkeyword) {
		this.jkeyword = jkeyword;
	}

	public Jdoc getJdoc() {
		return jdoc;
	}

	public void setJdoc(Jdoc jdoc) {
		this.jdoc = jdoc;
	}
	
	
}
