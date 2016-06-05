package com.jam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jam.domain.Jkeyword;

public interface JkeywordRepository extends CrudRepository<Jkeyword, String>{
	
	List<Jkeyword> findAll();
	
	Jkeyword findOne(String ko);//List<Jkeyword> findByKo(String ko);
		
	//List<Jkeyword> findByEn(String en);
	//List<Jkeyword> findByJtype(String jtype);
	
	//List<Jkeyword> findByJtypeAndScoreLessThan(String jtype, double score);
	//List<Jkeyword> findByJtypeAndScoreGreaterThan(String jtype, double score);

}
