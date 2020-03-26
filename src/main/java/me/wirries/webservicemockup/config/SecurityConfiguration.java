package me.wirries.webservicemockup.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * This is the security configuration of the application. The user will validate against the parameter
 * from the VM arguments:
 * -Dspring.security.user.name=user
 * -Dspring.security.user.password=pass
 *
 * @author denisw
 * @version 1.0
 * @since 26.03.20
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LOGGER.info("Configure web security for resources");
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic().realmName("WebServiceMockup")
                .and()
                .csrf().disable();
    }

}
