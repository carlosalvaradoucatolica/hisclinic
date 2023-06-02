package co.edu.ucatolica.hisclinic.domain.service.integration;

import co.edu.ucatolica.hisclinic.application.usecases.hisclinic.IdentificationNotFoundException;
import co.edu.ucatolica.hisclinic.infraestructure.dto.request.HisClinicDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class HisClinicControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldAddHisClinic() throws Exception{

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .registerModule(new JavaTimeModule())
                .writer();

        HisClinicDTO hisClinicDTO =  new HisClinicDTO(10012026,"Bogota", LocalDate.of(2002,06,16),"Angel","Daniel","CC","His");
        String payload = writer.writeValueAsString(hisClinicDTO);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.post("/clinicHistory/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payload))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    /*
    @Test
    void shouldExceptionUpdateHisClinic() throws Exception{

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .registerModule(new JavaTimeModule())
                .writer();

        HisClinicDTO hisClinicDTO =  new HisClinicDTO(10012026,"Bogota", LocalDate.of(2002,06,16),"Angel","Daniel","CC","His");
        String payload = writer.writeValueAsString(hisClinicDTO);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.post("/clinicHistory/update")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payload))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }
    */
}
