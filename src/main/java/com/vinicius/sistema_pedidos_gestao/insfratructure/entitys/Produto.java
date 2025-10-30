package com.vinicius.sistema_pedidos_gestao.insfratructure.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@org.hibernate.annotations.Proxy(lazy = true)
@Table(name = "produtos") // ← Evita o hífen no nome da tabela (Postgres não gosta)
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

    // ⚡ Imagem armazenada em bytes, carregamento preguiçoso (não vem junto na listagem)
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "imagem")
    private byte[] imagem;

    @NotNull
    private String categoriaId;

    private Boolean ativo;
}
