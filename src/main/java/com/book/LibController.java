package com.book;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.book.entity.Book;
import com.book.entity.Books;
import com.book.entity.BooksService;
import com.book.entity.LibraryService;


@RestController
public class LibController {
	Logger logger = LoggerFactory.getLogger(LibraryApplication.class);
	
	@Autowired  
	private LibraryService service;  	
	@Autowired  
	BooksService booksService;  
	
	@GetMapping("/book")
	private List<Books> getAllBooks() 
	{
	return booksService.getAllBooks();
	}
	//creating a get mapping that retrieves the detail of a specific book
	@GetMapping("/book/{bookid}")
	private Books getBooks(@PathVariable("bookid") int bookid) 
	{
	return booksService.getBooksById(bookid);
	}
	//creating a delete mapping that deletes a specified book
	@DeleteMapping("/book/{bookid}")
	private void deleteBook(@PathVariable("bookid") int bookid) 
	{
	booksService.delete(bookid);
	}
	//creating post mapping that post the book detail in the database
	@PostMapping("/books")
	private int saveBook(@RequestBody Books books) 
	{
	booksService.saveOrUpdate(books);
	return books.getBookid();
	}
	
	
	//Testing method
	@RequestMapping(method=RequestMethod.GET, path="/lib/hello")
	public String helloWorld()  
	{  
		logger.info("this is lib/hello operation!!");
		return "this is hello operation!!";  
	}
	
	// list all books in library
	@RequestMapping(method=RequestMethod.GET, path="/lib/books")
	public List<Book> listAll()  
	{  
		logger.info("this is find All operation!!");
		return service.findAll();  
	}
	// list a book by ISBN
	@RequestMapping(method=RequestMethod.GET, path="/lib/books/isbn/{isbn}")
	public Book findOneBook(@PathVariable String isbn) throws Exception  
	{  
		logger.info("this is findOneBook operation on ISBN:"+isbn);
		return service.findOneBook(isbn);  
	}
	
	// create a new books in library
	@RequestMapping(method=RequestMethod.POST, path="/lib/books")
	public Book newBook(@RequestBody Book ab) throws Exception  
	{  
		logger.info("this is lcreatation operation!!");
		return service.addBook(ab);  
	}
	
	// delete a book by ISBN
	@RequestMapping(method=RequestMethod.DELETE, path="/lib/books/isbn/{isbn}")
	public Book deleteBook(@PathVariable String isbn) throws Exception  
	{  
		logger.info("this is deletition operation on ISBN:"+isbn);
		return service.deleteBook(isbn);  
	}
	
	// update a book by ISBN
	@RequestMapping(method=RequestMethod.PUT, path="/lib/books")
	public Book updateBook(@RequestBody Book upd) throws Exception  
	{  
		logger.info("this is update operation:"+upd);
		return service.updateBook(upd);  
	}
}