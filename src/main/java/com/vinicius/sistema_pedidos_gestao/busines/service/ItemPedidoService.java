package com.vinicius.sistema_pedidos_gestao.busines.service;
import com.vinicius.sistema_pedidos_gestao.busines.dto.ItemPedidoCadastroDTO;
import com.vinicius.sistema_pedidos_gestao.busines.dto.PedidoCadastroDTO;
import com.vinicius.sistema_pedidos_gestao.insfratructure.entitys.ItemPedido;
import com.vinicius.sistema_pedidos_gestao.insfratructure.entitys.Pedido;
import com.vinicius.sistema_pedidos_gestao.insfratructure.repository.ItemPedidoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemPedidoService {

    private final ItemPedidoRepository repository;

    public ResponseEntity<?> salvarItemPedido(@Valid ItemPedidoCadastroDTO itemPedido) {

        Optional<ItemPedido> existeItem = repository.findByIdPedidoAndIdProduto(itemPedido.getIdPedido(), itemPedido.getIdProduto());

        if(existeItem.isPresent()){
            return ResponseEntity.status(409).body("Produto já foi adicionado ao pedido");
        }

        ItemPedido itemPedidoData = ItemPedido.builder()
                .idPedido(itemPedido.getIdPedido())
                .idProduto(itemPedido.getIdProduto())
                .titulo(itemPedido.getTitulo())
                .valorUnitario(itemPedido.getValorUnitario())
                .quantidade(itemPedido.getQuantidade())
                .build();

        repository.save(itemPedidoData);
        return ResponseEntity.ok("Produto adicionado ao pedido com sucesso!");
    }

    public List<ItemPedido> buscarItensPedido(Integer idPedido) {
        return repository.findAllByIdPedido(idPedido)
                .stream()
                .map(u -> ItemPedido.builder()
                        .id(u.getId())
                        .titulo(u.getTitulo())
                        .idProduto(u.getIdProduto())
                        .idPedido(u.getIdPedido())
                        .valorUnitario(u.getValorUnitario())
                        .quantidade(u.getQuantidade())
                        .build())
                .toList();
    }

    @Transactional
    public void deletarPorId(Integer id) {
        repository.deleteById(id);
    }
}
