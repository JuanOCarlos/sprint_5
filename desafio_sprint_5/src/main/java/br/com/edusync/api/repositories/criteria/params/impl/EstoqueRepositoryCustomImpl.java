package br.com.edusync.api.repositories.criteria.params.impl;

import br.com.edusync.api.models.EstoqueMovimento;
import br.com.edusync.api.repositories.criteria.params.EstoqueRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EstoqueRepositoryCustomImpl implements EstoqueRepositoryCustom {

    private EntityManager entityManager;

    public EstoqueRepositoryCustomImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    public List<EstoqueMovimento> listarHistoricoDeMovimento(Integer produtoId){
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<EstoqueMovimento> estoqueCriteriaQuery = criteriaBuilder.createQuery(EstoqueMovimento.class);

        Root<EstoqueMovimento> produtoRoot = estoqueCriteriaQuery.from(EstoqueMovimento.class);

        estoqueCriteriaQuery.select(produtoRoot).where(criteriaBuilder.equal(produtoRoot.get("produto"), produtoId));

                TypedQuery<EstoqueMovimento> estoqueMovimentoTypedQuery = this.entityManager.createQuery((estoqueCriteriaQuery));
        return estoqueMovimentoTypedQuery.getResultList();
    }
}