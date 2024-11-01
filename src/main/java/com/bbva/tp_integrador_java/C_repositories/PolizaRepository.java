package com.bbva.tp_integrador_java.C_repositories;

import com.bbva.tp_integrador_java.D_models.Poliza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PolizaRepository extends JpaRepository<Poliza, Long> {

   @Query("SELECT p FROM Poliza p JOIN FETCH p.cliente JOIN FETCH p.tipoSeguro")
   List <Poliza> findAllQuery ();

   Optional <Poliza> findByCodigoPoliza(String codigoPoliza);
}
