package com.bbva.tp_integrador_java.C_repositories;

import com.bbva.tp_integrador_java.D_models.TipoSeguro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoSeguroRepository extends JpaRepository<TipoSeguro, Long> {
}
