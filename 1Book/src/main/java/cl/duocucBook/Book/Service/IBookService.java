package cl.duocucBook.Book.Service;

import cl.duocucBook.Book.DTO.BookDto;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<BookDto> findAll();
    Optional<BookDto> findByIsbn(Long isbn);
    Optional<BookDto> findByTitle(String title);
    BookDto save(BookDto bookDTO);
    void deleteByIsbn(Long isbn);
    BookDto updateStock(Long isbn, int quantity);
}
