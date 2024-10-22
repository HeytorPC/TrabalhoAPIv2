package org.serratec.agencia.dto;
import java.time.LocalDate;

import org.serratec.agencia.model.StatusViagem;
import org.serratec.agencia.model.Viagem;

public record ViagemDto (
    Long id,
    String destino,
    LocalDate dataViagem,
    double valorUnitario,
    StatusViagem statusViagem
) {

    public Viagem toEntity() {
        Viagem viagem = new Viagem();
        viagem.setId(this.id);
        viagem.setDestino(this.destino);
        viagem.setDataViagem(this.dataViagem);
        viagem.setValorUnitario(this.valorUnitario);
        viagem.setStatusViagem(this.statusViagem);
        return viagem;
    }

    public static ViagemDto toDto(Viagem viagem) {
        return new ViagemDto(viagem.getId(), viagem.getDestino(), viagem.getDataViagem(), 
        		viagem.getValorUnitario(), viagem.getStatusViagem());
    }
}

