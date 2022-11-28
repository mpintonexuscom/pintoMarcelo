package fidelity.clase6.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
 
    @Autowired
    private UserDetailsService userDetails;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Bean
    private DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetails);
        authProvider.setPasswordEncoder(encoder);

        return authProvider;
    };
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authenticationProvider(authProvider());

        http.authorizeRequests().
        antMatchers("/login").permitAll().
        antMatchers("/usuarios/**").hasAnyAuthority("BASICO","PREMIUM").
        anyRequest().authenticated().and().logout().permitAll();

        http.headers().frameOptions().sameOrigin();

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer customizer() {
        return (web) -> web.ignoring().antMatchers("/imagenes/**", "/js/**", "/webjars/**");
    }
}
