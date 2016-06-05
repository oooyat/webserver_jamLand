package com.jam.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class JdocRelation {
	
	@Id
	@GeneratedValue
	private int id;
	
	private int docId;
	private String keywordKo;
	
	@ManyToOne
	private Jdoc jdoc;

	@ManyToOne
	private Jkeyword jkeyword;
	

	private JdocRelation()
	{
		
	}
	
	public int getDocId() {
		return docId;
	}

	public void setDocId(int docId) {
		this.docId = docId;
	}

	public String getKeywordKo() {
		return keywordKo;
	}

	public void setKeywordKo(String keywordKo) {
		this.keywordKo = keywordKo;
	}

	
	
	
}
