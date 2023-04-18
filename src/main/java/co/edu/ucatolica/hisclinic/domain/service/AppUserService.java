package co.edu.ucatolica.hisclinic.domain.service;
import co.edu.ucatolica.hisclinic.domain.model.AppUser;

public interface AppUserService {
    public AppUser upsert(AppUser appUser);
    public AppUser getByEmail(String email);
    public AppUser delete(AppUser appUser);
}
