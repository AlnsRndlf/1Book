package cl.duocuc.Book.Controller;

import cl.duocuc.Book.DTO.BookDTO;
import cl.duocuc.Book.Service.IBookService;
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
    public ResponseEntity<List<BookDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookDTO> findByIsbn(@PathVariable Long isbn) {
        BookDTO book = service.findByIsbn(isbn);
        if(book != null) {
            return ResponseEntity.ok(book);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar/{title}")
    public ResponseEntity<BookDTO> findByTitle(@PathVariable String title) {
        BookDTO book = service.findByTitle(title);
        if(book != null) {
            return ResponseEntity.ok(book);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<BookDTO> save(@Valid @RequestBody BookDTO bookDTO) {
        BookDTO saved = service.save(bookDTO);
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
    public ResponseEntity<BookDTO> updateStock(@PathVariable Long isbn, @PathVariable int quantity) {
            BookDTO updated = service.updateStock(isbn, quantity);
            if(updated != null) {
                return ResponseEntity.ok(updated);
            }
            else {
                return ResponseEntity.notFound().build();
            }
        }
}
