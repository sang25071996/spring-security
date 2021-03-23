package sang.uaa.com.vn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import sang.uaa.com.vn.config.security.JwtAccessDeniedHandler;
import sang.uaa.com.vn.config.security.RestAuthenticationEntryPoint;
import sang.uaa.com.vn.config.security.jwt.JwtRequestFilter;
import sang.uaa.com.vn.service.impl.UserServiceImpl;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserServiceImpl jwtUserDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManager) throws Exception {
		authenticationManager.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// disabled request into app
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
				"/configuration/security", "/swagger-ui.html", "/webjars/**");
	}

	@Bean
	public RestAuthenticationEntryPoint authenticationEntryPoint() {
		return new RestAuthenticationEntryPoint();
	}

	@Bean
	public JwtAccessDeniedHandler jwtAccessDeniedHandler() {
		return new JwtAccessDeniedHandler();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	//sessionRegistry the session registry which should be updated 
	//when the authenticated session is changed.
    @Bean
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(
          new SessionRegistryImpl());
    }

	/*
	 * Định nghĩa các request được access vào app
	 * 
	 * 
	 */
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.httpBasic()
					.and()
					.authorizeRequests()
					.antMatchers("/authenticate").permitAll()
					.antMatchers("/logout").permitAll()
					.antMatchers("//logout-success").permitAll()
					.antMatchers("/role").hasRole("ADMIN")
					.anyRequest().authenticated()
					.and().csrf().disable().formLogin().disable();
		//TODO: Disable session jwt
//		httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		httpSecurity.sessionManagement().sessionAuthenticationStrategy(sessionAuthenticationStrategy())
				.and()
				.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
				.and().exceptionHandling().accessDeniedHandler(jwtAccessDeniedHandler()).accessDeniedPage("/access-denied").and()
				.logout().logoutUrl("/logout").invalidateHttpSession(true)
				.addLogoutHandler(new SecurityContextLogoutHandler())
				.logoutSuccessUrl("/logout-success")
				.deleteCookies("JSESSIONID");
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}
}
