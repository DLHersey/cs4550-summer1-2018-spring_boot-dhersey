package com.example.myapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.models.Course;
import com.example.myapp.repositories.CourseRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseService {
	@Autowired
	CourseRepository repository;

	@GetMapping("/api/course")
	public Iterable<Course> findAllCourses() {
		return repository.findAll(); 
	}
	
	@PostMapping("/api/course")
	public Course createCourse (@RequestBody Course course) {
			return repository.save(course);
	}

}
