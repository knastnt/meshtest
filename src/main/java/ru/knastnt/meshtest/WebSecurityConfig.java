package ru.knastnt.meshtest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.knastnt.meshtest.web.RootController;
import ru.knastnt.meshtest.web.profile.ProfileRestController;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors().disable()
                .csrf()
                .ignoringAntMatchers(ProfileRestController.REST_URL + "/*")
                .and()
            .authorizeRequests()
                .anyRequest()
                    .authenticated()
                    .and()
                .formLogin()
                    .defaultSuccessUrl("/swagger-ui/index.html", true)
                    .and()
                .httpBasic()
                    .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/exit", "GET"))
                    .logoutSuccessUrl(RootController.EXIT_SUCCESS_URL).permitAll();
    }


    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        UserDetails theUser = User.withUsername("user")
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
                .password("1").roles("USER").build();

        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

        userDetailsManager.createUser(theUser);

        return userDetailsManager;
    }

}
