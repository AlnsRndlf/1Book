package cl.duocuc._Book.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Book {

    @Id
    private Long isbn;
    private String title;
    private String author;
    @Column(name = "year_publicated")
    private Integer yearPublicated;
    private Integer stock;
}
