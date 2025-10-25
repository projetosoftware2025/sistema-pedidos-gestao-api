package com.vinicius.sistema_pedidos_gestao.insfratructure.repository;

import com.vinicius.sistema_pedidos_gestao.insfratructure.entitys.Pedido;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    @Transactional
    void deleteById(Integer id);

    List<Pedido> findAllByDtPedidoBetweenAndCpf(LocalDate dtInicio, LocalDate dtFim, String cpf);

    Optional<Pedido> findByCpfAndStatus(String cpf, String status);

}
