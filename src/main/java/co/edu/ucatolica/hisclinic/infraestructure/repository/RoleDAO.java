package co.edu.ucatolica.hisclinic.infraestructure.repository;

import co.edu.ucatolica.hisclinic.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RoleDAO extends JpaRepository<Role,Integer> {
    public Collection<Role> findRolesByIdIn(Collection<Integer> rolesId);
}
