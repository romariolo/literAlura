package LiterAlura.repository;

import LiterAlura.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByAnoDeMorteIsNull();
    List<Autor> findByAniversarioBetween(LocalDate inicio, LocalDate fim);
}
