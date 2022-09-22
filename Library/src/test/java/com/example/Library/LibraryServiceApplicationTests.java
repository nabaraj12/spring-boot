package com.example.Library;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class LibraryServiceApplicationTests {

	@Autowired
	LibraryController libraryController;

	@MockBean
	private LibraryService libraryService;

	@Test
	void contextLoads() {

		List<Book> bookList= Stream.of(
				new Book(1,"Madan","oxford","laxman"),
				new Book(1,"Arvind","oxford","rahul")
		).collect(Collectors.toList());

		when(libraryService.getAllBooks()).thenReturn(bookList);

		assertThat(libraryController.getAllBooks()).isEqualTo(bookList);
	}

}
