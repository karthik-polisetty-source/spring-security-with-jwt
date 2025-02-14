package com.example.spring_security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    private UserDetailsService userDetailsService;

    private JwtAuthonticationFilterConfig jwtAuthonticationFilterConfig;

    public WebSecurityConfig(UserDetailsService userDetailsService,JwtAuthonticationFilterConfig jwtAuthonticationFilterConfig) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthonticationFilterConfig=jwtAuthonticationFilterConfig;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        request -> request
                                    .requestMatchers("/register","/login").permitAll()
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())

               .addFilterBefore(jwtAuthonticationFilterConfig ,UsernamePasswordAuthenticationFilter.class)
        ;

        return httpSecurity.build();

    }


  // @Bean

    public UserDetailsService userDetailsService(){

        UserDetails karthik=
                User.withUsername("karthik")
                        .password("{noop}password")
                        .roles("USER")
                        .build();

        UserDetails immu =
                User.withUsername("immu")
                        .password("{noop}password")
                        .roles("USER")
                        .build();

        UserDetails ashok =
                User.withUsername("ashok")
                        .password("{noop}password")
                        .roles("USER")
                        .build();

        return new  InMemoryUserDetailsManager(karthik,immu,ashok);

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder(14);
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        return provider;

    }

    @Bean
    public AuthenticationManager authenticationManager
            (AuthenticationConfiguration configuration ) throws Exception {

       return configuration.getAuthenticationManager();
    }
}
