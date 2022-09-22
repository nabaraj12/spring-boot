
package com.example.Books;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class BooksController {

    @Autowired
    BooksRepository booksRepository;

    @Autowired
    private DbConfiguration configuration;

    @Value("${server.port}")
    private int serverPort;

    @RequestMapping(value="/database",method = RequestMethod.GET)
    public Database retrieveLimits() {
        return new Database(configuration.getUrl());
    }
    @RequestMapping(value = "/port",method = RequestMethod.GET)
    public int getPort()
    {
        return serverPort;
    }

    @RequestMapping(value = "/books",method = RequestMethod.GET)
    public List<Book> getAllBooks()
    {
        return booksRepository.findAll();
    }

    @RequestMapping(value = "/books/{id}",method = RequestMethod.GET)
    public Optional<Book> getBookByID(@PathVariable int id)
    {
        return booksRepository.findById(id);
    }

    @RequestMapping(value = "/books",method = RequestMethod.POST)
    public void postBooks(@RequestBody Book book)
    {
        booksRepository.save(book);
    }

    @RequestMapping(value = "/books/{id}",method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable int id)
    {
        booksRepository.deleteById(id);
    }

    @RequestMapping(value = "/books/{id}",method = RequestMethod.PUT)
    public void updateBook(@PathVariable int id,@RequestBody Book book)
    {
        Book book1=booksRepository.getReferenceById(id);
        if(!Objects.equals(book1.getAuthor(), book.getAuthor()))
        {
            book1.setAuthor(book.getAuthor());
        }
        if(!Objects.equals(book1.getPublisher(), book.getPublisher()))
        {
            book1.setPublisher(book.getPublisher());
        }
        if(!Objects.equals(book1.getName(), book.getName()))
        {
            book1.setName(book.getName());
        }
        booksRepository.save(book1);
    }


}
