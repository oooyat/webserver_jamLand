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
	 
	
	

	private Jkeyword()
	{
		
	}
	
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

	

	@Override
	public String toString() {
		return ko;
	}
	
}
