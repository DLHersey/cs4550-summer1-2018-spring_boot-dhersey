package com.example.myapp.services;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.models.Course;
import com.example.myapp.repositories.CourseRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseService {
	@Autowired
	CourseRepository repository;
	
	//CREATE
	@PostMapping("/api/course")
	public Course createCourse (@RequestBody Course course) {
			return repository.save(course);
	}
	
	//READ
	@GetMapping("/api/course")
	public Iterable<Course> findAllCourses() {
		return repository.findAll(); 
	}
	@GetMapping("/api/course/{id}")
	public Course findCourseById(@PathVariable int id, HttpServletResponse response) {
		Optional<Course> data = repository.findById(id);
		if(data.isPresent()) {
			Course course = data.get();
			return course;
		}
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return null; 
	}
	
	//UPDATE
	@PutMapping("/api/course")
	public Course updateCourse(@RequestBody Course course, HttpServletResponse response) {
		Optional<Course> data = repository.findById(course.getId());
		if(data.isPresent()) {
			Course nCourse = data.get();
			nCourse.setTitle(course.getTitle());
			Date date = new Date();
			nCourse.setModified(date);
			return nCourse;
		}
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}
	
	//DELETE
	@DeleteMapping("/api/course/{cid}")
	public void deleteCourse(@PathVariable int cid) {
		repository.deleteById(cid);
	}

}
