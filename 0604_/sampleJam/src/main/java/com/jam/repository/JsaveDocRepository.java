package com.jam.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jam.domain.JsaveDoc;

public interface JsaveDocRepository extends CrudRepository<JsaveDoc, String>{
	
	List<JsaveDoc> findAll();
	List<JsaveDoc> findByUserId(String userId);

}
