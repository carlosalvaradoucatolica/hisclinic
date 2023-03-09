package co.edu.ucatolica.hisclinic.controller;

import co.edu.ucatolica.hisclinic.model.AppUser;
import co.edu.ucatolica.hisclinic.service.AppUserServiceImpl;
import co.edu.ucatolica.hisclinic.util.Response;
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

}
