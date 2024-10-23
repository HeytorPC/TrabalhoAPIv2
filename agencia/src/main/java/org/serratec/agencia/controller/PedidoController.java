package org.serratec.agencia.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.serratec.agencia.dto.PedidoDto;
import org.serratec.agencia.service.PedidoService;
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
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService servico;

    @GetMapping
    public List<PedidoDto> obterTodos() {
        return servico.buscarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Pedido por ID", 
    description = "Retorna os detalhes de um pedido específico com base no ID fornecido.")
    @ApiResponses(value = { 
    		@ApiResponse(responseCode = "404", description = "Não foi encontrado o pedido pelo id informado. Verifique!"),
    		@ApiResponse(responseCode = "200", description = "Pedido localizado!")
    })
    public ResponseEntity<PedidoDto> obterPorId(@PathVariable Long id) {
        Optional<PedidoDto> pedido = servico.buscarPorId(id);
        
        if (!pedido.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(pedido.get());
    }
    @PostMapping
    @Operation(summary = "Cadastrar um novo Pedido", 
    description = "Cria um novo pedido com base nos dados fornecidos e retorna o pedido criado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos ou data retroativa"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<String> cadastrarPedido(@RequestBody @Valid PedidoDto dto) {
        LocalDate dataPedido = dto.dataPedido();

      
        if (dataPedido.isBefore(LocalDate.now())) {
        
            return new ResponseEntity<>("Erro: A data do pedido não pode ser retroativa.", HttpStatus.BAD_REQUEST);
        }

        PedidoDto pedidoCriado = servico.salvarPedido(dto);
        return new ResponseEntity<>("Pedido criado com sucesso: " + pedidoCriado, HttpStatus.CREATED);
    }
    

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um Pedido", 
    description = "Exclui um pedido existente com base no ID fornecido. Retorna 204 se a exclusão for bem-sucedida.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "204", description = "Pedido excluído com sucesso"),
    		@ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
    		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> excluirPedido(@PathVariable Long id) {
        if (!servico.apagarPedido(id)) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterar um Pedido", 
    description = "Altera os dados de um pedido existente com base no ID fornecido.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Pedido alterado com sucesso"),
    		@ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
    		@ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
    		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<PedidoDto> alterarPedido(@PathVariable Long id, @RequestBody @Valid PedidoDto dto) {
        Optional<PedidoDto> pedidoAlterado = servico.modificarPedido(id, dto);
        
        if (!pedidoAlterado.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(pedidoAlterado.get());
    }
}