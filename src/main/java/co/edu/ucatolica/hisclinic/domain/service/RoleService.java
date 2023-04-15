package co.edu.ucatolica.hisclinic.domain.service;

import co.edu.ucatolica.hisclinic.domain.model.Role;

import java.util.Collection;
import java.util.Set;

public interface RoleService {
    public Role save(Role role);
    public Collection<Role> getRolesByIds(Collection<Integer> rolesId);
}
