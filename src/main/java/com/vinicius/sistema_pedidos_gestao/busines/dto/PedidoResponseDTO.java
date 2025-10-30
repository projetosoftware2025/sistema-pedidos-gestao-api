package com.vinicius.sistema_pedidos_gestao.busines.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record PedidoResponseDTO(
        Integer id,
        String status,
        LocalDateTime dtPedido
) {
}
