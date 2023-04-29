package br.com.edusync.api.repositories.criteria.params;

import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoFilterParam {

    private String descricao;
    private BigDecimal precoVenda;
    private Integer saldoAtual;
}
