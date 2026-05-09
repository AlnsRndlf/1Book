package cl.duocuc._Book.Service;

import cl.duocuc._Book.DTO.BookDTO;
import cl.duocuc._Book.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private BookRepository repository;

    @Override
    public List<BookDTO> findAll() {
        return repository.findAll().stream().map(this.)
    }
}
