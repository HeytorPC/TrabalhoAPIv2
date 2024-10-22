package org.serratec.agencia.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.agencia.dto.ClienteDto;
import org.serratec.agencia.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService servico;

    @GetMapping
    public List<ClienteDto> obterTodos() {
        return servico.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> obterPorId(@PathVariable Long id) {
        Optional<ClienteDto> cliente = servico.buscarPorId(id);
        
        if (!cliente.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(cliente.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDto cadastrarCliente(@RequestBody @Valid ClienteDto dto) {
        return servico.salvarCliente(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable Long id) {
        if (!servico.apagarCliente(id)) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> alterarCliente(@PathVariable Long id, @RequestBody @Valid ClienteDto dto) {
        Optional<ClienteDto> clienteAlterado = servico.modificarCliente(id, dto);
        
        if (!clienteAlterado.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(clienteAlterado.get());
    }
}
