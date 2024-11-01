package com.bbva.tp_integrador_java.C_repositories;

import com.bbva.tp_integrador_java.D_models.Cliente;
import com.bbva.tp_integrador_java.D_models.Poliza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
