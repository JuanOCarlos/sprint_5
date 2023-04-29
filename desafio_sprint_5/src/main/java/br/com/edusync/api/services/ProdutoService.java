package br.com.edusync.api.services;

import br.com.edusync.api.models.Produto;
import br.com.edusync.api.repositories.ProdutoRepository;
import br.com.edusync.api.repositories.criteria.params.ProdutoFilterParam;
import br.com.edusync.api.repositories.criteria.params.impl.ProdutoRepositoryCustomImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoRepositoryCustomImpl produtoRepositoryCustom;

    public void criar(Produto produto){
        produtoRepository.save(produto);
    }

    public Produto buscarPorCodigo(Integer codigo){
        Optional<Produto> optProduto = produtoRepository.findById(codigo);
            if (optProduto.isEmpty()){
                return null;
            }
            return optProduto.get();
    }

    public void deletar(Integer codigo){
        produtoRepository.deleteById(codigo);
    }

    public void atualizar(Integer codigo, Produto produto){
        if (produtoRepository.existsById(codigo)){
            produtoRepository.save(produto);
        }
    }

    public List<Produto> filtrar(Integer codigo) {
       return produtoRepositoryCustom.listarPositivo(codigo);
    }
    public List<Produto> filtrarProdutosCadastrados(ProdutoFilterParam produtoFilterParam){
        return produtoRepositoryCustom.filtrarProdutosCadastrados(produtoFilterParam);
    }
    public List<Produto> listaTudo(){
        return produtoRepository.findAll();
    }
}

