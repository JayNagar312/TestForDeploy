package com.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Person;
import com.service.FireBaseService;

@RestController
public class HomeController {

@Autowired
FireBaseService fservice;
	
	@PostMapping("/createPerson")
	public String postEx(@RequestBody Person person) throws InterruptedException, ExecutionException {
		return fservice.savePerson(person);
	}	

	@GetMapping("/getPerson")
	public Person getEx(@RequestHeader() String name) throws InterruptedException, ExecutionException {
		return fservice.getPerson(name);
	}
	@PutMapping("/updatePerson")
	public String putEx(@RequestBody Person person) throws InterruptedException, ExecutionException {
		return fservice.updatePerson(person);
	}
	@DeleteMapping("/deletePerson")
	public String deleteEx(@RequestHeader String name) {
		return fservice.deletePerson(name);
	}
}
