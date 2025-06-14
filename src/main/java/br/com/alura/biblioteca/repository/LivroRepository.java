package br.com.alura.biblioteca.repository;

import br.com.alura.biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    @Query("SELECT i FROM Livro i WHERE i.idiomas LIKE %:idioma%")
    List<Livro> findAllByIdioma(String idioma);
}
