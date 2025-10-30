package com.vinicius.sistema_pedidos_gestao.insfratructure.repository;

import com.vinicius.sistema_pedidos_gestao.insfratructure.entitys.Pedido;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime; // <--- Usando LocalDateTime conforme o erro
import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Transactional
    void deleteById(Integer id);

    // 🔹 1. Buscar pedidos entre duas datas (sem CPF)
    @Query("""
        SELECT p
        FROM Pedido p
        WHERE p.dtPedido BETWEEN :dtInicio AND :dtFim
    """)
    List<Pedido> findAllByDtPedidoBetween(
            @Param("dtInicio") LocalDateTime dtInicio,
            @Param("dtFim") LocalDateTime dtFim
    );

    // 🔹 2. Buscar pedidos entre duas datas e por CPF específico
    @Query("""
        SELECT p
        FROM Pedido p
        WHERE p.dtPedido BETWEEN :dtInicio AND :dtFim
          AND p.cpf = :cpf
    """)
    List<Pedido> findAllByDtPedidoBetweenAndCpf(
            @Param("dtInicio") LocalDateTime dtInicio,
            @Param("dtFim") LocalDateTime dtFim,
            @Param("cpf") String cpf
    );

    Optional<Pedido> findByCpfAndStatus(String cpf, String status);
}