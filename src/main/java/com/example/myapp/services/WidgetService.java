package com.example.myapp.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.models.Widget;
import com.example.myapp.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {
	@Autowired
	WidgetRepository repository;
	
	//CREATE
	
	
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
			nWidget.setName(widget.getName());
			nWidget.setStyle(widget.getStyle());
			nWidget.setClassName(widget.getClassName());
			nWidget.setText(widget.getText());
			nWidget.setWidth(widget.getWidth());
			nWidget.setHeight(widget.getHeight());
			repository.save(nWidget);
			return widget;
		}
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return null;
	}
	
	//DELETE
	@DeleteMapping("/api/widget/{wid}")
	public void deleteWidget(@PathVariable int wid) {
		repository.deleteById(wid);
	}
}
