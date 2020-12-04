package sang.uaa.com.vn.config.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class TokenProvider {
	
	private static final Logger LOG = LoggerFactory.getLogger(TokenProvider.class);
	@Value("${jwt.secret}")
	private String secret;
	private static final long EXPIRED_TIME = 86400000;
	
	public String generateToken(UserDetails userDetails) {
		 return Jwts.builder()
		 	.setSubject(userDetails.getUsername())
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
