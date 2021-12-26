package by.gstu.workout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class WorkoutApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(WorkoutApplication.class, args);
    }

    @Configuration
    static class WebConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .anyRequest().authenticated()

                    // set logout URL
                    .and().logout().logoutSuccessUrl("/")

                    // enable OAuth2/OIDC
                    .and().oauth2Client()
                    .and().oauth2Login();
        }
    }

}
