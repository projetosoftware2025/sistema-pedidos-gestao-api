package com.vinicius.sistema_pedidos_gestao.busines.dto;

public record ProdutoResponseDTO(
  Integer id,
  String titulo,
  String descricao,
  Float preco,
  String categoriaId,
  String urlImagem, // URL para acessar a imagem
  Boolean ativo
) {}
