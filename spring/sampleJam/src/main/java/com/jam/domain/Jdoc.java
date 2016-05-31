package com.jam.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Jdoc {
	
	@Id
	@GeneratedValue
	private int docId;
	
	private String title;
	private String subtitle;
	private String ytb;
	private String context1;
	private String context2;
	private int rcmd;
	private int	rprt;
	private String userId;
	private Date mdfyDate;
	
	@OneToMany
	private List<JsaveDoc> myJusers;
	
	@OneToMany
	private List<JdocRelation> myRelations;
		
	private Jdoc()
	{
		
	}
	
	public int getJdoc_id() {
		return docId;
	}

	public void setJdoc_id(int jdoc_id) {
		this.docId = jdoc_id;
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

	public String getYtb_id() {
		return ytb;
	}

	public void setYtb_id(String ytb_id) {
		this.ytb = ytb_id;
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

	public String getUser_id() {
		return userId;
	}

	public void setUser_id(String user_id) {
		this.userId = user_id;
	}

	public Date getMdfy_date() {
		return mdfyDate;
	}

	public void setMdfy_date(Date mdfy_date) {
		this.mdfyDate = mdfy_date;
	}

	public List<JsaveDoc> getMyJusers() {
		return myJusers;
	}

	public void setMyJusers(List<JsaveDoc> myJusers) {
		this.myJusers = myJusers;
	}

	public List<JdocRelation> getMyRelations() {
		return myRelations;
	}

	public void setMyRelations(List<JdocRelation> myRelations) {
		this.myRelations = myRelations;
	}

	@Override
	public String toString() {
		return title;
	}
}
