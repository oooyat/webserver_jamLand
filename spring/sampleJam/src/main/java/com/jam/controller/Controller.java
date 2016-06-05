package com.jam.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jam.domain.Jdoc;
import com.jam.domain.Jdocrelation;
import com.jam.domain.Jkeyword;
import com.jam.domain.JkeywordList;
import com.jam.domain.Jsavedoc;
import com.jam.domain.Juser;
import com.jam.repository.JkeywordRepository;
import com.jam.repository.JsavedocRepository;
import com.jam.repository.JuserRepository;
import com.jam.repository.JdocrelationRepository;
import com.jam.repository.Jdocrepository;

/*
 * 
 메인 – 키워드 목록
메인/keyword/키워드 – 키워드와 연관되어 있는 문서들 

메인/jdoc/문서id - 문서 내용	//0605수정. 

메인/emotion/joy || fear || ... ~감정순 문서

메일/juser -사용자 목록
메인/juser/사용자id – 사용자와 연관되어 있는 문서들

 * 
 * 
 */


@RestController
@RequestMapping("/")
public class Controller {
		
	@Autowired
	JkeywordRepository jkeywordRepository;
	
	@Autowired
	Jdocrepository jdocRepository;
	
	@Autowired
	JdocrelationRepository jdocrelationRepository;
	
	@Autowired
	JsavedocRepository jsavedocRepository;
	
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
	
	@RequestMapping("/keyword/{ko}")
	public List<Jdoc> keywordToDocList(@PathVariable String ko)
	{
		List<Jdocrelation> jrelations = jdocrelationRepository.findByKeyword(ko);
		List<String> docList = new ArrayList<String>();
		
		for (Jdocrelation rel : jrelations){
			docList.add(Integer.toString(rel.getDid()));
		}
		
		List<Jdoc> jdocList = new ArrayList<Jdoc>();
		for(int i=0; i< docList.size(); i++)
		{
			int index = Integer.parseInt(docList.get(i));
			Jdoc temp = jdocRepository.findByDid(index);
			jdocList.add(temp);
		}
		
		return jdocList;		
	}
	
	@RequestMapping("/jdoc/{did}")
	public Jdoc docDetail(@PathVariable String did)
	{
		Jdoc jdoc = jdocRepository.findByDid(Integer.parseInt(did));		
		return jdoc;		
	}
	
	@RequestMapping("/emotion/{et}")
	public List<Jdoc> emotionToDocList(@PathVariable String et)
	{
		List<Jdoc> alldocs = jdocRepository.findAll();
		
		List<Jkeyword> keywords = jkeywordRepository.findByJtypeOrderByScoreDesc(et); //키워드의 score가 큰 순서대로.
		double[] scores = new double[alldocs.size() + 1];	//문서의 개수만큼!
		for(int i=0; i< scores.length; i++) // score들 초기화.
		{
			scores[i] = 0;
		}
		
		/*****************************************************/
		for(int i=0; i < keywords.size() ; i++)
		{
			Jkeyword current_keyword = keywords.get(i);
			double current_score = current_keyword.getScore();
			List<Jdocrelation> current_relations 
						= jdocrelationRepository.findByKeyword(current_keyword.getKo());
			
			for(int j=0; j < current_relations.size() ; j++)
			{
				scores[current_relations.get(j).getDid()] = scores[current_relations.get(j).getDid()] + current_score;
			}
		}
		
//		for(int i=0; i < scores.length ; i++)
//		{
//			System.out.println("score : " + scores[i]);
//		}
		
		/*****************************************************/
		
		int[] top10List = new int[10];
		
		for(int i=0; i < top10List.length ; i++)
		{
			top10List[i] =0;	//초기호ㅏ
			double max = 0;
			int current_index=0;
			
			for(int j=0; j < scores.length ; j++)
			{
				if(scores[j] != 0)
				{												
					if(scores[j] > max)
					{
						max = scores[j];
						current_index = j;
						//System.out.println(j + "현재 j");
					}							
				}				
			}
			
			scores[current_index] = 0;
			top10List[i] = current_index;
			max = 0;
			current_index = 0;			
			//System.out.println(i + "번째 : " + top10List[i]);
		}
		/*****************************************************/			
		
		List<Jdoc> topdocs = new ArrayList<Jdoc>();
		for(int i=0; i < top10List.length; i++)
		if(top10List[i] != 0)
		{
			topdocs.add(jdocRepository.findByDid(top10List[i]));
		}
		
		return topdocs;
	}
	
	@RequestMapping("/juser")//forTest : no use
	public List<String> savedList() {

		List<Juser> jusers = juserRepository.findAll();
		List<String> userList = new ArrayList<String>();
		
		for (Juser juser : jusers){
			userList.add(juser.getId());
		}		
		return userList;
	}
	
	@RequestMapping("/juser/{uid}")
	public List<String> userToDocList(@PathVariable String uid)
	{
		List<Jsavedoc> jsavedocs = jsavedocRepository.findByUid(uid);
		List<String> savedocList = new ArrayList<String>();
		
		for (Jsavedoc savedoc : jsavedocs){
			savedocList.add(Integer.toString(savedoc.getDid()));
		}
		
		return savedocList;		
	}
	
	
	@RequestMapping("/jammenu")
	public String greeting(@RequestParam(value="name", required=false, defaultValue="World")String name, Model model)
	{
		Jdoc temp = new Jdoc();
		model.addAttribute("name", name);
	
		return "JAMLAND";
	}
	

}


/*

		//insert & update방법 id값으로 new인가 new가 아닌가 확인 후 결정!
//		Jkeyword temp2 = new Jkeyword();
//		temp2.setKo("일상");
//		temp2.setEn("ghost");
//		temp2.setType("anger");
//		temp2.setScore(0.65442);
//		
//		jkeywordRepository.save(temp2);


*/