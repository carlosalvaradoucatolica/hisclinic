package co.edu.ucatolica.hisclinic.domain.service;

import co.edu.ucatolica.hisclinic.domain.model.Role;

import java.util.Collection;

public interface RoleService {
    public Role save(Role role);
    public Collection<Role> getRoles();
}
