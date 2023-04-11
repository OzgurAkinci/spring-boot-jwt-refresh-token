package com.app.spring.security.jwt.security;

import com.app.spring.security.jwt.security.filters.BasicAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.spring.security.jwt.security.jwt.AuthEntryPointJwt;
import com.app.spring.security.jwt.security.filters.JwtAuthenticationFilter;
import com.app.spring.security.jwt.security.services.UserDetailsServiceImpl;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;



    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.antMatcher("/api/**").addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated()
                ).httpBasic(withDefaults());
        http.authenticationProvider(authenticationProvider());
        return http.build();
    }

    @Bean
    public SecurityFilterChain formLoginFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                );
        http.formLogin()
                .loginPage("/system/auth/signIn")
                .defaultSuccessUrl("/system/management/dashboard")
                .permitAll()
                .and()
                .logout()
                .permitAll();
        return http.build();
    }

    /*
    @Bean
    public SecurityFilterChain jwtFilterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.antMatcher("/api/**")
                .authorizeRequests().antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/test/**").permitAll()
                .anyRequest().authenticated();
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public SecurityFilterChain basicFilterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authenticationProvider(authenticationProvider())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.antMatcher("/system/**")
                .authorizeRequests()
                .antMatchers("/system/auth/sign-in").permitAll()
                .antMatchers("/system/management/**").hasRole("ADMIN")
                .anyRequest().permitAll();
        http.addFilterBefore(authenticationBasicFilter(), UsernamePasswordAuthenticationFilter.class);

        http.formLogin()
                .loginPage("/system/auth/signIn")
                .defaultSuccessUrl("/system/management/dashboard")
                .permitAll()
                .and()
                .logout()
                .permitAll();

        return http.build();

     */
        /*
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/system/auth/**").permitAll()
                .antMatchers("/system/management/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated();
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authenticationBasicFilter(), UsernamePasswordAuthenticationFilter.class);

        http.formLogin()
                .loginPage("/system/auth/signIn")
                .defaultSuccessUrl("/system/management/dashboard")
                .permitAll()
                .and()
                .logout()
                .permitAll();
        return http.build();

         */

}
