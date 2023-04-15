package co.edu.ucatolica.hisclinic.application.usecases.auth;

import co.edu.ucatolica.hisclinic.domain.model.AppUser;
import co.edu.ucatolica.hisclinic.infraestructure.dto.response.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface LoginUseCase {
    public ResponseEntity<ResponseDTO> login(AppUser appUser);
}
