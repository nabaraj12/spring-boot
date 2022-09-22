package com.example.Library;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    @Autowired
    BooksProxy booksProxy;

    public List<Book> getAllBooks()
    {
        return booksProxy.getAllBooks();
    }
}
