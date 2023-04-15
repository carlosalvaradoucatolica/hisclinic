package co.edu.ucatolica.hisclinic.application.usecases.auth;

import co.edu.ucatolica.hisclinic.infraestructure.dto.request.AppUserDTO;
import co.edu.ucatolica.hisclinic.infraestructure.dto.response.Response;
import org.springframework.http.ResponseEntity;

public interface SignUpUseCase {
    public ResponseEntity<Response> signUp(AppUserDTO appUserDTO);
}
