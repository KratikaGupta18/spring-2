package com.myapp.spring.rest.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.myapp.spring.dao.ItemRepository;
import com.myapp.spring.domain.Item;

@RestController
public class CatalogAPI {

	@Autowired
	private ItemRepository itemRepository;
	

	
	@GetMapping("/items")
	public ResponseEntity<List<Item>> findAll(){
		return new ResponseEntity<List<Item>>(itemRepository.findAll(),HttpStatus.OK);
	}
	
	@PostMapping("/items")
	public ResponseEntity<String> addItem(@RequestBody Item item){
		itemRepository.save(item);
		return new ResponseEntity<String>("created",HttpStatus.CREATED);
	}
	
	@PutMapping("/items/{id}")
	public ResponseEntity<String> updateItem(@RequestBody Item item ,@PathVariable("id") long id){
		Optional<Item> existingitem=itemRepository.findById(id);
		BeanUtils.copyProperties(item, existingitem.get());
		itemRepository.save(existingitem.get());
	    return new ResponseEntity<String>("updated",HttpStatus.OK);
		
	}
	
	@DeleteMapping("/items/{id}")
	public ResponseEntity<String> deleteItem(@PathVariable("id") long id){
		itemRepository.deleteById(id);
		return new ResponseEntity<String>("deleted",HttpStatus.OK);
	}
	
	
	
}
