package com.vinicius.sistema_pedidos_gestao.controller;
import com.vinicius.sistema_pedidos_gestao.busines.dto.ItemPedidoCadastroDTO;
import com.vinicius.sistema_pedidos_gestao.busines.dto.PedidoCadastroDTO;
import com.vinicius.sistema_pedidos_gestao.busines.service.ItemPedidoService;
import com.vinicius.sistema_pedidos_gestao.insfratructure.entitys.ItemPedido;
import com.vinicius.sistema_pedidos_gestao.insfratructure.entitys.Pedido;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/itens-pedido")
@RequiredArgsConstructor
public class ItemPedidoController {

    private final ItemPedidoService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@Valid @RequestBody ItemPedidoCadastroDTO itemPedido) {
        return service.salvarItemPedido(itemPedido);
    }

    @GetMapping("/buscar-itens")
    public ResponseEntity<?> buscarTodos(@RequestParam Integer idPedido){

        List<ItemPedido> itensPedido = service.buscarItensPedido(idPedido);
        if(itensPedido.isEmpty()){
            return ResponseEntity.status(404).body("Itens do Pedido não encontrados");
        }
        return ResponseEntity.ok().body(itensPedido);
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletar(@RequestParam Integer id) {
        service.deletarPorId(id);
        return ResponseEntity.ok("Item do pedido deletado com sucesso!");
    }


}
