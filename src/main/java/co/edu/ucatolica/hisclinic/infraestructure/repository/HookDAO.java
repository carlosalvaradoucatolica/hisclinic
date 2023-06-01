package co.edu.ucatolica.hisclinic.infraestructure.repository;

import co.edu.ucatolica.hisclinic.domain.model.Hook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HookDAO extends JpaRepository<Hook,Long>  {
}
