package org.serratec.agencia.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.agencia.model.Voo;
import org.serratec.agencia.service.VooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/voos")
public class VooController {

    @Autowired
    private VooService vooService;

    @GetMapping
    public List<Voo> obterTodos() {
        return vooService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voo> obterPorId(@PathVariable Long id) {
        Optional<Voo> voo = vooService.buscarPorId(id);
        
        if (!voo.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(voo.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Voo cadastrarVoo(@RequestBody @Valid Voo voo) {
        return vooService.salvarVoo(voo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirVoo(@PathVariable Long id) {
        if (!vooService.apagarVoo(id)) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voo> alterarVoo(@PathVariable Long id, 
                                           @RequestBody @Valid Voo voo) {
        Optional<Voo> vooAlterado = vooService.modificarVoo(id, voo);
        
        if (!vooAlterado.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(vooAlterado.get());
    }
}
