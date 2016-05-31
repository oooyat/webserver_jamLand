package com.jam.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jam.domain.Jkeyword;
import com.jam.repository.*;

@RestController
@RequestMapping("/")
public class Controller {
	
	@Autowired
	JdocRepository jdocrepository;
	
	@Autowired
	JdocRelationRepository jdocrelationrepository;
	
	@Autowired
	JkeywordRepository jkeywordrepository;
	
	@Autowired
	JsaveDocRepository jsavedocrepository;
	
	@Autowired
	JuserRepository juserrepository;
	
	@RequestMapping("/")
	public ArrayList<Jkeyword> list() {

		List<Jkeyword> keywords = jkeywordrepository.findAll();
		ArrayList<Jkeyword> keywordList = new ArrayList<Jkeyword>();
		
		for (Jkeyword keyword : keywords){
			//keywordList.addRestaurantList(keyword.getKo());
			keywordList.add(keyword);
		}
		
		return keywordList;
	}

}
