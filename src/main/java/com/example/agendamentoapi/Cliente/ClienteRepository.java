package com.example.agendamentoapi.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

    // vai retornar true se ja existir um cliente com esse email no bd
    boolean emailJaExiste(String email);
}
