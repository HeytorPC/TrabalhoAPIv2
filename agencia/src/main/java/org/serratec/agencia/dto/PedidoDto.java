package org.serratec.agencia.dto;

import java.time.LocalDate;

import org.serratec.agencia.model.MetodoPagamento;
import org.serratec.agencia.model.Pedido;
import org.serratec.agencia.model.StatusPedido;

public record PedidoDto (
		Long id,
		LocalDate dataPedido,
		MetodoPagamento metodoPagamento,
		StatusPedido statusPedido,
		double valorTotal,
		double valorLiquido,
		double valorBruto,
		double desconto
		
			) {

	public Pedido toEntity() {
		Pedido pedido = new Pedido();
		pedido.setId(this.id);
		pedido.setDataPedido(this.dataPedido);
		pedido.setMetodoPagamento(this.metodoPagamento);
		pedido.setStatusPedido(this.statusPedido);
		pedido.setValorTotal(this.valorTotal);
		pedido.setValorLiquido(this.valorLiquido);
		pedido.setValorBruto(this.valorBruto);
		pedido.setDesconto(this.desconto);
		return pedido;
	}
	
	public static PedidoDto toDto(Pedido pedido) {
		return new PedidoDto(pedido.getId(), pedido.getDataPedido(),pedido.getMetodoPagamento(),
				pedido.getStatusPedido(),pedido.getValorTotal(),pedido.getValorLiquido(),pedido.getValorBruto(), pedido.getDesconto());
	}
	
}
