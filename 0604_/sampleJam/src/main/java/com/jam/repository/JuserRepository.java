package com.jam.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jam.domain.Juser;

@RepositoryRestResource
public interface JuserRepository extends CrudRepository<Juser, String>{
	
	List<Juser> findAll();
	
	//Juser findOne(String id);

	//List<Juser> findByName(String name);
}
