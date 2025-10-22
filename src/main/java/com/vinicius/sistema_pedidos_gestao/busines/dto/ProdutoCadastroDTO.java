package com.vinicius.sistema_pedidos_gestao.busines.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdutoCadastroDTO {

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotBlank(message = "Descroção é obrigatório")
    private String descricao;

    @NotNull(message = "Preço é obrigatório")
    private Float preco;

    @NotBlank(message = "Url é obrigatório")
    private String url;

    @NotBlank(message = "Categoria é obrigatório")
    private String categoria;
}
