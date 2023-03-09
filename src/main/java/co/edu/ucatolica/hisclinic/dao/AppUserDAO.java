package co.edu.ucatolica.hisclinic.dao;

import co.edu.ucatolica.hisclinic.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserDAO extends JpaRepository<AppUser,Long> {
    public AppUser findAppUserByUsername(String username);
}
