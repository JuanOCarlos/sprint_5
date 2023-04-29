package br.com.edusync.api.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer codigo;

    @Column(length = 255)
    private String descricao;

    @Column(precision =10,scale =2)
    private BigDecimal precoVenda;
    private Integer saldoAtual;
}
