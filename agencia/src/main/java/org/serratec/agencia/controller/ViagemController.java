package org.serratec.agencia.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.agencia.model.Viagem;
import org.serratec.agencia.service.ViagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/viagens")
public class ViagemController {

    @Autowired
    private ViagemService viagemService;

    @GetMapping
    public List<Viagem> obterTodas() {
        return viagemService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viagem> obterPorId(@PathVariable Long id) {
        Optional<Viagem> viagem = viagemService.buscarPorId(id);
        
        if (!viagem.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(viagem.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viagem cadastrarViagem(@RequestBody @Valid Viagem viagem) {
        return viagemService.salvarViagem(viagem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirViagem(@PathVariable Long id) {
        if (!viagemService.apagarViagem(id)) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Viagem> alterarViagem(@PathVariable Long id, 
                                                 @RequestBody @Valid Viagem viagem) {
        Optional<Viagem> viagemAlterada = viagemService.modificarViagem(id, viagem);
        
        if (!viagemAlterada.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(viagemAlterada.get());
    }
}
