package co.edu.ucatolica.hisclinic.infraestructure.dto.request;

import co.edu.ucatolica.hisclinic.domain.model.AppUser;
import co.edu.ucatolica.hisclinic.domain.service.util.EmailValidator;
import lombok.Data;

import javax.persistence.Column;

@Data
public class AppUserDTO {
    private String email;
    private String firstName;
    private String lastName;
    private Boolean enabled = false;
    private String password;
    private final EmailValidator emailValidator;

    public AppUser valid(){
        if(
            emailValidator.test(this.email)
            &&   !email.isBlank()
            &&   !firstName.isBlank()
            &&   !lastName.isBlank()
            &&   !password.isBlank()
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
                emailValidator.test(this.email)
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
