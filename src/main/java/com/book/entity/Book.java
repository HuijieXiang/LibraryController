package com.book.entity;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Book  {
	protected static int glbookId=1;	// keep the book id across all objects; initialized as 1
	private int bookId;	// book id 
	private String isbn;
	private String bookName;	
	private Date publishDate;
	
	Logger logger = LoggerFactory.getLogger(LibraryService.class);
	public Book()   
	{
		logger.info("book Default constructor");
	}

	public Book(String isbn, String name, Date pubdate)   
	{
		if (isbn!=null && name!=null) {
			this.bookId=Book.glbookId;
			glbookId++;
			this.isbn = isbn;
			this.bookName = name;
			this.publishDate = pubdate;
			logger.info("IN book ctr ... ");
		}
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", bookName=" + bookName + ", bookId=" + bookId + ", publishDate=" + publishDate
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Book other = (Book) obj;
		return this.isbn.equals(other.getIsbn());
	}	
}