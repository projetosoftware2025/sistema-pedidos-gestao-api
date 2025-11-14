package com.vinicius.sistema_pedidos_gestao.controller;

import com.vinicius.sistema_pedidos_gestao.busines.dto.CategoriaCadastroDTO;
import com.vinicius.sistema_pedidos_gestao.busines.dto.ProdutoCadastroDTO;
import com.vinicius.sistema_pedidos_gestao.busines.service.CategoriaService;
import com.vinicius.sistema_pedidos_gestao.busines.service.ProdutoService;
import com.vinicius.sistema_pedidos_gestao.insfratructure.entitys.Categoria;
import com.vinicius.sistema_pedidos_gestao.insfratructure.entitys.Produto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping(value = "/cadastrar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> cadastrar(@Valid @ModelAttribute ProdutoCadastroDTO produto) {
        return produtoService.salvarProduto(produto);
    }

    @GetMapping("/buscar-produtos")
    public ResponseEntity<?> buscarTodos(){
        return ResponseEntity.ok(produtoService.buscarAtivos());
    }

    @GetMapping("/imagem/{id}")
    public ResponseEntity<byte[]> buscarImagem(@PathVariable Integer id) {
        byte[] imagemBytes = produtoService.buscarImagem(id);

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
        produtoService.deletarPorId(id);
        return ResponseEntity.ok("Produto deletado com sucesso!");
    }

    @PutMapping(value = "/atualizar", consumes = "multipart/form-data")
    public ResponseEntity<?> atualizar(
      @RequestParam Integer id,
      @ModelAttribute ProdutoCadastroDTO dto
    ) {
        return produtoService.atualizarProdutoPorId(id, dto);
    }


}
