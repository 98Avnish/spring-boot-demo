//package com.example.demo.config;
//
//import lombok.AllArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableWebSecurity
//@AllArgsConstructor
//public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private DataSource dataSource;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        // For Both Encoding & Matching Password
//        DelegatingPasswordEncoder encoder = (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        encoder.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder());
//        return encoder;
//    }
//
//
//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/conferenceData/**").authenticated()
//                .antMatchers("/value").anonymous()
//                .antMatchers("/h2-console/**").permitAll()
//                .antMatchers("/**").permitAll()
//                .anyRequest().authenticated()
//
//                .and()
//                .formLogin()
////                .loginPage("/login")
//                .failureUrl("/login?error=true")
////                .loginProcessingUrl("/perform_login")
////                .permitAll()
////                .defaultSuccessUrl("/", true)
//
//                .and()
//                .logout()
////                .logoutSuccessUrl("/login?logout=true")
////                .logoutUrl("/logout")
//                .logoutRequestMatcher(new AntPathRequestMatcher("/perform_logout", "GET"))
////                .invalidateHttpSession(true)
////                .deleteCookies("JSESSIONID")
////                .permitAll()
//        ;
//
//        // For H2-Console Only
//        http.csrf().disable();
//        http.headers().frameOptions().disable();
//    }
//
//    @Override
//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        //this.configureInMemAuthentication(auth);
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(passwordEncoder())
//        ;
//    }
//
//    protected void configureInMemAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password(passwordEncoder().encode("pass"))
//                .roles("ADMIN", "USER")
//                .and()
//                .withUser("user")
//                .password(passwordEncoder().encode("pass"))
//                .roles("USER");
//    }
//
//}
