package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.Jdocrelation;

public interface JdocrelationRepository extends CrudRepository<Jdocrelation, String>{
	
	Jdocrelation findByDidAndKeyword(int did, String keyword);
	
	List<Jdocrelation> findAll();
	List<Jdocrelation> findByDid(int did);
	List<Jdocrelation> findByKeyword(String keyword);
	//JdocRelation findByDocIdAndKeywordKo(int docId, String keywordKo);
	
	//void insert(Jdocrelation jdocrelation);

}
