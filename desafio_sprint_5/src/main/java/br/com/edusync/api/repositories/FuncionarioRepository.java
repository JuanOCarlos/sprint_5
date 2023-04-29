package br.com.edusync.api.repositories;

import br.com.edusync.api.models.FuncionarioResponsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioResponsavel, Integer> {
}
