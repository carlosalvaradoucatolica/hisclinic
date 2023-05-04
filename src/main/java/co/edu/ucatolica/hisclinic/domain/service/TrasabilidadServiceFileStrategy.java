package co.edu.ucatolica.hisclinic.domain.service;

import co.edu.ucatolica.hisclinic.domain.model.Auditoria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TrasabilidadServiceFileStrategy implements TrasabilidadService {
    @Override
    public void guardarAuditoria(Auditoria auditoria) {
        System.out.println("Guardado en archivo");
    }
}
