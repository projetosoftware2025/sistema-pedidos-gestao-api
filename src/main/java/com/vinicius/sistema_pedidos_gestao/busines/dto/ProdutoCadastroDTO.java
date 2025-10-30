package com.vinicius.sistema_pedidos_gestao.busines.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class ProdutoCadastroDTO {

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotBlank(message = "Descroção é obrigatório")
    private String descricao;

    @NotNull(message = "Preço é obrigatório")
    private Float preco;

    // Recebe a imagem no formato multipart
    private MultipartFile imagem;

    @NotBlank(message = "Categoria é obrigatório")
    private String categoria;
}
