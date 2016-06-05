package com.jam.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jam.domain.Jsavedoc;

public interface JsavedocRepository extends CrudRepository<Jsavedoc, String>{
	
	List<Jsavedoc> findAll();
	List<Jsavedoc> findByUid(String uid);

}
