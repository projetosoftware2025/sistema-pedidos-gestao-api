package com.vinicius.sistema_pedidos_gestao.insfratructure.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "produtos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @NotNull
    private String titulo;

    @NotBlank
    @NotNull
    private String descricao;

    @NotNull
    private Float preco;

    @NotBlank
    @NotNull
    private String url;

    @NotNull
    private String categoriaId;

    private Boolean ativo;
}
