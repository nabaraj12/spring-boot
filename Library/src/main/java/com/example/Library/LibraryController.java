package com.example.Library;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class LibraryController {

    @Autowired
    LibraryRepository libraryRepository;

    @Autowired
    BooksProxy booksProxy;

    @Autowired
    UsersProxy usersProxy;

    @Autowired
    LibraryService libraryService;

    @RequestMapping(value = "/library/books",method = RequestMethod.GET)
    @CircuitBreaker(name="books-service",fallbackMethod = "getBooks")
    public List<Book> getAllBooks()
    {
        return libraryService.getAllBooks();
    }

    public List<Book> getBooks(Exception e)
    {
        return Stream.of(
                new Book(1,"Madan","oxford","laxman"),
                new Book(1,"Arvind","oxford","rahul")
        ).collect(Collectors.toList());
    }

    @RequestMapping(value = "/library/books/{bookid}",method = RequestMethod.GET)
    public Optional<Book> getBookById(@PathVariable int bookid)
    {
        return booksProxy.getBookByID(bookid);
    }

    @RequestMapping(value = "/library/books",method = RequestMethod.POST)
    public void saveBooks(@RequestBody Book book)
    {
        booksProxy.postBooks(book);
    }

    @RequestMapping(value = "/library/books/{bookid}",method = RequestMethod.PUT)
    public void updateBooks(@PathVariable int bookid,@RequestBody Book book)
    {
        booksProxy.updateBook(bookid,book);
    }

    @RequestMapping(value = "/library/books/{bookid}",method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable int bookid)
    {
        booksProxy.deleteBook(bookid);
    }

    @RequestMapping(value = "/library/users",method = RequestMethod.GET)
    public List<User> getAllUsers()
    {
        return usersProxy.getAllUsers();
    }

    @RequestMapping(value = "/library/users/{username}",method = RequestMethod.GET)
    public Optional<User> getUserByUsername(@PathVariable String username)
    {
        return usersProxy.getUserByUsername(username);
    }

    @RequestMapping(value = "/library/users",method = RequestMethod.POST)
    public void addUser(@RequestBody User user)
    {
        usersProxy.postUsers(user);
    }

    @RequestMapping(value = "/library/users/{username}",method = RequestMethod.DELETE)
    public void deleteUserByUsername(@PathVariable String username)
    {
        usersProxy.deleteUser(username);
    }

    @RequestMapping(value = "/library/users/{username}",method = RequestMethod.PUT)
    public void updateUser(@PathVariable String username,@RequestBody User user)
    {
        usersProxy.updateUser(username,user);
    }

    @RequestMapping(value = "/library/users/{username}/books/{bookid}",method = RequestMethod.POST)
    public void issueLibraryBook(@PathVariable String username,@PathVariable int bookid)
    {
        Library library=new Library(username,bookid);
        libraryRepository.save(library);
    }

    @RequestMapping(value = "/library/users/{username}/books/{bookid}",method = RequestMethod.DELETE)
    public void deleteBookFromLibrary(@PathVariable String username,@PathVariable int bookid)
    {
        List<Library> libraryList=libraryRepository.findByUsernameAndBookId(username,bookid);
        libraryRepository.deleteAll(libraryList);
    }

    @RequestMapping(value = "/library/LibraryUsers",method = RequestMethod.GET)
    public List<Library> getListOfLibrary()
    {
        return libraryRepository.findAll();
    }









}
