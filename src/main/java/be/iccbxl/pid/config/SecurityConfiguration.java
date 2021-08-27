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
    @Override// like this passimo authentificationProvider to the config methode
    protected void configure (AuthenticationManagerBuilder auth) throws  Exception{
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select login ,password ,true from users where login=?")
                //con principal e credentials diciamo a spring che 1 e username e l'altro e password
                //requette pour cherche le user
                .authoritiesByUsernameQuery("SELECT roles.role as role,users.login as principal ,users.password,users.firstname,users.lastname,users.email,users.langue FROM role_user,roles,users WHERE role_user.role_id =roles.id and users.login =?")
                //pour recuperer le role de l'utilisateur
                .passwordEncoder(new BCryptPasswordEncoder())
                .rolePrefix("ROLE_");
                //con principal e credentials diciamo a spring che 1 e username e l'altro e password
       /* auth.inMemoryAuthentication().withUser("admin").roles("admin","user");    //ca veut dire que les user il sont en memoire pour le moment
        auth.inMemoryAuthentication().withUser("user").roles("user");

        */


    }


    @Override // we are going to override config methods
    protected  void configure(HttpSecurity http) throws Exception{ //we have a config method
        //we are going to provide our configuration
        http.authorizeRequests().antMatchers("/artist/index").hasRole("user");
        http.authorizeRequests().antMatchers("/locality/index").hasRole("user");
        http.authorizeRequests().antMatchers("/location/index").hasRole("user");
        http.authorizeRequests().antMatchers("/representation/index").hasRole("user");
        http.authorizeRequests().antMatchers("/role/index").hasRole("user");
        http.authorizeRequests().antMatchers("/show/index").hasRole("user");
        http.authorizeRequests().antMatchers("/type/index").hasRole("user");
        http.authorizeRequests().antMatchers("/login","/index").hasRole("user");

        http.authorizeRequests().antMatchers("/artist/edit").hasRole("admin");
        http.authorizeRequests().antMatchers("/artist/add").hasRole("admin");
        http.authorizeRequests().antMatchers("/artist/show").hasRole("admin");

        http.authorizeRequests().antMatchers("/locality/editLocalty").hasRole("admin");
        http.authorizeRequests().antMatchers("/locality/addLocality").hasRole("admin");
        http.authorizeRequests().antMatchers("/locality/show").hasRole("admin");

        http.authorizeRequests().antMatchers("/location/show").hasRole("admin");

        http.authorizeRequests().antMatchers("/representation/show").hasRole("admin");

        http.authorizeRequests().antMatchers("/role/show").hasRole("admin");
        http.authorizeRequests().antMatchers("/role/editRole").hasRole("admin");

        http.authorizeRequests().antMatchers("/type/show").hasRole("admin");
        http.authorizeRequests().antMatchers("/type/editType").hasRole("admin");
        http.authorizeRequests().antMatchers("/type/addType").hasRole("admin");



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

        http.exceptionHandling().accessDeniedPage("/   403"); //redirection quando non possiamo accedere ad una pagina offlimits,

    }
}
