package com.jam.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Jdir {
	
	@Id
	private int jdir_id;
	
	private String name;
	private Date create_date;
	private int upper_dir_id;
	private int user_id;
	
	@OneToMany
	private List<Jdir> dirs;
	
	@OneToMany
	private List<Jdoc> docs;
	

	private Jdir()
	{
		
	}
	
	public int getJdir_id() {
		return jdir_id;
	}

	public void setJdir_id(int jdir_id) {
		this.jdir_id = jdir_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public int getUpper_dir_id() {
		return upper_dir_id;
	}

	public void setUpper_dir_id(int upper_dir_id) {
		this.upper_dir_id = upper_dir_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public List<Jdir> getDirs() {
		return dirs;
	}

	public void setDirs(List<Jdir> dirs) {
		this.dirs = dirs;
	}

	public List<Jdoc> getDocs() {
		return docs;
	}

	public void setDocs(List<Jdoc> docs) {
		this.docs = docs;
	}

	@Override
	public String toString() {
		return name;
	}

}
