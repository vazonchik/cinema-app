package cinema.config;

import cinema.model.Role;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/register").permitAll()
                .antMatchers(HttpMethod.GET, "/cinema-halls",
                        "/movies", "/movie-sessions/available")
                .hasAnyRole(Role.RoleEnum.USER.name(), Role.RoleEnum.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/cinema-halls",
                        "/movies", "/movie-sessions").hasRole(Role.RoleEnum.ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/movie-sessions/{id}")
                .hasRole(Role.RoleEnum.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/movie-sessions/{id}")
                .hasRole(Role.RoleEnum.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/orders").hasRole(Role.RoleEnum.USER.name())
                .antMatchers(HttpMethod.POST, "/orders/complete")
                .hasRole(Role.RoleEnum.USER.name())
                .antMatchers(HttpMethod.PUT, "/shopping-carts/movie-sessions")
                .hasRole(Role.RoleEnum.USER.name())
                .antMatchers(HttpMethod.GET, "/shopping-carts/by-user")
                .hasRole(Role.RoleEnum.USER.name())
                .antMatchers(HttpMethod.GET, "/users/by-email")
                .hasRole(Role.RoleEnum.ADMIN.name())
                .and()
                .formLogin()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
