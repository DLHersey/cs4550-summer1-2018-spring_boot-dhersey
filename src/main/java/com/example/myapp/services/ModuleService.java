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
import com.example.myapp.models.Module;
import com.example.myapp.repositories.CourseRepository;
import com.example.myapp.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleService {
	@Autowired
	ModuleRepository repository;
	CourseRepository cRepository;
	
	//CREATE
	@PostMapping("/api/course/{cid}/module")
	public Module createModule(@PathVariable int cid, @RequestBody Module module, HttpServletResponse response) {
		Optional<Course> data = cRepository.findById(cid);
		if(data.isPresent()) {
			Course course = data.get();
			Module nModule = module;
			nModule.setCourse(course);
			return repository.save(nModule);
		}
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return null;
	}
	
	//READ
	@GetMapping("/api/module")
	public List<Module> findAllModules() {
		return (List<Module>) repository.findAll();
	}
	//findModuleById
	@GetMapping("/api/module/{id}")
	public Module findModuleById(@PathVariable int id, HttpServletResponse response) {
		Optional<Module> data = repository.findById(id); 
		if(data.isPresent()) {
			Module module = data.get();
			return module;
		}
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}
	//findAllModulesForCourse
	@GetMapping("/api/course/{cid}/module")
	public List<Module> findAllModulesForCourse(@PathVariable int cid, HttpServletResponse response) {
		Optional<List<Module>> data = repository.findAllModulesForCourse(cid);
		if(data.isPresent()) {
			List<Module> modules = data.get();
			return modules;
		}
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}
	
	//UPDATE
	@PutMapping("/api/module/{id}")
	public Module updateModule(@PathVariable int id, @RequestBody Module module, HttpServletResponse response) {
		Optional<Module> data = repository.findById(id);
		if(data.isPresent()) {
			Module nModule = data.get();
			nModule.setTitle(module.getTitle());
			nModule.setCourse(module.getCourse());
			nModule.setLessons(module.getLessons());
			return nModule;
		}
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}
	
	//DELETE
	@PostMapping("/api/module/{id}")
	public void deleteModule(@PathVariable int id) {
		repository.deleteById(id);
	}
}
