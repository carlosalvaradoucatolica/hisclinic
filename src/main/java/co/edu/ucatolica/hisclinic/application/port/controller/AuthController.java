package co.edu.ucatolica.hisclinic.application.port.controller;

import co.edu.ucatolica.hisclinic.application.usecases.auth.LoginUseCase;
import co.edu.ucatolica.hisclinic.application.usecases.auth.LoginUseCaseImpl;
import co.edu.ucatolica.hisclinic.application.usecases.auth.SignUpUseCaseImpl;
import co.edu.ucatolica.hisclinic.infraestructure.dto.request.AppUserDTO;
import co.edu.ucatolica.hisclinic.infraestructure.dto.response.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
@AllArgsConstructor
public class AuthController {

    private final LoginUseCaseImpl loginUseCase;
    private final SignUpUseCaseImpl signUpUseCase;

    @PostMapping("/signUp")
    public ResponseEntity<ResponseDTO> save(@RequestBody AppUserDTO appUserDTO){
        return signUpUseCase.signUp(appUserDTO);
    }
}
