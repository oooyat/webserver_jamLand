package com.jam.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jam.domain.Jdoc;
import com.jam.domain.JdocRelation;
import com.jam.domain.Jkeyword;
import com.jam.domain.JkeywordList;
import com.jam.domain.JsaveDoc;
import com.jam.domain.Juser;
import com.jam.repository.JkeywordRepository;
import com.jam.repository.JsaveDocRepository;
import com.jam.repository.JuserRepository;
import com.jam.repository.JdocRelationRepository;
import com.jam.repository.JdocRepository;

/*
 * 
 메인 – 키워드 목록
메인/키워드 – 키워드와 연관되어 있는 문서들 
메인/키워드/문서id - 문서 내용

메일/juser -사용자 목록
메인/juser/사용자id – 사용자와 연관되어 있는 문서들
메인/juser/사용자id/문서id– 문서 내용
 * 
 * 
 */


@RestController
@RequestMapping("/")
public class Controller {
		
	@Autowired
	JkeywordRepository jkeywordRepository;
	
	@Autowired
	JdocRepository jdocRepository;
	
	@Autowired
	JdocRelationRepository jdocRelationRepository;
	
	@Autowired
	JsaveDocRepository jsaveDocRepository;
	
	@Autowired
	JuserRepository juserRepository;
	
	@RequestMapping("/")
	public JkeywordList keywordList() {

		List<Jkeyword> jkeywords = jkeywordRepository.findAll();
		JkeywordList jkeywordList = new JkeywordList();
		
		for (Jkeyword jkeyword : jkeywords){
			jkeywordList.addJkeywordList(jkeyword.getKo());
		}
		
		return jkeywordList;
	}
	
	@RequestMapping("/{ko}")
	public List<String> keywordToDocList(@PathVariable String ko)
	{
		List<JdocRelation> jrelations = jdocRelationRepository.findByKeywordKo(ko);
		List<String> docList = new ArrayList<String>();
		
		for (JdocRelation rel : jrelations){
			docList.add(Integer.toString(rel.getDocId()));
		}
		
		return docList;
		
	}
	
	@RequestMapping("/juser")//forTest
	public List<String> savedList() {

		List<Juser> jusers = juserRepository.findAll();
		List<String> savedDocList = new ArrayList<String>();
		
		for (Juser juser : jusers){
			savedDocList.add(juser.getId());
		}
		
		return savedDocList;
	}
	
	
	

}
