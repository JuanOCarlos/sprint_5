package br.com.edusync.api.services;

import br.com.edusync.api.models.EstoqueMovimento;
import br.com.edusync.api.models.Produto;
import br.com.edusync.api.repositories.EstoqueRepository;
import br.com.edusync.api.repositories.criteria.params.impl.EstoqueRepositoryCustomImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private EstoqueRepositoryCustomImpl estoqueRepositoryCustom;

    @Autowired
    private ProdutoService produtoService;

    public void criar(EstoqueMovimento estoqueMovimento){

        Produto produto = produtoService.buscarPorCodigo(estoqueMovimento.getProduto().getCodigo());
        if (estoqueMovimento.getTipoMovimento().equals("E")){

            produto.setSaldoAtual(produto.getSaldoAtual() + estoqueMovimento.getQuantidade());
            produtoService.atualizar(produto.getCodigo(), produto);
        }else {
            produto.setSaldoAtual(produto.getSaldoAtual() - estoqueMovimento.getQuantidade());
            produtoService.atualizar(produto.getCodigo(), produto);
        }
        estoqueRepository.save(estoqueMovimento);
    }
    public EstoqueMovimento buscarPorCodigo(Integer codigo){
        Optional<EstoqueMovimento> optEstoque = estoqueRepository.findById(codigo);
        if (optEstoque.isEmpty()){
            return null;
        }
            return optEstoque.get();
    }

    public void deletar(Integer codigo){
        estoqueRepository.deleteById(codigo);
    }

    public void atualizar(Integer codigo, EstoqueMovimento estoque){
        if (estoqueRepository.existsById(codigo)){
            estoqueRepository.save(estoque);
        }
    }

    public List<EstoqueMovimento> listarHistoricoDeMovimento(Integer produtoId) {
        return estoqueRepositoryCustom.listarHistoricoDeMovimento(produtoId);
    }
    public List<EstoqueMovimento> listarTodos() {
        return estoqueRepository.findAll();
    }
}
