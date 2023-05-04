package co.edu.ucatolica.hisclinic.domain.service;

import co.edu.ucatolica.hisclinic.domain.model.Auditoria;
import co.edu.ucatolica.hisclinic.infraestructure.repository.AuditoriaDAO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TrasabilidadServiceBDStrategy implements TrasabilidadService {
    @Autowired
    private AuditoriaDAO auditoriaDAO;
    @Override
    public void guardarAuditoria(Auditoria auditoria) {
        auditoriaDAO.saveAndFlush(auditoria);
    }
}
