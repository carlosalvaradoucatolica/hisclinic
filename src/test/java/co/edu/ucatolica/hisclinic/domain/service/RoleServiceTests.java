package co.edu.ucatolica.hisclinic.domain.service;

import co.edu.ucatolica.hisclinic.domain.model.Role;
import co.edu.ucatolica.hisclinic.domain.model.Roles;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RoleServiceTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RoleService roleService;

    @Test
    void save(){
        Role role = new Role(Roles.VIEW_HC,"/view","visibility");
        Role rolePersisted = roleService.save(role);
        assertInstanceOf(rolePersisted.getClass(),role.getClass());
    }
    @Test
    void getRolesByIds(){
        Collection<Role> roles = roleService.getRoles();
        assertInstanceOf(Collection.class,roles.getClass());
    }
}
