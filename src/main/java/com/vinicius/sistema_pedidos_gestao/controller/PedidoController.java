package com.vinicius.sistema_pedidos_gestao.controller;

import com.vinicius.sistema_pedidos_gestao.busines.dto.CategoriaCadastroDTO;
import com.vinicius.sistema_pedidos_gestao.busines.dto.PedidoCadastroDTO;
import com.vinicius.sistema_pedidos_gestao.busines.dto.ProdutoCadastroDTO;
import com.vinicius.sistema_pedidos_gestao.busines.service.CategoriaService;
import com.vinicius.sistema_pedidos_gestao.busines.service.PedidoService;
import com.vinicius.sistema_pedidos_gestao.busines.service.ProdutoService;
import com.vinicius.sistema_pedidos_gestao.insfratructure.entitys.Categoria;
import com.vinicius.sistema_pedidos_gestao.insfratructure.entitys.Pedido;
import com.vinicius.sistema_pedidos_gestao.insfratructure.entitys.Produto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@Valid @RequestBody PedidoCadastroDTO pedido) {
        return service.salvarPedido(pedido);
    }

    @GetMapping("/buscar-pedidos")
    public ResponseEntity<?> buscarTodos(@RequestParam LocalDate dtInicio, @RequestParam LocalDate dtFim, @RequestParam(required = false) String cpf){

        List<Pedido> pedidos = service.buscarPedidos(dtInicio, dtFim, cpf);
        if(pedidos.isEmpty()){
            return ResponseEntity.status(404).body("Pedidos não encontrados");
        }
        return ResponseEntity.ok().body(pedidos);
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletar(@RequestParam Integer id) {
        service.deletarPorId(id);
        return ResponseEntity.ok("Pedido deletado com sucesso!");
    }

    @PutMapping("/atualizar-status")
    public ResponseEntity<?> atualizar(@RequestParam Integer id, @RequestParam String status) {
        return service.atualizarStatus(id, status);
    }

}
