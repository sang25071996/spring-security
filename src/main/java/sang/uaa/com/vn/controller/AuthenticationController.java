package sang.uaa.com.vn.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import sang.uaa.com.vn.config.security.jwt.TokenProvider;
import sang.uaa.com.vn.dto.LoginDto;
import sang.uaa.com.vn.entites.Authorizer;
import sang.uaa.com.vn.jwt.payload.LoginRespone;
import sang.uaa.com.vn.jwt.payload.Token;
import sang.uaa.com.vn.service.impl.UserServiceImpl;

@Controller
public class AuthenticationController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenProvider tokenProvider;
	
	@Autowired
	private UserServiceImpl userDetailsService;

	@PostMapping("/authenticate")
	public ResponseEntity<LoginRespone> authenticate(@Valid @RequestBody LoginDto loginDto) {
		UsernamePasswordAuthenticationToken authenToken = new UsernamePasswordAuthenticationToken(
				loginDto.getUsername(), loginDto.getPassword());
		Authentication authentication = authenticationManager.authenticate(authenToken);
		Authorizer authorizer = userDetailsService.loadUserByUsername(loginDto.getUsername());
		String tokenJwt = tokenProvider.generateToken(authorizer);
		Token token = new Token();
		token.setTokenType(tokenJwt);
		LOG.info("Expire time for Token {}", tokenProvider.getExpiredTimeFromToken(tokenJwt));
		String expiredTime = String.valueOf(tokenProvider.getExpiredTimeFromToken(tokenJwt).getTime());
		token.setExpireTime(expiredTime);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		HttpHeaders httpHeader = new HttpHeaders();
		List<String> roles = authorizer.getPrivileges(authorizer.getUser().getRoles());
		return new ResponseEntity<>(new LoginRespone(authorizer.getUsername(), roles, token), httpHeader, HttpStatus.OK);
	}
}
