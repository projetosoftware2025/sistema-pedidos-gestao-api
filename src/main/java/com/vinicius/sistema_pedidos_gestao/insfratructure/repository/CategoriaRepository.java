package com.vinicius.sistema_pedidos_gestao.insfratructure.repository;

import com.vinicius.sistema_pedidos_gestao.insfratructure.entitys.Categoria;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    @Transactional
    void deleteById(Integer id);

    List<Categoria> findAllByAtivo(Boolean ativo);

    Optional<Object> findByDescricao(@NotBlank(message = "Descrição é obrigatório") String descricao);
}
