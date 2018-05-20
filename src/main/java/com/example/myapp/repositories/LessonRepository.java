package com.example.myapp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.myapp.models.Lesson;

public interface LessonRepository 
	extends CrudRepository<Lesson, Integer>{
	

	@Query("SELECT u.lessons FROM Module u WHERE u.id=:mid")
	Optional<List<Lesson>> findAllLessonsForModule(@Param("mid") int mid);
}
