package cl.duocuc._Book.Service;

import cl.duocuc._Book.DTO.BookDTO;

import java.util.List;

public interface IBookService {
    List<BookDTO> findAll();
    BookDTO findByIsbn(Long isbn);
    BookDTO findByTitle(String title);
    BookDTO save(BookDTO bookDTO);
    void deleteByIsbn(Long isbn);

}
