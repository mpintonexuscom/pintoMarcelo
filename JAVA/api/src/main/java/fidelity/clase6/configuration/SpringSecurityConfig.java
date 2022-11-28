/*package fidelity.clase6.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.AuthenticationEntryPoint;

// CLASE PARA USUARIOS ESTATICOS

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfiguration {
    
    @Autowired AuthenticationEntryPoint auth;

    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().authorizeRequests().anyRequest().authenticated().
        and().httpBasic().authenticationEntryPoint(auth);
    }
}*/
