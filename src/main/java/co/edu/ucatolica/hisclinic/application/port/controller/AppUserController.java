package co.edu.ucatolica.hisclinic.application.port.controller;

import co.edu.ucatolica.hisclinic.domain.model.AppUser;
import co.edu.ucatolica.hisclinic.domain.service.AppUserServiceImpl;
import co.edu.ucatolica.hisclinic.infraestructure.dto.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/appUser")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserServiceImpl appUserService;
    //Crear usuario
    @PostMapping("")
    public ResponseEntity<Response> save(@RequestBody AppUser appUser){
        return appUserService.save(appUser);
    }

    @PutMapping("")
    public ResponseEntity<Response> update(@RequestBody AppUser appUser){ return appUserService.update(appUser); }

    @DeleteMapping("")
    public ResponseEntity<Response> delete(@RequestBody AppUser appUser){ return appUserService.delete(appUser); }
}
