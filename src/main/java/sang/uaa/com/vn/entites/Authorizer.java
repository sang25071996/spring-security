package sang.uaa.com.vn.entites;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * <p>Authorizer</p>
 * <p>Nov 28, 2020</p>
 * -------------------
 * @author macbook
 */

public class Authorizer implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	private User user;
	private List<String> privileges = new ArrayList<>();
	
	public Authorizer(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getGrantedAuthorities(user.getRoles());
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}
	
	@Override
	public String getUsername() {
		return user.getUsername();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public List<String> getPrivileges(Collection<Role> roles) {
		 
        for (Role role : roles) {
            privileges.add(role.getName());
        }
        return privileges;
    }
	
    private List<GrantedAuthority> getGrantedAuthorities(Set<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        getPrivileges(roles);
        for (Role privilege : roles) {
            authorities.add(new SimpleGrantedAuthority(privilege.getName()));
        }
        return authorities;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
