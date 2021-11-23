package com.pos.ebook.Ebook;

import com.pos.ebook.Ebook.dao.BookDao;
import com.pos.ebook.Ebook.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookTests {
	private BookDao bookDao;

	@BeforeEach
	void setUp() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataSource.setUsername("ebook");
		dataSource.setPassword("bunica");
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");

		bookDao = new BookDao(new JdbcTemplate(dataSource));
	}

	@Test
	void testGetAllBooks() {
		List<Book> books = bookDao.getAllBooks();

		assertEquals(2, books.size());
		assertEquals(books.get(0).getTitle(), "Baltagul");
	}

}
