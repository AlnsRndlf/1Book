package cl.duocucBook.Book.Service;

import cl.duocucBook.Book.DTO.BookDto;
import cl.duocucBook.Book.Model.Book;
import cl.duocucBook.Book.Repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {

    private final BookRepository repository;

    private BookDto toDTO(Book book) {
        return new BookDto(
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor(),
                book.getYearPublicated(),
                book.getStock()
        );
    }

    private Book toEntity(BookDto bookDTO) {
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
    public List<BookDto> findAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto save(BookDto bookDTO) {
        if(repository.existsById(bookDTO.getIsbn())) {
            throw new IllegalArgumentException("ya existe un libro con ese isbn");
        }
        return this.toDTO(repository.save(this.toEntity(bookDTO)));
    }

    @Override
    public void deleteByIsbn(Long isbn) {
        if(repository.existsById(isbn)) {
            repository.deleteById(isbn);
        }
    }

    @Override
    public BookDto findByIsbn(Long isbn) {
        return repository.findById(isbn).map(this::toDTO).orElse(null);
    }

    @Override
    public BookDto findByTitle(String title) {
        Book book = repository.findByTitle(title);
        if(book != null) {
            return toDTO(book);
        }
        else {
            return null;
        }
    }

    @Override
    public BookDto updateStock(Long isbn, int quantity) {
        Book book = repository.findById(isbn).orElse(null);
        if(book != null) {
            int newStock = book.getStock() + quantity;
            if(newStock < 0) {
                throw new IllegalArgumentException("Stock no puede ser negativo");
            }
            book.setStock(newStock);
            return this.toDTO(repository.save(book));
        }
        throw new IllegalArgumentException("Libro no encontrado");
    }
}
