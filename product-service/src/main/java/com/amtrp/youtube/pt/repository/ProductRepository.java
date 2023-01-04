package com.amtrp.youtube.pt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.amtrp.youtube.pt.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>{

}
