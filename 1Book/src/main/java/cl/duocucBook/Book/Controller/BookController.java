package cl.duocucBook.Book.Controller;

import cl.duocucBook.Book.DTO.BookDto;
import cl.duocucBook.Book.Service.IBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final IBookService service;

    @GetMapping
    public ResponseEntity<List<BookDto>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookDto> findByIsbn(@PathVariable Long isbn) {
        BookDto book = service.findByIsbn(isbn);
        if(book != null) {
            return ResponseEntity.ok(book);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar/{title}")
    public ResponseEntity<BookDto> findByTitle(@PathVariable("title") String title) {
        BookDto book = service.findByTitle(title);
        if(book != null) {
            return ResponseEntity.ok(book);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<BookDto> save(@Valid @RequestBody BookDto bookDTO) {
        BookDto saved = service.save(bookDTO);
        if(saved != null) {
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> delete(@PathVariable Long isbn) {
        service.deleteByIsbn(isbn);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{isbn}/stock/{quantity}")
    public ResponseEntity<BookDto> updateStock(@PathVariable Long isbn, @PathVariable int quantity) {
            BookDto updated = service.updateStock(isbn, quantity);
            if(updated != null) {
                return ResponseEntity.ok(updated);
            }
            else {
                return ResponseEntity.notFound().build();
            }
        }
}
