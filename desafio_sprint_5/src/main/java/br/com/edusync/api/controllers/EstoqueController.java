package br.com.edusync.api.controllers;

import br.com.edusync.api.models.EstoqueMovimento;
import br.com.edusync.api.models.FuncionarioResponsavel;
import br.com.edusync.api.models.Produto;
import br.com.edusync.api.services.EstoqueService;
import br.com.edusync.api.services.FuncionarioService;
import br.com.edusync.api.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService service;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping(value = "/novo")
    @Operation(summary = "Cria um novo estoque" , description = "Método que acessa o método criar do service e cria um novo estoque")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "Created - Estoque criado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Estoque não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado")
    })
    public ResponseEntity novoEstoque(@RequestBody EstoqueMovimento estoque,
                                      @RequestParam Integer produtoId,
                                      @RequestParam Integer funcionarioId){

        Produto produto = produtoService.buscarPorCodigo(produtoId);
        estoque.setProduto(produto);
        FuncionarioResponsavel funcionario = funcionarioService.buscarPorCodigo(funcionarioId);
        estoque.setFuncionario(funcionario);

        try {
            service.criar(estoque);
        }catch (NoSuchElementException e){
            return new ResponseEntity("Não foi possivel cadastrar estoque", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Estoque cadastrado com sucesso!", HttpStatus.CREATED);
    }

    @GetMapping(value = "/listar/{codigo}")
    @Operation(summary = "Lista um estoque" , description = "Método que acessa o método buscarPorCodigo do service e lista o estoque")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Estoque listado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Lista de estoque não localizada!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity listarEstoque(@PathVariable Integer codigo){
        try {
            return new ResponseEntity(service.buscarPorCodigo(codigo), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity("Lista de estoque não localizada!", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/atualizar/{codigo}")
    @Operation(summary = "Atualiza um estoque" , description = "Método que acessa o método atualizar do service e atualiza o estoque")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Estoque atualizado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Não foi possivel achar um estoque!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity atualizar(@PathVariable Integer codigo, @RequestBody EstoqueMovimento estoque){
        service.atualizar(codigo, estoque);
        try {
            return new ResponseEntity("Estoque " + codigo + " atualizado!", HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity("Não foi possivel achar o estoque!", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deletar/{codigo}")
    @Operation(summary = "Deleta um estoque" , description = "Método que acessa o método deletar do service e deleta o estoque")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Estoque deletado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Não foi possivel achar um estoque!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity deletar(@PathVariable Integer codigo){
        try {
            return new ResponseEntity("Estoque " + codigo + " deletado!", HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity("Não foi possivel achar um estoque!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/listarhistorico")
    @Operation(summary = "Lista o historico de movimento do estoque" , description = "Método que acessa o método listarHistoricoDeMovimento do service e lista o histórico")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Histórico listado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Não foi possivel achar um histórico!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity listarHistoricoDeMovimento(Integer produtoId){
        try {
            return new ResponseEntity(service.listarHistoricoDeMovimento(produtoId), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity("Não foi possivel achar um histórico!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/listar")
    @Operation(summary = "Lista todos os estoques" , description = "Método que acessa o método listarTodos do service e lista todos os histórico")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Históricos listados com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Não foi possivel achar um histórico!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity listarTudo(){
        try {
            return new ResponseEntity(service.listarTodos(), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity("Não foi possivel achar um histórico!", HttpStatus.NOT_FOUND);
        }
    }
}