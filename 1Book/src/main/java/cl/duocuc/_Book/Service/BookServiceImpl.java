package cl.duocuc._Book.Service;

import cl.duocuc._Book.DTO.BookDTO;
import cl.duocuc._Book.Model.Book;
import cl.duocuc._Book.Repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {

    private final BookRepository repository;

    private BookDTO toDTO(Book book) {
        return new BookDTO(
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor(),
                book.getYearPublicated(),
                book.getStock()
        );
    }

    private Book toEntity(BookDTO bookDTO) {
        return new Book(
                bookDTO.getIsbn(),
                bookDTO.getTitle(),
                bookDTO.getAuthor(),
                bookDTO.getYearPublicated(),
                bookDTO.getStock()
        );
    }


    // metodos del que implementa
    @Override
    public List<BookDTO> findAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO save(BookDTO bookDTO) {
        return this.toDTO(repository.save(this.toEntity(bookDTO)));
    }

    @Override
    public void deleteByIsbn(Long isbn) {
        if(repository.existsById(isbn)) {
            repository.deleteById(isbn);
        }
    }

    @Override
    public BookDTO findByIsbn(Long isbn) {
        return repository.findById(isbn).map(this::toDTO).orElse(null);
    }

    @Override
    public BookDTO findByTitle(String title) {
        Book book = repository.findByTitle(title);
        if(book != null) {
            return toDTO(book);
        }
        else {
            return null;
        }
    }
}
