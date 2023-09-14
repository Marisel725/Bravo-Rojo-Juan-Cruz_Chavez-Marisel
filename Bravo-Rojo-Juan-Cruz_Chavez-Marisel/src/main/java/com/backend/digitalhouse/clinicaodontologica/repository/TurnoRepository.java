package com.backend.digitalhouse.clinicaodontologica.repository;

import com.backend.digitalhouse.clinicaodontologica.entity.Odontologo;
import com.backend.digitalhouse.clinicaodontologica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno,Integer> {
}
