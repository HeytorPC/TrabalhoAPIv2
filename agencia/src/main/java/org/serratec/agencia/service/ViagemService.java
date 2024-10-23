package org.serratec.agencia.service;

import java.util.List;
import java.util.Optional;

import org.serratec.agencia.dto.ViagemDto;
import org.serratec.agencia.model.Viagem;
import org.serratec.agencia.repository.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViagemService {

    @Autowired
    private ViagemRepository repositorio;

    public List<ViagemDto> buscarTodos() {
        return repositorio.findAll().stream().map(v -> ViagemDto.toDto(v)).toList();
    }

    public Optional<ViagemDto> buscarPorId(Long id) {
        if (!repositorio.existsById(id)) {
            return Optional.empty();
        }
        return Optional.of(ViagemDto.toDto(repositorio.findById(id).get()));
    }

    public ViagemDto salvarViagem(ViagemDto dto) {
        Viagem viagemEntity = repositorio.save(dto.toEntity());
        return ViagemDto.toDto(viagemEntity);
    }

    public boolean apagarViagem(Long id) {
        if (!repositorio.existsById(id)) {
            return false;
        }
        repositorio.deleteById(id);
        return true;
    }

    public Optional<ViagemDto> modificarViagem(Long id, ViagemDto dto) {
        if (!repositorio.existsById(id)) {
            return Optional.empty();
        }
        Viagem viagemEntity = dto.toEntity();
        viagemEntity.setId(id);
        repositorio.save(viagemEntity);
        return Optional.of(ViagemDto.toDto(viagemEntity));
    }
}
