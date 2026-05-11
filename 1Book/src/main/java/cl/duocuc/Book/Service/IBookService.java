package cl.duocuc.Book.Service;

import cl.duocuc.Book.DTO.BookDto;

import java.util.List;

public interface IBookService {
    List<BookDto> findAll();
    BookDto findByIsbn(Long isbn);
    BookDto findByTitle(String title);
    BookDto save(BookDto bookDTO);
    void deleteByIsbn(Long isbn);
    BookDto updateStock(Long isbn, int quantity);
}
