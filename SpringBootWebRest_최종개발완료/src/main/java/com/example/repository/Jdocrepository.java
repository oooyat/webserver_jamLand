package com.example.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.domain.Jdoc;
import com.example.domain.Jdocrelation;


public interface Jdocrepository extends CrudRepository<Jdoc, String>{
	
	List<Jdoc> findAll();		
	Jdoc findByDid(int did);
	
	//void insert(Jdoc jdoc);
	//void update(Jdoc jdoc);
	
	//List<Jdoc> findByUserId(String userId);
	//Like문 쓰는법..? select * from jdoc where title LIKE '%abcd%'
	//List<Jdoc> findByTitle(String title);
	//List<Jdoc> findBySubtitle(String subtitle);
	
	//dir 번호와 title로 찾기
	//List<Jdoc> findByJdirJdirIdAndTitle(String jdirId, String title);
	
	//List<Jdoc> findByTitleOrderByRcmdAsc(String title);
	//List<Jdoc> findByTitleOrderByRcmdDesc(String title);	
	//List<Jdoc> findByTitleOrderByRprtAsc(String title);
	//List<Jdoc> findByTitleOrderByRprtDesc(String title);
		
	//List<Jdoc> findByTitleOrderByMdfyDateAsc(String title);
	//List<Jdoc> findByTitleOrderByMdfyDateDesc(String title);
	
}
