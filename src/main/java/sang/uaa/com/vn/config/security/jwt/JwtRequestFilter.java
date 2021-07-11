package sang.uaa.com.vn.config.security.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import sang.uaa.com.vn.constant.Constants;
import sang.uaa.com.vn.entites.Authorizer;
import sang.uaa.com.vn.service.impl.UserServiceImpl;
import sang.uaa.com.vn.user.entites.Role;
import sang.uaa.com.vn.user.entites.User;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	private static final Logger LOG = LoggerFactory.getLogger(JwtRequestFilter.class);
	
	@Autowired
	private TokenProvider tokenProvider;
	@Autowired
	private UserServiceImpl jwtUserDetailsService;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		 String header = request.getHeader(Constants.AUTHORIZATION_HEADER);
		 String token = "";
		 if(header != null && header.startsWith("Bearer")) {
			 LOG.info("JWT Token begin Bearer String");
			 token = header.substring(7);
			 Jws<Claims> claims = tokenProvider.parseClaims(token);
			 String username = claims.getBody().getSubject();
			 if (StringUtils.isNotEmpty(username)) {
				 List<String> scopes = claims.getBody().get("scopes", List.class);
				 List<String> privileges = claims.getBody().get("privileges", List.class);
				 
				 List<Role> roles = new ArrayList<>();
				 Role role;
				 for(String name : scopes) {
					 role = new Role();
					 role.setName(name);
					 roles.add(role);
				 }
				 User user = User.builder().username(username).roles(new HashSet<>(roles)).build();
				 Authorizer authorizer = Authorizer.builder().user(user).privileges(privileges).build();
				 UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						 authorizer, null, authorizer.getAuthorities());
				 SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			 }
		 } else {
			 LOG.warn("JWT Token does not begin with Bearer String");
		 }
		filterChain.doFilter(request, response);
	}

}
