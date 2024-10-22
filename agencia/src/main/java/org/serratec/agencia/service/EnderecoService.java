package org.serratec.agencia.service;

import java.util.List;
import java.util.Optional;

import org.serratec.agencia.model.Endereco;
import org.serratec.agencia.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository repositorio;

    public Endereco salvarEndereco(Endereco endereco) {
        return repositorio.save(endereco);
    }

    public Optional<Endereco> modificarEndereco(Long id, Endereco endereco) {
        if (!repositorio.existsById(id)) {
            return Optional.empty();
        }
        endereco.setId(id);
        return Optional.of(repositorio.save(endereco));
    }

    public boolean apagarEndereco(Long id) {
        if (!repositorio.existsById(id)) {
            return false;
        }
        repositorio.deleteById(id);
        return true;
    }

    public List<Endereco> buscarTodos() {
        return repositorio.findAll();
    }

    public Optional<Endereco> buscarPorId(Long id) {
        if (!repositorio.existsById(id)) {
            return Optional.empty();
        }
        return repositorio.findById(id);
    }
}
