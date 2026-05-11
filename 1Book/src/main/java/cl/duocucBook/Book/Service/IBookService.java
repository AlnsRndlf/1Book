package cl.duocucBook.Book.Service;

import cl.duocucBook.Book.DTO.BookDto;

import java.util.List;

public interface IBookService {
    List<BookDto> findAll();
    BookDto findByIsbn(Long isbn);
    BookDto findByTitle(String title);
    BookDto save(BookDto bookDTO);
    void deleteByIsbn(Long isbn);
    BookDto updateStock(Long isbn, int quantity);
}
