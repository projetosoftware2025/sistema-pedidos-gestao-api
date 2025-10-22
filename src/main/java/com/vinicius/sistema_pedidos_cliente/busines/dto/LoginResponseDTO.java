package com.vinicius.sistema_pedidos_cliente.busines.dto;

public record LoginResponseDTO(
        boolean logado,
        String mensagem,
        String usuario,
        String email
) {}
