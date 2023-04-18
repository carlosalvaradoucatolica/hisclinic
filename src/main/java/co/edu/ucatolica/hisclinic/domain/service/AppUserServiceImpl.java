package co.edu.ucatolica.hisclinic.domain.service;

import co.edu.ucatolica.hisclinic.infraestructure.repository.AppUserDAO;
import co.edu.ucatolica.hisclinic.domain.model.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService{
    private final AppUserDAO appUserDAO;
    @Override
    public AppUser upsert(AppUser appUser) {
        return appUserDAO.saveAndFlush(appUser);
    }

    @Override
    public AppUser getByEmail(String email) {
        return appUserDAO.findAppUserByEmail(email);
    }

    @Override
    public AppUser delete(AppUser appUser) {
        appUserDAO.delete(appUser);
        return null;
    }
}
