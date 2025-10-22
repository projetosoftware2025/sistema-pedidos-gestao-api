package com.vinicius.sistema_pedidos_gestao.busines.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoriaCadastroDTO {

    @NotBlank(message = "Descrição é obrigatório")
    private String descricao;

    @NotBlank(message = "Url é obrigatório")
    private String url;

}
