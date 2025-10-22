package com.vinicius.sistema_pedidos_gestao.busines.service;

import com.vinicius.sistema_pedidos_gestao.busines.dto.CategoriaCadastroDTO;
import com.vinicius.sistema_pedidos_gestao.busines.dto.ProdutoCadastroDTO;
import com.vinicius.sistema_pedidos_gestao.insfratructure.entitys.Categoria;
import com.vinicius.sistema_pedidos_gestao.insfratructure.entitys.Produto;
import com.vinicius.sistema_pedidos_gestao.insfratructure.repository.CategoriaRepository;
import com.vinicius.sistema_pedidos_gestao.insfratructure.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;

    public ResponseEntity<?> salvarProduto(@Valid ProdutoCadastroDTO produto) {
        if (repository.findByTitulo(produto.getTitulo()).isPresent()) {
            return ResponseEntity.badRequest().body("Produto já cadastrado!");
        }

        Produto produtoData = Produto.builder()
                .titulo(produto.getTitulo())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .url(produto.getUrl())
                .categoriaId(produto.getCategoria())
                .ativo(true)
                .build();

        repository.save(produtoData);
        return ResponseEntity.ok("Produto cadastrado com sucesso!");
    }

    public List<Produto> buscarAtivos() {
        return repository.findAllByAtivo(true)
                .stream()
                .map(u -> Produto.builder()
                        .id(u.getId())
                        .titulo((u.getTitulo()))
                        .descricao(u.getDescricao())
                        .preco(u.getPreco())
                        .categoriaId(u.getCategoriaId())
                        .url(u.getUrl())
                        .ativo(u.getAtivo())
                        .build())
                .toList();
    }

    @Transactional
    public void deletarPorId(Integer id) {
        repository.deleteById(id);
    }

    public ResponseEntity<?> atualizarProdutoPorId(Integer id, Produto produtoAtualizado) {
        Optional<Produto> produtoOpt = repository.findById(id);
        if (produtoOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Produto não encontrado!");
        }

        Produto produto = produtoOpt.get();

        if (produtoAtualizado.getTitulo() != null) produto.setTitulo(produtoAtualizado.getTitulo());
        if (produtoAtualizado.getDescricao() != null) produto.setDescricao(produtoAtualizado.getDescricao());
        if (produtoAtualizado.getPreco() != null) produto.setPreco(produtoAtualizado.getPreco());
        if (produtoAtualizado.getUrl() != null) produto.setUrl(produtoAtualizado.getUrl());
        if (produtoAtualizado.getCategoriaId() != null) produto.setCategoriaId(produtoAtualizado.getCategoriaId());
        if (produtoAtualizado.getAtivo() != null) produto.setAtivo(produtoAtualizado.getAtivo());

        repository.save(produto);
        return ResponseEntity.ok("Produto atualizado com sucesso!");
    }



}
