package com.spring.Librarymanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }


    // /student/ ->get ADMIN+student ,PUT Student ADMIN ,DELETE ADMIN,POST ADMIN
    // /book/ ->get ADMIN STUDENT,PUT/POST/DELETE ADMIN
    // /transaction/ (issue or return) -> GET STUDENT ADMIN,POST/PUT/DELETE ADMIN
    // /author/->no authentication or authorisation
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                httpBasic().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.PUT,"/student/**").hasAnyAuthority(AuthorityConstants.ADMIN_AUTHORITY,AuthorityConstants.STUDENT_AUTHORITY)
                .antMatchers(HttpMethod.GET,"/student/all/**").hasAuthority(AuthorityConstants.ADMIN_AUTHORITY)
                .antMatchers(HttpMethod.GET,"/student/**").hasAnyAuthority(AuthorityConstants.ADMIN_AUTHORITY,AuthorityConstants.STUDENT_AUTHORITY)
                .antMatchers("/student/**").hasAuthority(AuthorityConstants.ADMIN_AUTHORITY)
                .antMatchers(HttpMethod.GET,"/book/**").hasAnyAuthority(AuthorityConstants.ADMIN_AUTHORITY,AuthorityConstants.STUDENT_AUTHORITY)
                .antMatchers("/book/**").hasAuthority(AuthorityConstants.ADMIN_AUTHORITY)
                .antMatchers(HttpMethod.GET,"/transactions/all/**").hasAuthority(AuthorityConstants.ADMIN_AUTHORITY)
                .antMatchers(HttpMethod.GET,"/transaction/**").hasAnyAuthority(AuthorityConstants.ADMIN_AUTHORITY,AuthorityConstants.STUDENT_AUTHORITY)
                .antMatchers("/transaction/**").hasAuthority(AuthorityConstants.ADMIN_AUTHORITY)
                .antMatchers("/**").permitAll()
                .and().formLogin();
    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
