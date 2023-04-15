package co.edu.ucatolica.hisclinic.domain.service;

import co.edu.ucatolica.hisclinic.domain.model.Role;
import co.edu.ucatolica.hisclinic.infraestructure.repository.RoleDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
@AllArgsConstructor
public class RoleServiceImpl implements RoleService{
    private final RoleDAO roleDAO;
    @Override
    public Role save(Role role) {
        return roleDAO.save(role);
    }

    @Override
    public Collection<Role> getRolesByIds(Collection<Integer> rolesId) {
        return roleDAO.findRolesByIdIn(rolesId);
    }
}
