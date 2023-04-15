package co.edu.ucatolica.hisclinic.application.usecases.auth;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
@NoArgsConstructor
public class SignUpUseCaseImpl {
    @Bean
    private BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //TODO: Encode password to persist
    //bCryptPasswordEncoder().encode(appUser.getPassword())
}
