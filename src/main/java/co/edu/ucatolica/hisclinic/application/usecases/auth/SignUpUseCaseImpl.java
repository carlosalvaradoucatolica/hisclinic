package co.edu.ucatolica.hisclinic.application.usecases.auth;

import co.edu.ucatolica.hisclinic.domain.model.AppUser;
import co.edu.ucatolica.hisclinic.domain.service.AppUserService;
import co.edu.ucatolica.hisclinic.infraestructure.dto.request.AppUserDTO;
import co.edu.ucatolica.hisclinic.infraestructure.dto.response.ResponseDTO;
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
public class SignUpUseCaseImpl implements SignUpUseCase{

    private final AppUserService appUserService;
    @Bean
    private BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public ResponseEntity<ResponseDTO> signUp(AppUserDTO appUserDTO) {
        //Hacemos la validaciones apoyandonos de la clase DTO implementada en este caso, si no se encuentra todo como se definio en el caso de uso, se retorna bad_request
        AppUser appUser = appUserDTO.valid();
        if (appUser == null) {
            return new ResponseEntity<>(
                    ResponseDTO.builder()
                            .timeStamp(LocalDateTime.now())
                            .message("Los datos no contienen un formato correcto, por favor valide formato de email, que todos los campos se hayan enviado con el mismo nombre y no se encuentren vacios.")
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .build()
                    , HttpStatus.BAD_REQUEST
            );
        }
        //Encriptamos la contrasenna antes de enviarla a la base de datos
        appUser.setPassword(bCryptPasswordEncoder().encode(appUser.getPassword()));
        //Por defecto las cuentas se crean inhabilitadas hasta que se confirmen con el metodo que corresponda.
        appUser.setEnabled(false);
        //Por defecto, no se agrega ningun rol, solo hasta cuando el cliente realiza la compra.
        appUser.setRoles(null);
        //Insertamos el usuario en la base de datos.
        appUser = appUserService.upsert(appUser);
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("id",appUser.getId()))
                        .message("Usuario creado con exito, por favor verifique la cuenta.")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
                , HttpStatus.CREATED
        );
    }
}
