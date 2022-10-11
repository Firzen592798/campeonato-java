package br.com.firzen.campeoanto.config;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.firzen.campeoanto.rest.config.JwtAuthenticationEntryPoint;
import br.com.firzen.campeoanto.rest.config.JwtRequestFilter;
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@EnableWebSecurity //Autoriza o Spring Security a suportar e prover a integração com Spring MVC
public class WebSecurityConfig {

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    
    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("teste").password("{noop}teste").roles("ADMIN");
    }*/
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
 
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http.csrf().disable()
		// dont authenticate this particular request
		.authorizeRequests().antMatchers("/authenticate").permitAll().
		and().authorizeRequests().antMatchers("/firzen/authenticate").permitAll().
		antMatchers("/rest/**").authenticated();
		
    	//http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	
    	http = http.exceptionHandling()
                .authenticationEntryPoint(
                    (request, response, ex) -> {
                        response.sendError(
                            HttpServletResponse.SC_UNAUTHORIZED,
                            ex.getMessage()
                        );
                    }
                )
                .and();

    	http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    			
        http.authorizeRequests().antMatchers("/", "/home").permitAll()
			//.antMatchers("/campeonato/**").authenticated()
			//.antMatchers("/participante/**").authenticated()
			.anyRequest().permitAll()
			.and()
		.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/campeonato", true)
			.permitAll()
			.and()
		.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/login")
			.permitAll()
			.and()
        .exceptionHandling().accessDeniedPage("/403");
        http.headers().frameOptions().sameOrigin();
 
        return http.build();
    }
 
    
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
    }
}