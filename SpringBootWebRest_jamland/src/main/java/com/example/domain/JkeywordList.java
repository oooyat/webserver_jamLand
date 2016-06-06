package com.example.domain;

import java.util.ArrayList;
import java.util.List;


public class JkeywordList {
	private List<String> keywordList;
	
	public JkeywordList()
	{
		keywordList = new ArrayList<String>();
	}
	
	public void addJkeywordList(String ko)
	{
		keywordList.add(ko);
	}

	public List<String> getKeywordList() {
		return keywordList;
	}

	public void setKeywordList(List<String> keywordList) {
		this.keywordList = keywordList;
	}

}
