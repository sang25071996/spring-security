package sang.uaa.com.vn.config.security.jwt;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import sang.uaa.com.vn.entites.Authorizer;
import sang.uaa.com.vn.entites.Privilege;
import sang.uaa.com.vn.entites.Role;

@Component
public class TokenProvider {
	
	private static final Logger LOG = LoggerFactory.getLogger(TokenProvider.class);
	@Value("${jwt.secret}")
	private String secret;
	private static final long EXPIRED_TIME = 86400000;
	
	public String generateToken(Authorizer authorizer) {
		Map<String, Object> claims = new HashMap<>();
		List<String> roles = new ArrayList<>();
		List<String> privileges = new ArrayList<>();
		for (Role role : authorizer.getUser().getRoles()) {
			roles.add(role.getName());
			for (Privilege privilege : role.getPrivileges()) {
				privileges.add(privilege.getName());
			}
		}
		
		claims.put("scopes", roles);
		claims.put("privileges", privileges);
		return Jwts.builder().setClaims(claims).setSubject(authorizer.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRED_TIME))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}
	
	public String getUsernameFromToken(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}
	
	public Jws<Claims> parseClaims(String token) {
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
		} catch (SignatureException | MalformedJwtException e) {
			LOG.error("Invalid JWT Signature.");
			LOG.trace("Invalid JWT Signature trace {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			LOG.error("Expired JWT Token");
			LOG.trace("Expired JWT Token trace {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			LOG.error("Unsupported JWT Token ");
			LOG.trace("Unsupported JWT Token  trace {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			LOG.error("JWT token compact of handler are invalid.");
			LOG.trace("JWT token compact of handler are invalid {}.", e.getMessage());
		}
		return claims;
	}
	
	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody();
	}
	
	public Date getExpiredTimeFromToken(String token) {
		return getAllClaimsFromToken(token).getExpiration();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (SignatureException | MalformedJwtException e) {
			LOG.error("Invalid JWT Signature.");
			LOG.trace("Invalid JWT Signature trace {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			LOG.error("Expired JWT Token");
			LOG.trace("Expired JWT Token trace {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			LOG.error("Unsupported JWT Token ");
			LOG.trace("Unsupported JWT Token  trace {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			LOG.error("JWT token compact of handler are invalid.");
			LOG.trace("JWT token compact of handler are invalid {}.", e.getMessage());
		}
		return false;
	}
}
