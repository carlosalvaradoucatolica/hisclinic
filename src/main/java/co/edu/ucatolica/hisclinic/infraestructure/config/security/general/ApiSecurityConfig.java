package co.edu.ucatolica.hisclinic.infraestructure.config.security.general;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
    //@Autowired
    //AppUserService appUserService; //UserDetailsServiceImpl

    //@Autowired
    //JwtEntryPoint jwtEntryPoint;

    //@Autowired
    //PasswordEncoder passwordEncoder;

    //@Bean
    //public JwtTokenFilter jwtTokenFilter(){
    //    return new JwtTokenFilter();
    //}

    //@Override
    //protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //    auth.userDetailsService(appUserService).passwordEncoder(passwordEncoder.bCryptPasswordEncoder());
    //}

    //@Override
    //@Bean
    //public AuthenticationManager authenticationManagerBean() throws Exception {
    //    return super.authenticationManagerBean();
    //}

    //@Override
    //protected AuthenticationManager authenticationManager() throws Exception {
    //    return super.authenticationManager();
    //}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/store/**").permitAll()
                .antMatchers("/clinicHistory/**").permitAll()
                .anyRequest().authenticated()
                .and()
                //.exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
                //.and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
