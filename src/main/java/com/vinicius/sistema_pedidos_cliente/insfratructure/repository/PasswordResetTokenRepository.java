package com.vinicius.sistema_pedidos_cliente.insfratructure.repository;

import com.vinicius.sistema_pedidos_cliente.insfratructure.entitys.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByEmailAndToken(String email, String token);
    void deleteByEmail(String email);
}
