package co.edu.ucatolica.hisclinic.infraestructure.repository;

import co.edu.ucatolica.hisclinic.domain.model.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrazabilidadDAO extends JpaRepository<Auditorium,Integer> {
}