package co.edu.ucatolica.hisclinic.application.usecases.hisclinic;

import co.edu.ucatolica.hisclinic.domain.model.Auditorium;
import co.edu.ucatolica.hisclinic.domain.model.ClinicHistory;
import co.edu.ucatolica.hisclinic.infraestructure.dto.request.HisClinicDTO;
import co.edu.ucatolica.hisclinic.infraestructure.repository.ClinicHistoryDAO;
import co.edu.ucatolica.hisclinic.infraestructure.repository.TrazabilidadDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class HisClinicServiceImpl implements HisClinicService {
    private final ClinicHistoryDAO clinicHistoryDAO;
    private final TrazabilidadDAO trazabilidadDAO;
    @Override
    public ClinicHistory save(ClinicHistory clinicHistory, Auditorium auditorium) {
        //
        auditorium.setDate(LocalDateTime.now());
        auditorium.setEvent("CREATE HISTORY CLINIC");
        auditorium.setUser(clinicHistory.getIdentification());
        trazabilidadDAO.saveAndFlush(auditorium);
        if (clinicHistoryDAO.findByIdentification(clinicHistory.getIdentification()).isPresent()){
            throw new UserException();
        }
        if(clinicHistory.getDate_of_birth()==null){
            throw new FechaDeNacimientoVaciaException();
        }
        return clinicHistoryDAO.saveAndFlush(clinicHistory);
    }

    public ClinicHistory updateClinicHistory(HisClinicDTO hisClinicDTO) {

        ClinicHistory clinicHistory = clinicHistoryDAO.findByIdentification(hisClinicDTO.getIdentification()).orElseThrow(()-> new IdentificationNotFoundException());
        clinicHistory.setName(hisClinicDTO.getName());
        clinicHistory.setLast_name(hisClinicDTO.getLast_name());
        clinicHistory.setCity(hisClinicDTO.getCity());
        clinicHistory.setComment(hisClinicDTO.getComment());
        if (hisClinicDTO.getDate_of_birth() == null){
            throw new FechaDeNacimientoVaciaException();
        }
        clinicHistory.setDate_of_birth(hisClinicDTO.getDate_of_birth());
        return clinicHistoryDAO.saveAndFlush(clinicHistory);
    }
}