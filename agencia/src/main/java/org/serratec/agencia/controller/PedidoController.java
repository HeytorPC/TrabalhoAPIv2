package org.serratec.agencia.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.agencia.dto.PedidoDto;
import org.serratec.agencia.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<PedidoDto> obterPorId(@PathVariable Long id) {
        Optional<PedidoDto> pedido = servico.buscarPorId(id);
        
        if (!pedido.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(pedido.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDto cadastrarPedido(@RequestBody @Valid PedidoDto dto) {
        return servico.salvarPedido(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPedido(@PathVariable Long id) {
        if (!servico.apagarPedido(id)) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDto> alterarPedido(@PathVariable Long id, @RequestBody @Valid PedidoDto dto) {
        Optional<PedidoDto> pedidoAlterado = servico.modificarPedido(id, dto);
        
        if (!pedidoAlterado.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(pedidoAlterado.get());
    }
}

