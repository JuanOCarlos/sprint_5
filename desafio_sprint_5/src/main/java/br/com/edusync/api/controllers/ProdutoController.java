package br.com.edusync.api.controllers;

import br.com.edusync.api.models.Produto;
import br.com.edusync.api.repositories.criteria.params.ProdutoFilterParam;
import br.com.edusync.api.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping(value = "/novo")
    @Operation(summary = "Cria um novo produto" , description = "Método que acessa o método criar no service e cria um novo produto")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Produto criado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Produto não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity novoProduto(@RequestBody Produto produto){
        try {
            service.criar(produto);
        }catch (NoSuchElementException e){
            return new ResponseEntity("Não foi possivel criar o produto!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(produto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/listar/{codigo}")
    @Operation(summary = "Lista um produto" , description = "Método que acessa método buscarPorCodigo e lista o produto")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Produto listado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Produto não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity listarFuncionario(@PathVariable Integer codigo){
        try {
            return new ResponseEntity(service.buscarPorCodigo(codigo), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity("Produto "+ codigo + " não localizado", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/atualizar/{codigo}")
    @Operation(summary = "Atualiza um produto" , description = "Método que acessa o métoso atualizar e atualiza o produto")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Produto atualizado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Produto não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity atualizar(@PathVariable Integer codigo, @RequestBody Produto produto){
        try {
            service.atualizar(codigo, produto);
        }catch (NoSuchElementException e){
            return new ResponseEntity("Não foi possivel atualizar o produto! " + codigo, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Produto " + codigo + " atualizado com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletar/{codigo}")
    @Operation(summary = "Deleta um Produto" , description = "Método que acessa o método deletar no service e deleta um funcionario")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Produto deletado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Produto não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity deletar(@PathVariable Integer codigo){
        try {
            service.deletar(codigo);
        }catch (NoSuchElementException e){
            return new ResponseEntity("Não foi possivel deletar o produto " + codigo, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Produto " + codigo + " deletado com sucesso!", HttpStatus.OK);
    }

    @GetMapping(value = "/filtrar")
    @Operation(summary = "Filtra um produto com saldo positivo" , description = "Método que acessa o método filtrar no service e mostra os produtos com saldo positivo")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Produto filtrado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Produto não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity filtrarPositivo(Integer codigo){
        try {
            return new ResponseEntity(service.filtrar(codigo), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity("Produto não localizado!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/filtrarprodutoscadastrados")
    @Operation(summary = "FIltra produtos cadastrados" , description = "Método que acessa o método filtrarProdutosCadastrados e filtra os produtos cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Produto filtrado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Produto não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity filtrarProdutosCadastrados(@RequestParam(required = false) String descricao,
                                                     @RequestParam(required = false) BigDecimal precoVenda,
                                                     @RequestParam(required = false) Integer saldoAtual){
        ProdutoFilterParam params = new ProdutoFilterParam();
        params.setDescricao(descricao);
        params.setSaldoAtual(saldoAtual);
        params.setPrecoVenda(precoVenda);
        try {
            return new ResponseEntity(service.filtrarProdutosCadastrados(params), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity("Produto não localizado!", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(value = "/listar")
    @Operation(summary = "Lista todos os produtos" , description = "Método que acessa o método listarTudo do service e lista todos os produtos")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Produtos listados com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Não foi possivel achar um produtos!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity listarTudo(){
        try {
            return new ResponseEntity(service.listaTudo(), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity("Não foi possivel achar um produtos!", HttpStatus.NOT_FOUND);
        }
    }
}