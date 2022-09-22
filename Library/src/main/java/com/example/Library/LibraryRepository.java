package com.example.Library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Library,Integer> {

    void deleteLibraryByUsernameAndBookId(String username, int bookId);
    List<Library> findByUsernameAndBookId(String username, int bookId);
}
