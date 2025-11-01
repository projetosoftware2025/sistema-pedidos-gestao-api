package com.vinicius.sistema_pedidos_gestao.insfratructure.repository;

import com.vinicius.sistema_pedidos_gestao.busines.dto.ProdutoResponseDTO;
import com.vinicius.sistema_pedidos_gestao.insfratructure.entitys.Categoria;
import com.vinicius.sistema_pedidos_gestao.insfratructure.entitys.Produto;
import io.micrometer.observation.ObservationFilter;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    @Transactional
    void deleteById(Integer id);

    List<Produto> findAllByAtivo(Boolean ativo);

    @Query("SELECT new com.vinicius.sistema_pedidos_gestao.busines.dto.ProdutoResponseDTO(" +
      "p.id, p.titulo, p.descricao, p.preco, p.categoriaId, null, p.ativo) " +
      "FROM Produto p WHERE p.ativo = true")
    List<ProdutoResponseDTO> buscarAtivosSemImagem();


    Optional<Object> findByTitulo(@NotBlank(message = "Título é obrigatório") String titulo);

    @Query("SELECT p.imagem FROM Produto p WHERE p.id = :id")
    Optional<byte[]> buscarImagem(@Param("id") Integer id);


}
