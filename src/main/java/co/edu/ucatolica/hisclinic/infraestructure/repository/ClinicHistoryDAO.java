package co.edu.ucatolica.hisclinic.infraestructure.repository;

import co.edu.ucatolica.hisclinic.domain.model.ClinicHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClinicHistoryDAO extends JpaRepository<ClinicHistory,Integer> {

public Optional<ClinicHistory> findByIdentification(int id);
}
