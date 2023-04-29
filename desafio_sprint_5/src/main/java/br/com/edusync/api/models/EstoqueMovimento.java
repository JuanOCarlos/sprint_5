package br.com.edusync.api.models;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;

@Data
@Entity(name = "tb_estoque_movimento")
public class EstoqueMovimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer codigo;

    @ManyToOne
    private Produto produto;
    @ManyToOne
    private FuncionarioResponsavel funcionario;
    private Date dataHora;
    @Column(length = 1)
    private String tipoMovimento;
    private Integer quantidade;
}
