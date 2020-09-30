package com.book.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LibraryService {
	Logger logger = LoggerFactory.getLogger(LibraryService.class);
	// define library collection
	List<Book> libCollect= new ArrayList<Book>();
	
	// constructor to load library books
	public LibraryService() {
		initLibrary();
	}
	
	// adding initial books into library collection
	public void initLibrary() {
		Book abook = new Book("0-101-345", "Book1", new Date());
		libCollect.add(abook);
		libCollect.add(new Book("0-102-345", "Book2", new Date()) );
		libCollect.add(new Book("0-103-345", "Book3", new Date()) );
		libCollect.add(new Book("0-104-345", "Book4", new Date()) );
		libCollect.add(new Book("0-105-345", "Book5", new Date()) );
		// ... continue to add more as needed ...
	}
	
	//retrieve all books from the library collection  
	public List<Book> findAll()  
	{  
		return libCollect;  
	}
	
	//retrieve a book from the library collection by ISBN
	public Book findOneBook(String isbn) throws Exception  
	{  
		Book bk = new Book();
		bk.setBookId(0);bk.setIsbn(isbn);
		int ind =libCollect.indexOf(bk);		
		if (ind==-1) {
			String errm="Did not find the ISBN:"+isbn;
			logger.info(errm);
			throw new Exception (errm);
		}
		return libCollect.get(ind);
	}
	
	// create a book and add it to library collection  
	public Book addBook(Book abook)  throws Exception 
	{
		logger.info(" input book:"+abook);
		if (abook==null || abook.getBookName()==null || abook.getIsbn()==null) {
			String errormsg="Invalid book entry: book name["+abook.getBookName()+"] and ISBN["+abook.getIsbn()+"] should NOT be null";
			logger.info(errormsg);	// log it
			throw new Exception (errormsg);
		} else {
			Date nd = new Date();
			if (abook.getPublishDate()!=null)
				nd = abook.getPublishDate();
			Book ab = new Book(abook.getIsbn(),abook.getBookName(),nd);
			libCollect.add(ab);		// add the new book into library collection
			return ab;
		}
	}
	
	// delete a book by book ISBN from library collection  
	public Book deleteBook( String bkISBN)  throws Exception 
	{
		String errmsg=null;
		List<Book> isbns= libCollect.stream()				// Java 8 stream
				.filter(p-> p.getIsbn().equals(bkISBN))		// filter the book by ISBN
				.collect(Collectors.toList());				// return the collection	
		logger.info("isbns obj size:"+isbns.size());
		if (isbns==null || isbns.size()==0) {
			errmsg="Did not find such book with ISBN:"+bkISBN;
		} else if (isbns.size()>1) {
			errmsg="Found multiple books with same ISBN:"+bkISBN+", book count:"+isbns.size();
		}
		if (errmsg!=null) {
			logger.info(errmsg);
			throw new Exception (errmsg);
		}
		
		// delete the isbns[0] from libCollect
		libCollect.remove(isbns.get(0));
		return isbns.get(0);
	}
	
	// create a book and add it to library collection  
	public Book updateBook(Book upd)  throws Exception 
	{
		logger.info("IN updateBook() input book:"+upd);
		// 1. update book by ISBN if it exists
		for (Book bk:libCollect) {
			if (bk.getIsbn().equals(upd.getIsbn()) ) {
				logger.info("Found the book by ISBN, doing update");
				bk.setBookId(upd.getBookId());
				bk.setBookName(upd.getBookName());
				bk.setPublishDate(upd.getPublishDate());
				return bk;
			}
		}
		
		// 2. update book by bookId if it exists
		for (Book bk:libCollect) {
			if (bk.getBookId()==upd.getBookId() ) {
				logger.info("Found the book by book ID, doing update");
				bk.setIsbn(upd.getIsbn());
				bk.setBookName(upd.getBookName());
				bk.setPublishDate(upd.getPublishDate());
				return bk;
			}
		}
		String errmsg="Did not find the update book by ID:"+upd.getBookId()+" or ISBN:"+upd.getIsbn();
		logger.info(errmsg);
		throw new Exception (errmsg);		
	}
}
