package br.com.edusync.api.repositories.criteria.params;

import br.com.edusync.api.models.Produto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepositoryCustom {

    List<Produto> listarPositivo(Integer codigo);
    List<Produto> filtrarProdutosCadastrados(ProdutoFilterParam produtoFilterParam);
}
