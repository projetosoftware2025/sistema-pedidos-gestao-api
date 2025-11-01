package com.vinicius.sistema_pedidos_gestao.busines.service;

import com.vinicius.sistema_pedidos_gestao.busines.dto.ProdutoCadastroDTO;
import com.vinicius.sistema_pedidos_gestao.busines.dto.ProdutoResponseDTO;
import com.vinicius.sistema_pedidos_gestao.insfratructure.entitys.Produto;
import com.vinicius.sistema_pedidos_gestao.insfratructure.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;

    /**
     * 🚀 Cadastra um novo produto com imagem em bytes
     */
    @Transactional
    public ResponseEntity<?> salvarProduto(ProdutoCadastroDTO produto) {
        if (repository.findByTitulo(produto.getTitulo()).isPresent()) {
            return ResponseEntity.badRequest().body("Produto já cadastrado!");
        }

        try {
            Produto produtoData = Produto.builder()
              .titulo(produto.getTitulo())
              .descricao(produto.getDescricao())
              .preco(produto.getPreco())
              .imagem(produto.getImagem() != null ? produto.getImagem().getBytes() : null)
              .categoriaId(produto.getCategoria())
              .ativo(true)
              .build();

            repository.save(produtoData);
            return ResponseEntity.ok("Produto cadastrado com sucesso!");

        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Erro ao processar imagem: " + e.getMessage());
        }
    }

    /**
     * ⚡ Busca produtos ativos SEM carregar imagens (muito mais rápido)
     */
    @Transactional(readOnly = true)
    public List<ProdutoResponseDTO> buscarAtivos() {
        // Consulta customizada no repositório que já retorna DTO sem imagem
        return repository.buscarAtivosSemImagem();
    }

    @Transactional(readOnly = true)
    public byte[] buscarImagem(Integer id) {
        return repository.buscarImagem(id)
          .orElse(null);
    }

    @Transactional
    public void deletarPorId(Integer id) {
        repository.deleteById(id);
    }

    /**
     * 🔁 Atualiza um produto parcialmente
     */
    @Transactional
    public ResponseEntity<?> atualizarProdutoPorId(Integer id, Produto produtoAtualizado) {
        Optional<Produto> produtoOpt = repository.findById(id);
        if (produtoOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Produto não encontrado!");
        }

        Produto produto = produtoOpt.get();

        if (produtoAtualizado.getTitulo() != null) produto.setTitulo(produtoAtualizado.getTitulo());
        if (produtoAtualizado.getDescricao() != null) produto.setDescricao(produtoAtualizado.getDescricao());
        if (produtoAtualizado.getPreco() != null) produto.setPreco(produtoAtualizado.getPreco());
        if (produtoAtualizado.getImagem() != null) produto.setImagem(produtoAtualizado.getImagem());
        if (produtoAtualizado.getCategoriaId() != null) produto.setCategoriaId(produtoAtualizado.getCategoriaId());
        if (produtoAtualizado.getAtivo() != null) produto.setAtivo(produtoAtualizado.getAtivo());

        repository.save(produto);
        return ResponseEntity.ok("Produto atualizado com sucesso!");
    }
}
