package com.vinicius.sistema_pedidos_gestao.busines.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemPedidoCadastroDTO {

    @NotNull(message = "O ID do produto não pode ser nulo.")
    private Integer idProduto;

    @NotNull(message = "O ID do pedido não pode ser nulo.")
    private Integer idPedido;

    @NotBlank(message = "O título é obrigatório.")
    private String titulo;

    @NotNull(message = "O valor unitário não pode ser nulo.")
    private Float valorUnitario;

    @NotNull(message = "A quantidade não pode ser nula.")
    private Float quantidade;
}
