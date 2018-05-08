package com.myapp.spring.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.myapp.spring.domain.Item;

@Repository
public interface ItemRepository extends MongoRepository<Item, Long> {

}
