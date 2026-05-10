package cl.duocuc._Book.Controller;

import cl.duocuc._Book.DTO.BookDTO;
import cl.duocuc._Book.Service.IBookService;
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
    public ResponseEntity<BookDTO> findByTitle(@RequestParam String title) {
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
        BookDTO save = service.save(bookDTO);
        if(save != null) {
            return new ResponseEntity<>(service.save(bookDTO), HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> delete(@PathVariable Long isbn) {
        service.deleteByIsbn(isbn);
        return ResponseEntity.noContent().build();
    }
}
