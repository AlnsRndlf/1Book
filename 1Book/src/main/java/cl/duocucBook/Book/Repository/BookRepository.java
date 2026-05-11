package cl.duocucBook.Book.Repository;


import cl.duocucBook.Book.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Book findByTitle(String title);
}
