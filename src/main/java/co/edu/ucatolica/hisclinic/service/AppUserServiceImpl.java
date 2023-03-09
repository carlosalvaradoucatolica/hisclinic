package co.edu.ucatolica.hisclinic.service;

import co.edu.ucatolica.hisclinic.dao.AppUserDAO;
import co.edu.ucatolica.hisclinic.model.AppUser;
import co.edu.ucatolica.hisclinic.util.EmailValidator;
import co.edu.ucatolica.hisclinic.util.Response;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@Transactional
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService{

    private final AppUserDAO appUserDAO;
    private final EmailValidator emailValidator;
    @Bean
    private BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public ResponseEntity<Response> save(AppUser appUser) {
        /*
        return  new ResponseEntity<Response>(
                Response.builder()
                        .timeStampo(LocalDateTime.now())
                        .message("Usuario creado exitosamente.")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .data(Map.of("appUser",appUser))
                        .build()
                ,HttpStatus.CREATED
        );*/

        String badRequestMessage = "";
        if(
            appUser.getFirstName() == null
            || appUser.getLastName() == null
            || appUser.getUsername() == null
            || appUser.getPassword() == null
            || appUser.getFirstName().equals("")
            || appUser.getLastName().equals("")
            || appUser.getUsername().equals("")
            || appUser.getPassword().equals("")
        ) {
            badRequestMessage = "Campos requeridos no estan incluidos en el cuerpo del JSON.";
        } else if (!emailValidator.test(appUser.getUsername())){
            badRequestMessage = "El email no corresponde a una dirección de correo electrónico valida.";
        } else if(appUserDAO.findAppUserByUsername(appUser.getUsername()) != null ){
            badRequestMessage = "El usuario ya existe.";
        }

        if(!badRequestMessage.equals("")){
            return new ResponseEntity<Response>(
                    Response.builder()
                            .timeStampo(LocalDateTime.now())
                            .message(badRequestMessage)
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .build()
                    ,HttpStatus.BAD_REQUEST
            );
        }

        appUser.setEnabled(false);
        appUser.setPassword(bCryptPasswordEncoder().encode(appUser.getPassword()));
        Long id = appUserDAO.saveAndFlush(appUser).getId();
        return  new ResponseEntity<Response>(
                Response.builder()
                        .timeStampo(LocalDateTime.now())
                        .message("Usuario creado exitosamente.")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .data(Map.of("id",id))
                        .build()
                ,HttpStatus.CREATED
        );
    }

    @Override
    public ResponseEntity<Response>  get(String username) {
        return null;
        //return appUserDAO.findAppUserByUsername(username);
    }

    @Override
    public ResponseEntity<Response>  update(AppUser appUser) {
        return null;
        //return appUserDAO.saveAndFlush(appUser);
    }
}
