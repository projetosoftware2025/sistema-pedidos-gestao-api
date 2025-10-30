package com.vinicius.sistema_pedidos_gestao.busines.dto;

public record CategoriaResponseDTO(
  Integer id,
  String descricao,
  String urlImagem,
  Boolean ativo
) {}