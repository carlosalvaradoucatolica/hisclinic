package co.edu.ucatolica.hisclinic.application.usecases.hisclinic;

import co.edu.ucatolica.hisclinic.domain.model.AppUser;
import co.edu.ucatolica.hisclinic.domain.service.AppUserService;
import co.edu.ucatolica.hisclinic.infraestructure.dto.request.HisClinicDTO;
import co.edu.ucatolica.hisclinic.infraestructure.dto.request.PurchaseDTO;
import co.edu.ucatolica.hisclinic.infraestructure.dto.response.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ValidUserDatabaseImpl implements ValidUserDatabase {
    @Autowired
    AppUserService appUserService;

    @Override
    public ResponseEntity<ResponseDTO> createHisClinic(HisClinicDTO hisClinicDTO) throws JsonProcessingException {
        AppUser appUser = appUserService.getByEmail("carlosalvaradom@icloud.com");

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
