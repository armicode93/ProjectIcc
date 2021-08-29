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
import org.springframework.security.crypto.password.Md4PasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity // integrate spring security with spring mvc, and enable web security support
public class SecurityConfiguration extends WebSecurityConfigurerAdapter { //this class overload config methods to config to prive our costum configuration

    @Autowired
    private DataSource dataSource;

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
    @Override// like this passiamo authentificationProvider to the config methode
    protected void configure (AuthenticationManagerBuilder auth) throws  Exception{
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select login as principal ,password as credentials ,true from users where login=?")
                //con principal e credentials diciamo a spring che 1 e username e l'altro e password
                //requette pour cherche le user
                .authoritiesByUsernameQuery("select r.role as role ,u.login as principal FROM role_user ru,roles r,users u where r.id = ru.role_id AND ru.user_id = u.id and u.login=?")
                //pour recuperer le role de l'utilisateur
                .passwordEncoder(passwordEncoder())
                .rolePrefix("ROLE_");
                //con principal e credentials diciamo a spring che 1 e username e l'altro e password
       /* auth.inMemoryAuthentication().withUser("admin").roles("admin","user");    //ca veut dire que les user il sont en memoire pour le moment
        auth.inMemoryAuthentication().withUser("user").roles("user");

        */


    }


    @Override // we are going to override config methods
    protected  void configure(HttpSecurity http) throws Exception{ //we have a config method
        //we are going to provide our configuration

        http.authorizeRequests().antMatchers(
                "/registration", // we have provided the acces to the different url
                "login",
                 "index",
                 "/"
                ).permitAll()

                //authenticate anyRequest to this url
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

        http.authorizeRequests().antMatchers("/artist/index").hasRole("USER");
        http.authorizeRequests().antMatchers("/locality/index").hasRole("USER");
        http.authorizeRequests().antMatchers("/location/index").hasRole("USER");
        http.authorizeRequests().antMatchers("/representation/index").hasRole("USER");
        http.authorizeRequests().antMatchers("/role/index").hasRole("USER");
        http.authorizeRequests().antMatchers("/show/index").hasRole("USER");
        http.authorizeRequests().antMatchers("/type/index").hasRole("USER");


        http.authorizeRequests().antMatchers("/artist/edit").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/artist/add").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/artist/show").hasRole("ADMIN");

        http.authorizeRequests().antMatchers("/locality/editLocality").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/locality/addLocality").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/locality/show").hasRole("ADMIN");

        http.authorizeRequests().antMatchers("/location/show").hasRole("ADMIN");

        http.authorizeRequests().antMatchers("/representation/show").hasRole("ADMIN");

        http.authorizeRequests().antMatchers("/role/show").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/role/editRole").hasRole("ADMIN");

        http.authorizeRequests().antMatchers("/type/show").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/type/editType").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/type/addType").hasRole("ADMIN");





        http.exceptionHandling().accessDeniedPage("/403"); //redirection quando non possiamo accedere ad una pagina offlimits,

    }
}
