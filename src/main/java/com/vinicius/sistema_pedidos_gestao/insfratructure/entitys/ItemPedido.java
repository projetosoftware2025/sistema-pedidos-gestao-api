package com.vinicius.sistema_pedidos_gestao.insfratructure.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Table(name = "itensPedido")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Está correto para Integer
    @NotNull
    private Integer idProduto;

    // Está correto para Integer
    @NotNull
    private Integer idPedido;

    // Usar apenas @NotBlank para String, pois já implica que não é nulo.
    @NotBlank
    private String titulo;

    @NotNull
    private Float valorUnitario;

    @NotNull
    private Float quantidade;

}
