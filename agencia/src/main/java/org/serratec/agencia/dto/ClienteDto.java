package org.serratec.agencia.dto;
import java.time.LocalDate;

import org.serratec.agencia.model.Cliente;
import org.serratec.agencia.model.Endereco;

public record ClienteDto (
    Long id,
    String nome,
    String cpf,
    LocalDate dataNascimento,
    String telefone,
    String email,
    Endereco endereco
) {

    public Cliente toEntity() {
        Cliente cliente = new Cliente();
        cliente.setId(this.id);
        cliente.setNome(this.nome);
        cliente.setCpf(this.cpf);
        cliente.setDataNascimento(this.dataNascimento);
        cliente.setTelefone(this.telefone);
        cliente.setEmail(this.email);
        cliente.setEndereco(this.endereco);
        return cliente;
    }

    public static ClienteDto toDto(Cliente cliente) {
        return new ClienteDto( cliente.getId(), cliente.getNome(), cliente.getCpf(), 
        		cliente.getDataNascimento(), cliente.getTelefone(), cliente.getEmail(), 
        		cliente.getEndereco());
    }
}
