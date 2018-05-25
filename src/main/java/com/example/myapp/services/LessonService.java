package com.example.myapp.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.models.Course;
import com.example.myapp.models.Lesson;
import com.example.myapp.models.Module;
import com.example.myapp.repositories.CourseRepository;
import com.example.myapp.repositories.LessonRepository;
import com.example.myapp.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonService {
	@Autowired
	LessonRepository repository;
	@Autowired
	ModuleRepository mRepository;
	@Autowired
	CourseRepository cRepository;
	
	//CREATE
	@PostMapping("/api/course/{cid}/module/{mid}/lesson")
	public Lesson createLesson(@PathVariable int cid, @PathVariable int mid, 
							@RequestBody Lesson lesson, HttpServletResponse response) {
		Optional<Course> cData = cRepository.findById(cid);
		Optional<Module> mData = mRepository.findById(mid);
		if(cData.isPresent() && mData.isPresent()) {
			return repository.save(lesson);
		}
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return null;
	}
	
	//READ
	@GetMapping("/api/lesson")
	public List<Lesson> findAllLessons() {
		return (List<Lesson>) repository.findAll();
	}
	@GetMapping("/api/lesson/{id}")
	public Lesson findLessonById(@PathVariable int id, HttpServletResponse response) {
		Optional<Lesson> data = repository.findById(id);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			return lesson;
		}
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}
	@GetMapping("/api/course/{cid}/module/{mid}/lesson")
	public List<Lesson> findAllLessonssForModule(@PathVariable int cid, @PathVariable int mid, HttpServletResponse response) {
		Optional<List<Lesson>> data = repository.findAllLessonsForModule(mid);
		if(data.isPresent()) {
			List<Lesson> lessons = data.get();
			return lessons;
		}
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}
	
	
	//UPDATE
	@PutMapping("/api/lesson/{lessonId}")
	public Lesson updateLesson(@PathVariable int lessonId, @RequestBody Lesson lesson, HttpServletResponse response) {
		Optional<Lesson> data = repository.findById(lessonId);
		if (data.isPresent()) {
			Lesson tlesson = data.get();
			tlesson.setTitle(lesson.getTitle());
			tlesson.setModule(lesson.getModule());
			repository.save(tlesson);
			return tlesson;
		}
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}
	
	//DELETE
	@PostMapping("/api/lesson/{id}")
	public void deleteLesson(@PathVariable int id) {
		repository.deleteById(id);
	}
	
	
}
