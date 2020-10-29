package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/conferenceData/**").authenticated()
                .antMatchers("/value").anonymous()
                .antMatchers("/h2-console").permitAll()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()

                .and()
                .formLogin()
//                .loginPage("/login")
                .failureUrl("/login?error=true")
//                .loginProcessingUrl("/perform_login")
//                .permitAll()
//                .defaultSuccessUrl("/", true)

                .and()
                .logout()
//                .logoutSuccessUrl("/login?logout=true")
//                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/perform_logout", "GET"))
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID")
//                .permitAll()
        ;

    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("pass"))
                .roles("ADMIN", "USER")
                .and()
                .withUser("user")
                .password(passwordEncoder().encode("pass"))
                .roles("USER");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
