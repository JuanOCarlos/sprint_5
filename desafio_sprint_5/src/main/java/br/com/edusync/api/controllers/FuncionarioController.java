package br.com.edusync.api.controllers;

import br.com.edusync.api.models.FuncionarioResponsavel;
import br.com.edusync.api.services.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @PostMapping(value = "/novo")
    @Operation(summary = "Cria um novo funcionario" , description = "Método que acessa o método criar no service e cria um novo funcionario")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Funcionario criado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Funcionario não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity funcionarioNovo(@RequestBody FuncionarioResponsavel funcionario){
        try {
            service.criar(funcionario);
        }catch (NoSuchElementException e){
            return new ResponseEntity("Não foi possivel cadastrar um funcionario!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(funcionario, HttpStatus.CREATED);
    }

    @GetMapping(value = "/listar/{codigo}")
    @Operation(summary = "Lista um funcionario" , description = "Método que acessa o método buscarPorCodigo no service e lista o seguinte funcionario")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Funcionario listado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Funcionario não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity listarPorCodigo(@PathVariable Integer codigo){
        try {
            return new ResponseEntity(service.buscarPorCodigo(codigo), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity("Funcionario não localizado!", HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping(value = "/atualizar/{codigo}")
    @Operation(summary = "Atualiza um funcionario" , description = "Método que acessa o método atualizar no service e atualiza o funcionario")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Funcionario atualizado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Funcionario não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity atualizarFuncionario(@PathVariable Integer codigo, @RequestBody FuncionarioResponsavel funcionario){
        try {
            service.atualizar(codigo, funcionario);
        }catch (NoSuchElementException e){
            return new ResponseEntity("Não foi possivel atualizar o funcionario!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Funcionario " + codigo + " atualizado com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletar/{codigo}")
    @Operation(summary = "Deleta um funcionario" , description = "Método que acessa o método deletar e deleta um funcionario")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Funcionario deletado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Funcionario não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity deletar(@PathVariable Integer codigo){
        try {
            service.deletar(codigo);
        }catch (NoSuchElementException e){
            return new ResponseEntity("Não foi possivel deletar o funcionario!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Funcionario " + codigo + " deletado com sucesso!", HttpStatus.OK);
    }

    @GetMapping(value = "/listar")
    @Operation(summary = "Lista todos os funcionarios" , description = "Método que acessa o método listarTodos do service e lista todos os funcionarios")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Funcionarios listados com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Não foi possivel achar um Funcionario!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity listarTudo(){
        try {
            return new ResponseEntity(service.listarTudo(), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity("Não foi possivel achar um Funcionario!", HttpStatus.NOT_FOUND);
        }

    }
}
