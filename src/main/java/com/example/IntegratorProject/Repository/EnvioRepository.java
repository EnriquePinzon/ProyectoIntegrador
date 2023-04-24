package com.example.IntegratorProject.Repository;

import com.example.IntegratorProject.Entities.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Integer> {
    @Query("SELECT e FROM Envio e WHERE e.estadoDelEnvio = ?1")
    public List<Envio>filtroPorEstado(String estadoDelEnvio);
}
