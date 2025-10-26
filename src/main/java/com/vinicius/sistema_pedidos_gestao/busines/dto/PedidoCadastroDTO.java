package com.vinicius.sistema_pedidos_gestao.busines.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PedidoCadastroDTO {

    @NotBlank
    @NotNull
    private String cliente;

    @NotNull
    private String cpf;

    @NotBlank
    private String telefone;

    private LocalDate dtPedido;

    private String status;

    @NotBlank
    private String formaPagamento;

    @NotNull
    private Float totalItens;

    @NotNull
    private Float valorTotal;
}
