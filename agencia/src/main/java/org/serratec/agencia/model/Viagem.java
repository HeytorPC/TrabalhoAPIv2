package org.serratec.agencia.model;

import java.time.LocalDate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity 
@Table(name = "viagens")
public class Viagem {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String destino; 
    
    @NotNull
    private LocalDate dataViagem;
    
    @NotNull
    @Positive
    private double valorUnitario;
    
    @Enumerated(EnumType.STRING)
    private StatusViagem statusViagem;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Voo voo;


    
    public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getDestino() {
		return destino;
	}



	public void setDestino(String destino) {
		this.destino = destino;
	}



	public double getValorUnitario() {
		return valorUnitario;
	}



	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}



	public StatusViagem getStatusViagem() {
		return statusViagem;
	}



	public void setStatusViagem(StatusViagem statusViagem) {
		this.statusViagem = statusViagem;
	}



	public Voo getVoo() {
		return voo;
	}



	public void setVoo(Voo voo) {
		this.voo = voo;
	}



	public LocalDate getDataViagem() {
		return dataViagem;
	}



	public void setDataViagem(LocalDate dataViagem) {
        if (dataViagem.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data da viagem não pode ser retroativa.");
        }
        this.dataViagem = dataViagem;
    }
}
