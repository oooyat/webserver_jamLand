package com.jam.domain;

import javax.persistence.ManyToOne;

public class JdocRelation {
	private int doc_id;
	private int keyword_id;
	
	@ManyToOne
	private Jkeyword jkeyword;
	
	@ManyToOne
	private Jdoc jdoc;

	public int getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(int doc_id) {
		this.doc_id = doc_id;
	}

	public int getKeyword_id() {
		return keyword_id;
	}

	public void setKeyword_id(int keyword_id) {
		this.keyword_id = keyword_id;
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
