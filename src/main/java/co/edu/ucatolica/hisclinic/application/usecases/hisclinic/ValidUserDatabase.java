package co.edu.ucatolica.hisclinic.application.usecases.hisclinic;

import co.edu.ucatolica.hisclinic.infraestructure.dto.request.HisClinicDTO;
import co.edu.ucatolica.hisclinic.infraestructure.dto.response.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

public interface ValidUserDatabase {
    public ResponseEntity<ResponseDTO> createHisClinic(HisClinicDTO hisClinicDTO) throws JsonProcessingException;
}