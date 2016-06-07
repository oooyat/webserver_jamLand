package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.domain.Jkeyword;

public interface JkeywordRepository extends CrudRepository<Jkeyword, String>{
	
	List<Jkeyword> findAll();
	
	Jkeyword findOne(String ko);//List<Jkeyword> findByKo(String ko);
		
	List<Jkeyword> findByJtypeOrderByScoreDesc(String jtype);
	//List<Jkeyword> findByEn(String en);
	//List<Jkeyword> findByJtype(String jtype);

	List<Jkeyword> findByJtype(String et);
	
	
//	@Query(value = "INSERT into jkeyword(ko, en, jtype, score) values( :ko, :en, :jtype, :score)")
//	@Modifying
//	void insertJkeyword(@Param("ko") String ko, @Param("en") String en, @Param("jtype") String jtype, @Param("score") double score);

	
	//List<Jkeyword> findByJtypeAndScoreLessThan(String jtype, double score);
	//List<Jkeyword> findByJtypeAndScoreGreaterThan(String jtype, double score);

}
