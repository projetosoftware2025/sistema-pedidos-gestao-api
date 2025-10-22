package com.vinicius.sistema_pedidos_cliente.insfratructure.repository;

import com.vinicius.sistema_pedidos_cliente.busines.dto.UsuarioDTO;
import com.vinicius.sistema_pedidos_cliente.insfratructure.entitys.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);

    List<UsuarioDTO> findAllByAtivo(Boolean ativo);
}
