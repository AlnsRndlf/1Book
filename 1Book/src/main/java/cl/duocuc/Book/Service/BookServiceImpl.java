package cl.duocuc.Book.Service;

import cl.duocuc.Book.DTO.BookDTO;
import cl.duocuc.Book.Model.Book;
import cl.duocuc.Book.Repository.BookRepository;
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

    @Override
    public BookDTO updateStock(Long isbn, int quantity) {
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
