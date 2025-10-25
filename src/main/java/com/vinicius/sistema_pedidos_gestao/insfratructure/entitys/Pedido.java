package com.vinicius.sistema_pedidos_gestao.insfratructure.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @NotNull
    private String cliente;

    @NotBlank
    @NotNull
    private String cpf;

    @NotBlank
    private String telefone;

    private LocalDate dtPedido;

    private String status;

    private LocalDate dtFinalizacao;

    private LocalDate dtCancelamento;

    @NotNull
    private String formaPagamento;

    @NotNull
    private Float totalItens;

    @NotNull
    private Float valorTotal;

}
