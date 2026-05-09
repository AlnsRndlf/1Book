package cl.duocuc._Book.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Integer yearPublicated;
    private Integer stock;
}
