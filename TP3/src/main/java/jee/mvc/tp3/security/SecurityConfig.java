package jee.mvc.tp3.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("Hakim").password(passwordEncoder.encode("1234")).roles("USER").build(),
                User.withUsername("Yacer").password(passwordEncoder.encode("1234")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder.encode("password")).roles("USER", "ADMIN").build()
        );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
                httpSecurity.authorizeHttpRequests(authorize ->
                authorize.requestMatchers("/webjars/**").permitAll()
        );
        httpSecurity.formLogin(
                formLoginConfigurer ->
                        formLoginConfigurer.loginPage("/login").permitAll()
        );
//        httpSecurity.authorizeHttpRequests(authorize ->
//                authorize.requestMatchers("/add").hasRole("ADMIN")
//                        .requestMatchers("/edit/**").hasRole("ADMIN")
//                        .requestMatchers("/patient").hasRole("ADMIN")
//                        .requestMatchers("/**").permitAll()
//        );
        httpSecurity.authorizeHttpRequests(authorize ->
                authorize.anyRequest().authenticated()
        );
        httpSecurity.exceptionHandling(exceptionHandlingConfigurer ->
                exceptionHandlingConfigurer.accessDeniedPage("/403")
        );
        httpSecurity.rememberMe(Customizer.withDefaults());

        return httpSecurity.build();
    }
}
