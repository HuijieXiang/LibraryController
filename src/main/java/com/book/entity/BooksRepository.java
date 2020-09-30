package com.book.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;  
  
//repository that extends CrudRepository  
public interface BooksRepository extends JpaRepository<Books, Integer>  
{  
}