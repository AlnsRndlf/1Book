package cl.duocucBook.book.service;

import cl.duocucBook.book.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<BookDto> findAll();
    Optional<BookDto> findByIsbn(Long isbn);
    Optional<BookDto> findByTitle(String title);
    BookDto save(BookDto bookDTO);
    void deleteByIsbn(Long isbn);
    Optional<BookDto> updateStock(Long isbn, int quantity);
}
