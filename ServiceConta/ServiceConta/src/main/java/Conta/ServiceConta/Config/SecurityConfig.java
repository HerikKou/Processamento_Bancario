package Conta.ServiceConta.Config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> {})
            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
                    .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
		    .requestMatchers(HttpMethod.GET, "/api/conta/checkbalance").hasAnyRole("USER", "ADMIN")
            .requestMatchers(HttpMethod.GET, "/api/conta/validateBalance").hasAnyRole("USER", "ADMIN")
                     .requestMatchers(HttpMethod.GET, "/api/conta/getAllAccount").hasRole("ADMIN")
                     .requestMatchers(HttpMethod.POST,"/api/conta/debit").hasAnyRole("USER", "ADMIN")
                     .requestMatchers(HttpMethod.POST,"/api/conta/credit").hasAnyRole("USER", "ADMIN")
			.requestMatchers(HttpMethod.GET, "/api/conta/**").hasRole("USER")
                    .anyRequest().authenticated())
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
}

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}