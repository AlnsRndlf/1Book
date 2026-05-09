package cl.duocuc._Book.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor @NoArgsConstructor
public class BookDTO {

    @NotNull(message = "El ISBN es obligatorio.")
    private Long isbn;

    @Size(min = 1,max = 255,message = "cantidad de caracteres invalida.")
    @NotBlank(message = "El titulo no puede estar vacio.")
    private String title;

    @Size(min = 1,max = 255,message = "cantidad de caracteres invalida.")
    @NotBlank(message = "Tiene que tener un autor.")
    private String author;

    // se puede desconocer el año
    private Integer yearPublicated;

    @NotNull
    @Min(value = 0, message = "el stock disponible no puede ser un valor negativo.")
    private Integer stock;
}
