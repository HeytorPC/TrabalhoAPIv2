package org.serratec.agencia.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.agencia.model.Voo;
import org.serratec.agencia.service.VooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/voos")
public class VooController {

    @Autowired
    private VooService vooService;

    @GetMapping
    @Operation(summary = "Buscar todos os Voos", 
    description = "Recupera uma lista de todos os voos disponíveis.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Lista de voos retornada com sucesso"),
    		@ApiResponse(responseCode = "500", description = "Erro interno no servidor ao tentar recuperar os voos")
    })
    public List<Voo> obterTodos() {
        return vooService.buscarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Voo por ID", 
    description = "Recupera as informações detalhadas de um voo específico com base no ID fornecido.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Voo encontrado e retornado com sucesso"),
    		@ApiResponse(responseCode = "404", description = "Voo não encontrado. O ID fornecido não corresponde a nenhum voo registrado."),
    		@ApiResponse(responseCode = "500", description = "Erro interno no servidor ao tentar buscar o voo")
    })
    public ResponseEntity<Voo> obterPorId(@PathVariable Long id) {
        Optional<Voo> voo = vooService.buscarPorId(id);
        
        if (!voo.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(voo.get());
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo Voo", 
    description = "Adiciona um novo voo ao sistema com base nos dados fornecidos. "
                + "Os dados devem incluir informações válidas, como o destino, a data e o horário do voo.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "201", description = "Voo criado com sucesso"),
    		@ApiResponse(responseCode = "400", description = "Erro de validação. Um ou mais campos fornecidos são inválidos."),
    		@ApiResponse(responseCode = "500", description = "Erro interno no servidor ao tentar cadastrar o voo")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public Voo cadastrarVoo(@RequestBody @Valid Voo voo) {
        return vooService.salvarVoo(voo);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um Voo", 
    description = "Remove um voo existente com base no ID fornecido. "
                + "Se o ID não for encontrado, retorna um erro. A exclusão é permanente.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "204", description = "Voo excluído com sucesso"),
    		@ApiResponse(responseCode = "404", description = "Voo não encontrado. O ID fornecido não corresponde a nenhum voo registrado."),
    		@ApiResponse(responseCode = "500", description = "Erro interno no servidor ao tentar excluir o voo")
    })
    public ResponseEntity<Void> excluirVoo(@PathVariable Long id) {
        if (!vooService.apagarVoo(id)) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterar um Voo", 
    description = "Atualiza as informações de um voo existente com base no ID fornecido. "
                + "Os dados fornecidos no corpo da requisição devem ser válidos. Se o ID não for encontrado, será retornado um erro.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Voo alterado com sucesso"),
    		@ApiResponse(responseCode = "400", description = "Erro de validação. Um ou mais campos fornecidos são inválidos."),
    		@ApiResponse(responseCode = "404", description = "Voo não encontrado. O ID fornecido não corresponde a nenhum voo registrado."),
    		@ApiResponse(responseCode = "500", description = "Erro interno no servidor ao tentar alterar o voo")
    })
    public ResponseEntity<Voo> alterarVoo(@PathVariable Long id, 
                                           @RequestBody @Valid Voo voo) {
        Optional<Voo> vooAlterado = vooService.modificarVoo(id, voo);
        
        if (!vooAlterado.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(vooAlterado.get());
    }
}