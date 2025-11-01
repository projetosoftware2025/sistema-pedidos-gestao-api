package com.vinicius.sistema_pedidos_gestao.insfratructure.repository;

import com.vinicius.sistema_pedidos_gestao.busines.dto.CategoriaResponseDTO;
import com.vinicius.sistema_pedidos_gestao.busines.dto.ProdutoResponseDTO;
import com.vinicius.sistema_pedidos_gestao.insfratructure.entitys.Categoria;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    @Transactional
    void deleteById(Integer id);

    List<Categoria> findAllByAtivo(Boolean ativo);

    Optional<Object> findByDescricao(@NotBlank(message = "Descrição é obrigatório") String descricao);

  @Query("SELECT new com.vinicius.sistema_pedidos_gestao.busines.dto.CategoriaResponseDTO(" +
    "c.id, c.descricao, null, c.ativo) " +
    "FROM Categoria c WHERE c.ativo = true")
  List<CategoriaResponseDTO> buscarAtivosSemImagem();
}
