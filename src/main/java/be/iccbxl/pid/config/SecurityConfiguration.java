package be.iccbxl.pid.config;

import be.iccbxl.pid.model.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity // integrate spring security with spring mvc, and enable web security support
public class SecurityConfiguration extends WebSecurityConfigurerAdapter { //this class overload config methods to config to prive our costum configuration

    @Autowired
    private UserService userService;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() // to encode password,criptage
    {
        return new BCryptPasswordEncoder();
    }

    //To integrate Spring data JPA and hybernate in Spring security we need to provide a bean
    @Bean
    public DaoAuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
    @Override// like this passimo authentificationProvider to the config methode
    protected void configure (AuthenticationManagerBuilder auth) throws  Exception{
        auth.authenticationProvider(authenticationProvider());
    }


    @Override // we are going to override config methods
    protected  void configure(HttpSecurity http) throws Exception{ //we have a config method
        //we are going to provide our configuration
        http.authorizeRequests().antMatchers(
                "/registration**", // we have provided the acces to the different url
                "/js/**",
                "/css/**",
                "/img/**").permitAll()
               .anyRequest().authenticated() //authenticate anyRequest to this url
                .and()
                .formLogin()// we are going to use a form login
                .loginPage("/login") //costum login page
                .permitAll()//al user can access to this url
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher( new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                .permitAll(); // permit acces to this url



    }
}
