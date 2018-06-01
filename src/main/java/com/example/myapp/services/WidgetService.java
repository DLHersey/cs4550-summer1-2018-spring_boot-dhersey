package com.example.myapp.services;

import java.util.List;
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

import com.example.myapp.models.Lesson;
import com.example.myapp.models.Widget;
import com.example.myapp.repositories.LessonRepository;
import com.example.myapp.repositories.WidgetRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class WidgetService {
	@Autowired
	WidgetRepository repository;
	@Autowired
	LessonRepository lRepository;
	
	//CREATE
	@PostMapping("/api/lesson/{lid}/widget")
	public Widget createWidget(@PathVariable int lid, @RequestBody Widget widget, HttpServletResponse response) {
		Optional<Lesson> data = lRepository.findById(lid);
		if(data.isPresent()) {
			repository.save(widget);
		}
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		return null;
	}
	
	
	//READ
	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets() {
		return (List<Widget>) repository.findAll();
	}
	
	@GetMapping("/api/widget/{wid}")
	public Widget findWidgetById(@PathVariable int wid, HttpServletResponse response) {
		Optional<Widget> data = repository.findById(wid);
		if(data.isPresent()) {
			Widget widget = data.get();
			return widget;
		}
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}
	
	//UPDATE
	@PutMapping("/api/widget/{wid}")
	public Widget updateWidget(@PathVariable int wid, @RequestBody Widget widget, HttpServletResponse response) {
		Optional<Widget> data = repository.findById(wid);
		if(data.isPresent()) {
			Widget nWidget = data.get();
			nWidget.setLesson(widget.getLesson());
			nWidget.setName(widget.getName());
			nWidget.setStyle(widget.getStyle());
			nWidget.setWidgetType(widget.getWidgetType());
			nWidget.setText(widget.getText());
			nWidget.setWidth(widget.getWidth());
			nWidget.setHeight(widget.getHeight());
			nWidget.setSize(widget.getSize());
			nWidget.setSrc(widget.getSrc());
			nWidget.setListOrder(widget.getListOrder());
			nWidget.setListType(widget.getListType());
			nWidget.setListItems(widget.getListItems());
			repository.save(nWidget);
			return widget;
		}
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return null;
	}
	
	//SAVE
	@PostMapping("/api/widget/save")
	public void saveAllWidgets(@RequestBody List<Widget> widgets) {
			for(Widget widget: widgets) {
				repository.save(widget);
			}
	}
	
	
	//DELETE
	@DeleteMapping("/api/widget/{wid}")
	public void deleteWidget(@PathVariable int wid) {
		repository.deleteById(wid);
	}
}
