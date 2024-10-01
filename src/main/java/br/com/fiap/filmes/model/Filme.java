package br.com.fiap.filmes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="TB_FIAP_FILMES")
@Getter @Setter
@NoArgsConstructor
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "filme")
    @SequenceGenerator(name = "filme", sequenceName = "seq_fiap_filme", allocationSize = 1)
    @Column(name="cdFilme", length = 9)
    private Long id;

    @Column(name = "nm_filme", nullable = false, length = 100)
    private String nome;

    @Column(name = "ds_genero", nullable = false, length = 70)
    private String genero;

    @Column(name = "dt_lancamento", nullable = false)
    private LocalDate lancamento;

    @Column(name = "ds_sinopse", nullable = false, length = 500)
    private String sinopse;


}
