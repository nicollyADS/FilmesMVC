package br.com.fiap.filmes.repository;

import br.com.fiap.filmes.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
}
