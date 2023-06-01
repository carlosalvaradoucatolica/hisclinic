package co.edu.ucatolica.hisclinic.application.usecases.hisclinic;

import co.edu.ucatolica.hisclinic.domain.model.Auditorium;
import co.edu.ucatolica.hisclinic.domain.model.ClinicHistory;
import co.edu.ucatolica.hisclinic.domain.service.TrazabilidadService;
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
        if (!clinicHistoryDAO.findByIdentification(clinicHistory.getIdentification()).isPresent()){
            throw new IdentificationException();
        }
        return clinicHistoryDAO.saveAndFlush(clinicHistory);
    }
}