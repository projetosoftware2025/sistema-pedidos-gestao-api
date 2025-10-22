package com.vinicius.sistema_pedidos_gestao.busines.service;

import com.vinicius.sistema_pedidos_gestao.busines.dto.CategoriaCadastroDTO;
import com.vinicius.sistema_pedidos_gestao.insfratructure.entitys.Categoria;
import com.vinicius.sistema_pedidos_gestao.insfratructure.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    public ResponseEntity<?> salvarCategoria(@Valid CategoriaCadastroDTO categoria) {
        if (repository.findByDescricao(categoria.getDescricao()).isPresent()) {
            return ResponseEntity.badRequest().body("Categoria já cadastrada!");
        }

        Categoria categoriaData = Categoria.builder()
                .descricao(categoria.getDescricao())
                .url(categoria.getUrl())
                .ativo(true)
                .build();

        repository.save(categoriaData);
        return ResponseEntity.ok("Categoria cadastrada com sucesso!");
    }

    public List<Categoria> buscarAtivos() {
        return repository.findAllByAtivo(true)
                .stream()
                .map(u -> Categoria.builder()
                        .id(u.getId())
                        .descricao(u.getDescricao())
                        .ativo(u.getAtivo())
                        .build())
                .toList();
    }

    @Transactional
    public void deletarPorId(Integer id) {
        repository.deleteById(id);
    }

    public ResponseEntity<?> atualizarCategoriaPorId(Integer id, Categoria categoriaAtualizada) {
        Optional<Categoria> categoriaOpt = repository.findById(id);
        if (categoriaOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Categoria não encontrada!");
        }

        Categoria categoria = categoriaOpt.get();

        if (categoriaAtualizada.getDescricao() != null) categoria.setDescricao(categoriaAtualizada.getDescricao());
        if (categoriaAtualizada.getUrl() != null) categoria.setUrl(categoriaAtualizada.getUrl());

        repository.save(categoria);
        return ResponseEntity.ok("Categoria atualizada com sucesso!");
    }



}
