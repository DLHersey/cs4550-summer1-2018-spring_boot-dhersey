package com.example.myapp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.myapp.models.Module;

public interface ModuleRepository 
	extends CrudRepository<Module, Integer>{
	
	@Query("SELECT u.modules FROM Course u WHERE u.id=:cid")
	Optional<List<Module>> findAllModulesForCourse(@Param("cid") int cid);
}
