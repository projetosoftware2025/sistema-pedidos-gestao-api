package com.vinicius.sistema_pedidos_gestao.insfratructure.repository;
import com.vinicius.sistema_pedidos_gestao.insfratructure.entitys.ItemPedido;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
    @Transactional
    void deleteById(Integer id);

    List<ItemPedido> findAllByIdPedido(Integer idPedido);

    Optional<ItemPedido> findByIdPedidoAndIdProduto(@NotNull Integer idPedido, @NotNull Integer idProduto);
}
