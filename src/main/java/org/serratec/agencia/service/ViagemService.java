package org.serratec.agencia.service;

import java.util.List;
import java.util.Optional;

import org.serratec.agencia.model.Viagem;
import org.serratec.agencia.repository.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViagemService {

    @Autowired
    private ViagemRepository repositorio;

    public Viagem salvarViagem(Viagem viagem) {
        return repositorio.save(viagem);
    }

    public Optional<Viagem> modificarViagem(Long id, Viagem viagem) {
        if (!repositorio.existsById(id)) {
            return Optional.empty();
        }
        viagem.setId(id);
        return Optional.of(repositorio.save(viagem));
    }

    public boolean apagarViagem(Long id) {
        if (!repositorio.existsById(id)) {
            return false;
        }
        repositorio.deleteById(id);
        return true;
    }

    public List<Viagem> buscarTodos() {
        return repositorio.findAll();
    }

    public Optional<Viagem> buscarPorId(Long id) {
        if (!repositorio.existsById(id)) {
            return Optional.empty();
        }
        return Optional.of(repositorio.findById(id).get());
    }
}
