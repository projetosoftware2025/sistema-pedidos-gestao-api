package com.vinicius.sistema_pedidos_gestao.insfratructure.entitys;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categorias")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descricao;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] imagem; // armazena a imagem em bytes

    private Boolean ativo;
}
