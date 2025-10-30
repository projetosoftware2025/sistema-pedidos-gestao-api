package com.vinicius.sistema_pedidos_gestao.busines.service;
import com.vinicius.sistema_pedidos_gestao.busines.dto.PedidoCadastroDTO;
import com.vinicius.sistema_pedidos_gestao.busines.dto.PedidoResponseDTO;
import com.vinicius.sistema_pedidos_gestao.insfratructure.entitys.Pedido;
import com.vinicius.sistema_pedidos_gestao.insfratructure.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;

    public ResponseEntity<?> salvarPedido(PedidoCadastroDTO pedido) {

        // 1. Lógica de Validação de Negócio (Busca por pedido em andamento)
        Optional<Pedido> existePedido = repository.findByCpfAndStatus(pedido.getCpf(), "A");

        if(existePedido.isPresent()){
            return ResponseEntity.status(409).body("Ainda existe um pedido em andamento para você");
        }

        // 2. Constrói a entidade Pedido a partir do DTO
        Pedido pedidoData = Pedido.builder()
                .cliente(pedido.getCliente())
                .cpf(pedido.getCpf())
                .dtPedido(LocalDateTime.now())
                .formaPagamento(pedido.getFormaPagamento())
                .telefone(pedido.getTelefone())
                .status("A")
                .build();

        Pedido pedidoSalvo = repository.save(pedidoData);

        PedidoResponseDTO pedidoDataTeste = PedidoResponseDTO.builder()
                .id(pedidoSalvo.getId())
                .dtPedido(pedidoSalvo.getDtPedido())
                .status(pedidoSalvo.getStatus())
                .build();

        return ResponseEntity.ok().body(pedidoDataTeste);
    }

    public List<Pedido> buscarPedidos(LocalDate dtInicio, LocalDate dtFim, String cpf) {
        List<Pedido> resultados;

        // Criando os limites com hora/minuto/segundo exatos antes de chamar o repositório
        LocalDateTime dtInicioComHora = dtInicio.atStartOfDay(); // 00:00:00
        LocalDateTime dtFimComHora = dtFim.atTime(23, 59, 59); // 23:59:59

        if (cpf != null && !cpf.isBlank()) {
            // Busca filtrando por CPF
            resultados = repository.findAllByDtPedidoBetweenAndCpf(dtInicioComHora, dtFimComHora, cpf);
        } else {
            // Busca apenas pelo intervalo de datas
            resultados = repository.findAllByDtPedidoBetween(dtInicioComHora, dtFimComHora);
        }

        return resultados.stream()
                .map(u -> Pedido.builder()
                        .id(u.getId())
                        .cliente(u.getCliente())
                        .cpf(u.getCpf())
                        .status(u.getStatus())
                        .telefone(u.getTelefone())
                        .dtPedido(u.getDtPedido())
                        .formaPagamento(u.getFormaPagamento())
                        .build())
                .toList();
    }


    @Transactional
    public void deletarPorId(Integer id) {
        repository.deleteById(id);
    }

    public ResponseEntity<?> atualizarStatus(Integer id, String status) {
        Optional<Pedido> pedidoOpt = repository.findById(id);
        if (pedidoOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Pedido não encontrado!");
        }

        Pedido pedido = pedidoOpt.get();

        if (status != null) pedido.setStatus(status);

        repository.save(pedido);
        return ResponseEntity.ok("Status do pedido atualizado com sucesso!");
    }



}
