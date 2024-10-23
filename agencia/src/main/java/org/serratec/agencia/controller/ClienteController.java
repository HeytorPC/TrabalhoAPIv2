package org.serratec.agencia.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.agencia.dto.ClienteDto;
import org.serratec.agencia.service.ClienteService;
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
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService servico;

    @GetMapping
    @Operation(summary = "Buscar todos os Clientes", 
    description = "Retorna uma lista de todos os clientes cadastrados.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Clientes retornados com sucesso"),
    		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<ClienteDto> obterTodos() {
        return servico.buscarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Cliente por ID", 
    description = "Retorna os detalhes de um cliente específico com base no ID fornecido.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Cliente encontrado e retornado com sucesso"),
    		@ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
    		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
})
    public ResponseEntity<ClienteDto> obterPorId(@PathVariable Long id) {
        Optional<ClienteDto> cliente = servico.buscarPorId(id);
        
        if (!cliente.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(cliente.get());
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo Cliente", 
    description = "Cria um novo cliente com base nos dados fornecidos e retorna o cliente criado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<String> cadastrarCliente(@RequestBody @Valid ClienteDto dto) {
        String cpf = dto.cpf(); 
        String email = dto.email(); 

       
        if (cpf == null || cpf.isEmpty()) {
            return new ResponseEntity<>("Erro: O CPF não pode estar vazio.", HttpStatus.BAD_REQUEST);
        }
        
        if (cpf == null || cpf.length() != 11) {
            return new ResponseEntity<>("Erro: O CPF deve ter exatamente 11 dígitos.", HttpStatus.BAD_REQUEST);
        }

       
        if (email == null || email.isEmpty()) {
            return new ResponseEntity<>("Erro: O e-mail não pode estar vazio.", HttpStatus.BAD_REQUEST);
        }

        
        if (!servico.ehEmailUnico(email)) {
            return new ResponseEntity<>("Erro: O e-mail já está em uso.", HttpStatus.BAD_REQUEST);
        }

        ClienteDto clienteCriado = servico.salvarCliente(dto);
        return new ResponseEntity<>("Cliente cadastrado com sucesso: " + clienteCriado, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um Cliente", 
    description = "Exclui um cliente existente com base no ID fornecido.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "204", description = "Cliente excluído com sucesso"),
    		@ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
    		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> excluirCliente(@PathVariable Long id) {
        if (!servico.apagarCliente(id)) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterar um Cliente", 
    description = "Altera os dados de um cliente existente com base no ID fornecido.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Cliente alterado com sucesso"),
    		@ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
    		@ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
    		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<ClienteDto> alterarCliente(@PathVariable Long id, @RequestBody @Valid ClienteDto dto) {
        Optional<ClienteDto> clienteAlterado = servico.modificarCliente(id, dto);
        
        if (!clienteAlterado.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(clienteAlterado.get());
    }
}