package br.com.edusync.api.repositories.criteria.params;

import br.com.edusync.api.models.EstoqueMovimento;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstoqueRepositoryCustom {

    List<EstoqueMovimento> listarHistoricoDeMovimento(Integer produto);
}
