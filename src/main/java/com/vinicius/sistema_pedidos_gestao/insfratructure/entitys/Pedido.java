package com.vinicius.sistema_pedidos_gestao.insfratructure.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @NotNull
    private String cliente;

    @NotNull
    private String cpf;

    @NotBlank
    private String telefone;

    private LocalDateTime dtPedido;

    private String status;

    private LocalDate dtFinalizacao;

    private LocalDate dtCancelamento;

    @NotNull
    private String formaPagamento;


}
