package org.serratec.agencia.service;

import java.util.List;
import java.util.Optional;

import org.serratec.agencia.model.Voo;
import org.serratec.agencia.repository.VooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VooService {

    @Autowired
    private VooRepository repositorio;

    public Voo salvarVoo(Voo voo) {
        return repositorio.save(voo);
    }

    public Optional<Voo> modificarVoo(Long id, Voo voo) {
        if (!repositorio.existsById(id)) {
            return Optional.empty();
        }
        voo.setId(id);
        return Optional.of(repositorio.save(voo));
    }

    public boolean apagarVoo(Long id) {
        if (!repositorio.existsById(id)) {
            return false;
        }
        repositorio.deleteById(id);
        return true;
    }

    public List<Voo> buscarTodos() {
        return repositorio.findAll();
    }

    public Optional<Voo> buscarPorId(Long id) {
        if (!repositorio.existsById(id)) {
            return Optional.empty();
        }
        return Optional.of(repositorio.findById(id).get());
    }
}
