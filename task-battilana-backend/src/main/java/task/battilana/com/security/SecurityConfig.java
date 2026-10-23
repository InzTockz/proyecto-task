package task.battilana.com.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import task.battilana.com.jwt.JwtFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity http, JwtFilter jwtFilter) throws Exception {
        return http
                .cors( cors -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedOrigins(List.of("*"));
                    corsConfiguration.setAllowedHeaders(List.of("*"));
                    corsConfiguration.setAllowedMethods(List.of("*"));
                })
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/v1/api/usuario/listar").hasAnyRole("ADMINISTRADOR")
                                //.requestMatchers("/v1/api/usuario/registrar").hasAnyRole("ADMINISTRADOR")
                                .requestMatchers("/v1/api/tareas/registrar").hasAnyRole("ADMINISTRADOR", "USUARIO")
                                .requestMatchers("/v1/api/tareas/eliminar/**").hasAnyRole("ADMINISTRADOR", "USUARIO")
                                .requestMatchers("/v1/api/tareas/actualizar-estado/**").hasAnyRole("ADMINISTRADOR", "USUARIO")
                                //LISTADO
                                .requestMatchers("/v1/api/tareas/listado-pendiente/usuario/**").hasAnyRole("ADMINISTRADOR", "USUARIO")
                                .requestMatchers("/v1/api/tareas/listado-terminado/usuario/**").hasAnyRole("ADMINISTRADOR", "USUARIO")
                                .requestMatchers("/v1/api/tareas/listado-total/usuario/**").hasAnyRole("ADMINISTRADOR", "USUARIO")
                                //CONTADORES
                                .requestMatchers("/v1/api/tareas/contador-pendientes/usuario/**").hasAnyRole("ADMINISTRADOR", "USUARIO")
                                .requestMatchers("/v1/api/tareas/contador-completado/usuario/**").hasAnyRole("ADMINISTRADOR", "USUARIO")
                                .requestMatchers("/v1/api/tareas/contador-total/usuario/**").hasAnyRole("ADMINISTRADOR", "USUARIO")
                                //CARPETAS
                                .requestMatchers("/v1/api/carpetas/**").hasAnyRole("ADMINISTRADOR", "USUARIO")
                                .requestMatchers("/v1/api/usuario/login").permitAll()
                                .requestMatchers("/v1/api/usuario/registrar").permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
