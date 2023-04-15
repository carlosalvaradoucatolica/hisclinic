package co.edu.ucatolica.hisclinic;

import co.edu.ucatolica.hisclinic.domain.model.Role;
import co.edu.ucatolica.hisclinic.domain.model.Roles;
import co.edu.ucatolica.hisclinic.domain.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HisclinicApplication {
	public static void main(String[] args) {
		SpringApplication.run(HisclinicApplication.class, args);
	}
	@Bean
	CommandLineRunner run(
			RoleService roleService
	) {
		return args -> {
			roleService.save(new Role(Roles.VIEW_HC,"/view","visibility"));
			roleService.save(new Role(Roles.EDIT_HC,"/edit","edit"));
			roleService.save(new Role(Roles.UPDATE_HC,"/update","save"));

		};
	}
}