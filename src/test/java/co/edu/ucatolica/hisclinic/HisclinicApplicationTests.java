package co.edu.ucatolica.hisclinic;

import co.edu.ucatolica.hisclinic.domain.model.AppUser;
import co.edu.ucatolica.hisclinic.domain.service.AppUserService;
import co.edu.ucatolica.hisclinic.domain.service.ProductService;
import co.edu.ucatolica.hisclinic.infraestructure.repository.AppUserDAO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class HisclinicApplicationTests {


	@Autowired AppUserDAO appUserDAO ;
	@Autowired AppUserService appUserService;
	@Test
	@DisplayName("Verificar usuario traido a traves de email")
	void shouldReturnUser() {

		//arrange
		AppUser appUser;
		appUser = new AppUser("carlosalvaradom@icloud.com","Carlos","Alvarado",false,"12345678",null);
		AppUser appUser2;
		//act
		appUser2 = appUserDAO.findAppUserByEmail("carlosalvaradom@icloud.com");
		//assert
		assertEquals(appUser, appUser);
	}

}
