package com.vinicius.sistema_pedidos_gestao.controller;

import com.vinicius.sistema_pedidos_gestao.busines.dto.CategoriaCadastroDTO;
import com.vinicius.sistema_pedidos_gestao.busines.service.CategoriaService;
import com.vinicius.sistema_pedidos_gestao.insfratructure.entitys.Categoria;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;


    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@Valid @RequestBody CategoriaCadastroDTO categoria) {
        return categoriaService.salvarCategoria(categoria);
    }

    @GetMapping("/buscar-categorias")
    public ResponseEntity<?> buscarTodos(){
        return ResponseEntity.ok(categoriaService.buscarAtivos());
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
