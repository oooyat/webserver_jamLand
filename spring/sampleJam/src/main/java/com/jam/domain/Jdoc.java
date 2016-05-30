package com.jam.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Jdoc {
	
	@Id
	private int jdoc_id;
	
	private String title;
	private String subtitle;
	private String ytb_id;
	private String context1;
	private String context2;
	private int rcmd;
	private int	rprt;
	private String user_id;
	private Date mdfy_date;
	private int dir_id;
	
	@OneToMany
	private List<JsaveDoc> myJusers;
	
	@OneToMany
	private List<JdocRelation> myRelations;
	
	@ManyToOne
	private Jdir jdir;
	
	private Jdoc()
	{
		
	}
	
	public int getJdoc_id() {
		return jdoc_id;
	}

	public void setJdoc_id(int jdoc_id) {
		this.jdoc_id = jdoc_id;
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
		return ytb_id;
	}

	public void setYtb_id(String ytb_id) {
		this.ytb_id = ytb_id;
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
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Date getMdfy_date() {
		return mdfy_date;
	}

	public void setMdfy_date(Date mdfy_date) {
		this.mdfy_date = mdfy_date;
	}

	public int getDir_id() {
		return dir_id;
	}

	public void setDir_id(int dir_id) {
		this.dir_id = dir_id;
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

	public Jdir getJdir() {
		return jdir;
	}

	public void setJdir(Jdir jdir) {
		this.jdir = jdir;
	}

	@Override
	public String toString() {
		return title;
	}
}
