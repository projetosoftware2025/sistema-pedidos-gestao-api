package com.vinicius.sistema_pedidos_gestao.controller;

import com.vinicius.sistema_pedidos_gestao.busines.dto.CategoriaCadastroDTO;
import com.vinicius.sistema_pedidos_gestao.busines.service.CategoriaService;
import com.vinicius.sistema_pedidos_gestao.insfratructure.entitys.Categoria;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping(value = "/cadastrar", consumes = "multipart/form-data")
    public ResponseEntity<?> cadastrar(
      @RequestParam("descricao") String descricao,
      @RequestParam(value = "imagem", required = false) MultipartFile imagem
    ) {
        CategoriaCadastroDTO dto = new CategoriaCadastroDTO();
        dto.setDescricao(descricao);
        dto.setImagem(imagem);
        return categoriaService.salvarCategoria(dto);
    }

    @GetMapping("/buscar-categorias")
    public ResponseEntity<?> buscarTodos() {
        // Retorna a lista de DTOs, que contêm a URL da imagem
        return ResponseEntity.ok(categoriaService.buscarAtivos());
    }

    // NOVO ENDPOINT: Serve a imagem binária a partir do ID
    @GetMapping("/imagem/{id}")
    public ResponseEntity<byte[]> buscarImagem(@PathVariable Integer id) {
        byte[] imagemBytes = categoriaService.buscarImagem(id);

        if (imagemBytes == null || imagemBytes.length == 0) {
            return ResponseEntity.notFound().build();
        }

        // Retorna a imagem. Se você souber o tipo exato (PNG, GIF, etc.),
        // use o MediaType específico. Caso contrário, IMAGE_JPEG é um bom chute comum.
        return ResponseEntity.ok()
          .contentType(MediaType.IMAGE_JPEG)
          .body(imagemBytes);
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletar(@RequestParam Integer id) {
        categoriaService.deletarPorId(id);
        return ResponseEntity.ok("Categoria deletada com sucesso!");
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizar(@RequestParam Integer id, @RequestBody Categoria categoria) {
        return categoriaService.atualizarCategoriaPorId(id, categoria);
    }
}
