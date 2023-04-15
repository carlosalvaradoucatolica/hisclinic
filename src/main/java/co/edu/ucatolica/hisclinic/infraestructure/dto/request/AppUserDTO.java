package co.edu.ucatolica.hisclinic.infraestructure.dto.request;

import co.edu.ucatolica.hisclinic.domain.model.AppUser;
import co.edu.ucatolica.hisclinic.domain.service.util.EmailValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDTO {
    @Bean
    private EmailValidator emailValidator() {
        return new EmailValidator();
    }
    private String email;
    private String firstName;
    private String lastName;
    private Boolean enabled = false;
    private String password;

    public AppUser valid(){
        if(
            emailValidator().test(this.email)
            && email != null
            && !email.isBlank()
            && firstName != null
            && !firstName.isBlank()
            && lastName != null
            && !lastName.isBlank()
            && password != null
            && !password.isBlank()
        ) {
            return new AppUser(
                    this.email,
                    this.firstName,
                    this.lastName,
                    false,
                    this.password,
                    null
            );
        } else {
            return null;
        }
    }

    public AppUser validAtLogin(){
        if(
                emailValidator().test(this.email)
                &&   !email.isBlank()
                &&   !password.isBlank()
        ) {
            return new AppUser(
                    this.email,
                    null,
                    null,
                    null,
                    this.password,
                    null
            );
        } else {
            return null;
        }
    }
}
