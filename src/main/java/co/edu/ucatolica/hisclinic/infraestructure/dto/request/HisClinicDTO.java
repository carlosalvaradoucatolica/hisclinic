package co.edu.ucatolica.hisclinic.infraestructure.dto.request;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HisClinicDTO {
    private int identification;
    private String city;
    private LocalDate date_of_birth;
    private String last_name;
    private String name;
    private String type;
    @NotNull
    private String comment;
}
