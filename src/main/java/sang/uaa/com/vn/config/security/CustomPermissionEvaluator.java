package sang.uaa.com.vn.config.security;

import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import sang.uaa.com.vn.entites.Authorizer;

public class CustomPermissionEvaluator implements PermissionEvaluator {
	
	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		
		if ((authentication == null) || (targetDomainObject == null) || !(permission instanceof String)) {
			return false;
		}
		
		return hasPrivilege(authentication, (String) permission);
	}
	
	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		
		if ((authentication == null) || (targetType == null) || !(permission instanceof String)) {
			return false;
		}
		
		return hasPrivilege(authentication, (String) permission);
	}
	
	private boolean hasPrivilege(Authentication auth, String permission) {
		
		Authorizer authorizer = (Authorizer) auth.getPrincipal();
		
		return authorizer.getPrivileges().contains(permission);
		
	}
	
}
