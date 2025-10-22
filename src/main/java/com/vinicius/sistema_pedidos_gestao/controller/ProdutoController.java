package com.vinicius.sistema_pedidos_gestao.controller;

import com.vinicius.sistema_pedidos_gestao.busines.dto.CategoriaCadastroDTO;
import com.vinicius.sistema_pedidos_gestao.busines.dto.ProdutoCadastroDTO;
import com.vinicius.sistema_pedidos_gestao.busines.service.CategoriaService;
import com.vinicius.sistema_pedidos_gestao.busines.service.ProdutoService;
import com.vinicius.sistema_pedidos_gestao.insfratructure.entitys.Categoria;
import com.vinicius.sistema_pedidos_gestao.insfratructure.entitys.Produto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@Valid @RequestBody ProdutoCadastroDTO produto) {
        return produtoService.salvarProduto(produto);
    }

    @GetMapping("/buscar-produtos")
    public ResponseEntity<?> buscarTodos(){
        return ResponseEntity.ok(produtoService.buscarAtivos());
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletar(@RequestParam Integer id) {
        produtoService.deletarPorId(id);
        return ResponseEntity.ok("Produto deletado com sucesso!");
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizar(@RequestParam Integer id, @RequestBody Produto produto) {
        return produtoService.atualizarProdutoPorId(id, produto);
    }

}
