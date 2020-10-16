package com.book.jms;

import java.util.ArrayList;
import java.util.List;

import com.book.entity.Books;

public class MessageStorage {
	  private List<Books> books = new ArrayList<>();
	  
	  public void add(Books book) {
	    books.add(book);
	  }
	  
	  public void clear() {
	    books.clear();
	  }
	  
	  public List<Books> getAll(){
	    return books;
	  }
}
