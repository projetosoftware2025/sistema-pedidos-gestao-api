package com.vinicius.sistema_pedidos_gestao.insfratructure.repository;

import com.vinicius.sistema_pedidos_gestao.insfratructure.entitys.Categoria;
import com.vinicius.sistema_pedidos_gestao.insfratructure.entitys.Produto;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    @Transactional
    void deleteById(Integer id);

    List<Produto> findAllByAtivo(Boolean ativo);

    Optional<Object> findByTitulo(@NotBlank(message = "Título é obrigatório") String titulo);
}
