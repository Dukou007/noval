package com.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {

	public List<Books> findByNameContaining(String name);
	
	public List<Books> findByBookState(Integer bookState);
	
}
