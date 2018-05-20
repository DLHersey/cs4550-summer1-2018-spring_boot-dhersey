package com.example.myapp.repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.myapp.models.Module;

public interface LessonRepository 
	extends CrudRepository<Module, Integer>{
}
