package co.edu.ucatolica.hisclinic.infraestructure.repository;

import co.edu.ucatolica.hisclinic.domain.model.AppUser;
import co.edu.ucatolica.hisclinic.domain.model.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriaDAO extends JpaRepository<Auditoria,Long> {

}
