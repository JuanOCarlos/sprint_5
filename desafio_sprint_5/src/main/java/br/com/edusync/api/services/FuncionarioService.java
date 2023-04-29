package br.com.edusync.api.services;

import br.com.edusync.api.models.FuncionarioResponsavel;
import br.com.edusync.api.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public void criar(FuncionarioResponsavel funcionario){
        funcionarioRepository.save(funcionario);
    }

    public FuncionarioResponsavel buscarPorCodigo(Integer codigo){
        Optional<FuncionarioResponsavel> optFuncionario = funcionarioRepository.findById(codigo);
        if (optFuncionario.isEmpty()){
            return null;
        }
        return optFuncionario.get();
    }

    public void deletar(Integer codigo){
        funcionarioRepository.deleteById(codigo);
    }

    public void atualizar(Integer codigo, FuncionarioResponsavel funcionario){
        if (funcionarioRepository.existsById(codigo)) {
            funcionarioRepository.save(funcionario);
        }
    }
    public List<FuncionarioResponsavel> listarTudo(){
        return funcionarioRepository.findAll();
    }
}
