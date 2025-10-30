package com.vinicius.sistema_pedidos_gestao.busines.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CategoriaCadastroDTO {

    private String descricao;

    private MultipartFile imagem; // imagem enviada no upload
}
