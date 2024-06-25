package com.lostandfound.repository;

import com.lostandfound.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	List<Item> findByNameContainingIgnoreCase(String name);
	  List<Item> findAllByImageUrlNotNull();

	    // Query method to find all found items with null imageUrl
	    List<Item> findAllByImageUrlNull();
}
