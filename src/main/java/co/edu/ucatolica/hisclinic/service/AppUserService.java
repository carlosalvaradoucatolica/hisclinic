package co.edu.ucatolica.hisclinic.service;
import co.edu.ucatolica.hisclinic.model.AppUser;
import co.edu.ucatolica.hisclinic.util.Response;
import org.springframework.http.ResponseEntity;

public interface AppUserService {
    public ResponseEntity<Response> save(AppUser appUser);
    public ResponseEntity<Response>  get(String username);
    public ResponseEntity<Response>  update(AppUser appUser);
    public ResponseEntity<Response>  delete(AppUser appUser);
}
