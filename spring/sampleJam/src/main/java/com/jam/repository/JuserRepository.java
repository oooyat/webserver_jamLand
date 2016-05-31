package com.jam.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jam.domain.Juser;

public interface JuserRepository extends CrudRepository<Juser, String>{
	
	List<Juser> findAll();
	
	Juser findOne(String id);

	List<Juser> findByName(String name);
}
