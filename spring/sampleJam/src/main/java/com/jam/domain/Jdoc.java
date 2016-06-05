package com.jam.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Jdoc {
	
	@Id
	@GeneratedValue
	private int did;
	
	private String title;
	private String subtitle;
	private String ytb;
	private String context1;
	private String context2;
	private int rcmd;
	private int	rprt;
	private String uid;
	private Date mdfydate;
	
	
	@OneToMany( mappedBy = "jdoc")
	private List<Jsavedoc> jsavedocs;

	@OneToMany( mappedBy = "jdoc")
	private List<Jdocrelation> jdocrelations;
		
	public Jdoc()
	{
		
	}
	
	

	public int getDid() {
		return did;
	}



	public void setDid(int did) {
		this.did = did;
	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getYtbId() {
		return ytb;
	}

	public void setYtbId(String ytbId) {
		this.ytb = ytbId;
	}

	public String getContext1() {
		return context1;
	}

	public void setContext1(String context1) {
		this.context1 = context1;
	}

	public String getContext2() {
		return context2;
	}

	public void setContext2(String context2) {
		this.context2 = context2;
	}

	public int getRcmd() {
		return rcmd;
	}

	public void setRcmd(int rcmd) {
		this.rcmd = rcmd;
	}

	public int getRprt() {
		return rprt;
	}

	public void setRprt(int rprt) {
		this.rprt = rprt;
	}

	

	public String getUid() {
		return uid;
	}



	public void setUid(String uid) {
		this.uid = uid;
	}



	

	

	public Date getMdfydate() {
		return mdfydate;
	}



	public void setMdfydate(Date mdfydate) {
		this.mdfydate = mdfydate;
	}

	@Override
	public String toString() {
		return title;
	}
	
}
