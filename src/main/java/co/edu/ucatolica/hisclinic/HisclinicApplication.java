package co.edu.ucatolica.hisclinic;

import co.edu.ucatolica.hisclinic.domain.model.AppUser;
import co.edu.ucatolica.hisclinic.domain.model.Product;
import co.edu.ucatolica.hisclinic.domain.model.Role;
import co.edu.ucatolica.hisclinic.domain.model.Roles;
import co.edu.ucatolica.hisclinic.domain.service.AppUserService;
import co.edu.ucatolica.hisclinic.domain.service.ProductService;
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
			RoleService roleService,
			ProductService productService,
			AppUserService appUserService
	) {
		return args -> {
			roleService.save(new Role(Roles.VIEW_HC,"/view","visibility"));
			roleService.save(new Role(Roles.EDIT_HC,"/edit","edit"));
			roleService.save(new Role(Roles.UPDATE_HC,"/update","save"));
			productService.save(new Product("Servicios Hisclinic 1 Mes","Licencia 1 Mes de vigencia x 1 usuario de acceso para visualizar, crear y editar historias clinicas.",1,"COP",60000,1));
			productService.save(new Product("Servicios Hisclinic 3 Meses","Licencia 3 Meses de vigencia x 1 usuario de acceso para visualizar, crear y editar historias clinicas.",1,"COP",150000,3));
			productService.save(new Product("Servicios Hisclinic 6 Meses","Licencia 6 Meses de vigencia x 1 usuario de acceso para visualizar, crear y editar historias clinicas.",1,"COP",300000,6));
			productService.save(new Product("Servicios Hisclinic 12 Meses","Licencia 12 Meses de vigencia x 1 usuario de acceso para visualizar, crear y editar historias clinicas.",1,"COP",600000,12));
			appUserService.upsert(new AppUser("carlosalvaradom@icloud.com","Carlos","Alvarado",false,"12345678",null));
		};
	}
}