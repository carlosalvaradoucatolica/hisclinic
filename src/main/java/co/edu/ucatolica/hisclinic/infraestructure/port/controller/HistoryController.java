package co.edu.ucatolica.hisclinic.infraestructure.port.controller;
import co.edu.ucatolica.hisclinic.domain.model.Auditorium;
import co.edu.ucatolica.hisclinic.domain.model.ClinicHistory;
import co.edu.ucatolica.hisclinic.application.usecases.hisclinic.HisClinicServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/clinicHistory")
@AllArgsConstructor
public class HistoryController {
    private final HisClinicServiceImpl hisClinicService;
    @PostMapping("/create")
    public ClinicHistory createHistoryClinic(@RequestBody ClinicHistory hisClinicDTO) throws JsonProcessingException { return hisClinicService.save(hisClinicDTO, new Auditorium()); }
}