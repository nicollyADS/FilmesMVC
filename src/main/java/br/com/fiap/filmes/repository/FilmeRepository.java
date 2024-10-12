package br.com.fiap.filmes.repository;

import br.com.fiap.filmes.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Long> {

    List<Filme> findByNomeContainingIgnoreCase(String nome);

}
