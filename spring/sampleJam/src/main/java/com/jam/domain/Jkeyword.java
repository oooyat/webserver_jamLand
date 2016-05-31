package com.jam.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Jkeyword {
	
	@Id	
	private String ko;
	
	private String en;
	private String jtype;
	private double score;
	
	@OneToMany
	private List<JdocRelation> docs;

	public String getKo() {
		return ko;
	}

	public void setKo(String ko) {
		this.ko = ko;
	}

	public String getEn() {
		return en;
	}

	public void setEn(String en) {
		this.en = en;
	}

	public String getType() {
		return jtype;
	}

	public void setType(String type) {
		this.jtype = type;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public List<JdocRelation> getDocs() {
		return docs;
	}

	public void setDocs(List<JdocRelation> docs) {
		this.docs = docs;
	}

	@Override
	public String toString() {
		return ko;
	}
	
}
