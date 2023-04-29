package br.com.edusync.api.repositories;

import br.com.edusync.api.models.EstoqueMovimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRepository extends JpaRepository<EstoqueMovimento, Integer> {
}
