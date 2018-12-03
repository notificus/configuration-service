/*
package configuration.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
*/
/*import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.web.CasAuthenticationFilter;*//*

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;

import static java.lang.String.format;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    //@Autowired AuthenticationEntryPoint authenticationEntryPoint;
    //@Autowired AuthenticationProvider authenticationProvider;

    @Value("${notification-service.ip}")
    private String notificationServiceIp;

    @Value("${configuration-web-app.ip}")
    private String configurationWebAppIp;

*/
/*    @Bean
    public CasAuthenticationFilter casAuthenticationFilter(ServiceProperties sP)
            throws Exception {
        CasAuthenticationFilter filter = new CasAuthenticationFilter();
        filter.setServiceProperties(sP);
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }*//*


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/users/**")
                //.permitAll()
                .access("hasIpAddress('" + notificationServiceIp + "') or hasIpAddress('" + configurationWebAppIp + "')")
                .antMatchers("/**")
                //.permitAll()
                .authenticated()
                .and()
                .httpBasic();
        //.authenticationEntryPoint(authenticationEntryPoint);
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withUsername()
                .build();

        return new InMemoryUserDetailsManager(user);
    }

*/
/*    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(
                Arrays.asList(authenticationProvider));
    }*//*

}
*/
