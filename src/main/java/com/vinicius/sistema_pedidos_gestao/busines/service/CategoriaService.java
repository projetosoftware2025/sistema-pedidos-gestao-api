package com.vinicius.sistema_pedidos_gestao.busines.service;

import com.vinicius.sistema_pedidos_gestao.busines.dto.CategoriaCadastroDTO;
import com.vinicius.sistema_pedidos_gestao.busines.dto.CategoriaResponseDTO;
import com.vinicius.sistema_pedidos_gestao.insfratructure.entitys.Categoria;
import com.vinicius.sistema_pedidos_gestao.insfratructure.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    public ResponseEntity<?> salvarCategoria(CategoriaCadastroDTO categoria) {
        if (repository.findByDescricao(categoria.getDescricao()).isPresent()) {
            return ResponseEntity.badRequest().body("Categoria já cadastrada!");
        }

        try {
            Categoria categoriaData = Categoria.builder()
              .descricao(categoria.getDescricao())
              .imagem(categoria.getImagem() != null ? categoria.getImagem().getBytes() : null)
              .ativo(true)
              .build();

            repository.save(categoriaData);
            return ResponseEntity.ok("Categoria cadastrada com sucesso!");

        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Erro ao processar imagem: " + e.getMessage());
        }
    }

    // Método que agora retorna o DTO com a URL da imagem
    @Transactional(readOnly = true)
    public List<CategoriaResponseDTO> buscarAtivos() {
        return repository.findAllByAtivo(true)
          .stream()
          .map(u -> new CategoriaResponseDTO( // Mapeando para o DTO
            u.getId(),
            u.getDescricao(),
            // Monta a URL para o novo endpoint de imagem: /categorias/{id}/imagem
            ServletUriComponentsBuilder.fromCurrentContextPath()
              .path("/categoria/") // Deve coincidir com o @RequestMapping do Controller
              .path(u.getId().toString())
              .path("/imagem")
              .toUriString(),
            u.getAtivo()
          ))
          .toList();
    }

    // NOVO MÉTODO: Busca apenas os bytes da imagem por ID (usado pelo Controller de imagem)
    // ESSENCIAL: O @Transactional(readOnly = true) é necessário aqui para acessar o Large Object (OID)
    @Transactional(readOnly = true)
    public byte[] buscarImagem(Integer id) {
        return repository.findById(id)
          .map(Categoria::getImagem)
          .orElse(null);
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
        if (categoriaAtualizada.getImagem() != null) categoria.setImagem(categoriaAtualizada.getImagem());

        repository.save(categoria);
        return ResponseEntity.ok("Categoria atualizada com sucesso!");
    }
}
