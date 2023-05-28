package co.edu.ucatolica.hisclinic.application.usecases.hisclinic;

import co.edu.ucatolica.hisclinic.domain.model.Auditorium;
import co.edu.ucatolica.hisclinic.domain.model.ClinicHistory;

public interface HisClinicService {
    public ClinicHistory save(ClinicHistory clinicHistory, Auditorium auditorium);
}
