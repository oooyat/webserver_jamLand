package com.jam.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jam.domain.Jdocrelation;

public interface JdocrelationRepository extends CrudRepository<Jdocrelation, String>{
	
	List<Jdocrelation> findByDid(int did);
	List<Jdocrelation> findByKeyword(String keyword);
	//JdocRelation findByDocIdAndKeywordKo(int docId, String keywordKo);
	
	//void insert(Jdocrelation jdocrelation);

}
