package com.jam.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jam.domain.JdocRelation;

public interface JdocRelationRepository extends CrudRepository<JdocRelation, String>{
	
	List<JdocRelation> findByDocId(int docId);
	List<JdocRelation> findByKeywordKo(String keywordKo);
	//JdocRelation findByDocIdAndKeywordKo(int docId, String keywordKo);

}
