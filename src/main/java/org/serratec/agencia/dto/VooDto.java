package org.serratec.agencia.dto;

import org.serratec.agencia.model.Voo;
import org.serratec.agencia.model.StatusVoo;

public record VooDto(
    Long id,
    String companhia,
    StatusVoo statusVoo
) {

    public Voo toEntity() {
        Voo voo = new Voo();
        voo.setId(this.id);
        voo.setCompanhia(this.companhia);
        voo.setStatusVoo(this.statusVoo);
        return voo;
    }

    public static VooDto toDto(Voo voo) {
        return new VooDto(voo.getId(), voo.getCompanhia(), voo.getStatusVoo());
    }
}
