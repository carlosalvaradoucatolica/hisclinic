package co.edu.ucatolica.hisclinic.infraestructure.repository;

import co.edu.ucatolica.hisclinic.domain.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserDAO extends JpaRepository<AppUser,Long> {
    public AppUser findAppUserByEmail(String username);

}
