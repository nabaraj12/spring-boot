package com.example.Library;


import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.List;
import java.util.Optional;

@FeignClient(name="books-service")
@LoadBalancerClient(name="books-service")
public interface BooksProxy {

    @RequestMapping(value = "/books",method = RequestMethod.GET)
    public List<Book> getAllBooks();

    @RequestMapping(value = "/books/{id}",method = RequestMethod.GET)
    public Optional<Book> getBookByID(@PathVariable int id);

    @RequestMapping(value = "/books",method = RequestMethod.POST)
    public void postBooks(@RequestBody Book book);

    @RequestMapping(value = "/books/{id}",method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable int id);

    @RequestMapping(value = "/books/{id}",method = RequestMethod.PUT)
    public void updateBook(@PathVariable int id,@RequestBody Book book);
}
