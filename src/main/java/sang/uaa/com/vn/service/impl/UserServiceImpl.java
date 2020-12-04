package sang.uaa.com.vn.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import sang.uaa.com.vn.common.service.BaseService;
import sang.uaa.com.vn.dto.RoleDto;
import sang.uaa.com.vn.dto.UserDto;
import sang.uaa.com.vn.entites.Authorizer;
import sang.uaa.com.vn.entites.Role;
import sang.uaa.com.vn.entites.User;
import sang.uaa.com.vn.repository.UserRepository;
import sang.uaa.com.vn.service.UserService;
import sang.uaa.com.vn.utils.exception.NotFoundException;

@Service
public class UserServiceImpl extends BaseService implements UserService, UserDetailsService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public Authorizer loadUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if (ObjectUtils.isEmpty(user)) {
			throw new UsernameNotFoundException(username);
		}
		return new Authorizer(user);
	}
	
	/**
	 * 
	 * <p>create user</p>
	 * Nov 13, 2020
	 *-------------------
	 * @author macbook
	 *
	 */
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setPassword(passwordEncode(userDto.getPassword()));
		Set<Role> roles = new HashSet<>();
		for (RoleDto roleDto : userDto.getRoles()) {
			roles.add(new Role(roleDto.getId(), roleDto.getName()));
		}
		user.setRoles(roles);
		if (ObjectUtils.isEmpty(user)) {
			LOG.error("User: {} is Empty",user);
			throw new NotFoundException("USER IS NULL");
			throw new NotFoundException("USER OR PASSWORD IS NULL");
		} else {
			setCreateInfo(user);
			userRepository.save(user);
		}
		
		return dozerBeanMapper.map(user, UserDto.class);
	}
	
	/**
	 * 
	 * <p>find All User</p>
	 * Nov 28, 2020
	 *-------------------
	 * @author macbook
	 *
	 */
	@Override
	public List<UserDto> findAllUser() {
		
		List<UserDto> userDtos = new ArrayList<>();
		List<User> users = userRepository.findAll();
		users.stream().forEach(user -> userDtos.add(dozerBeanMapper.map(user, UserDto.class)));
		return userDtos;
	}
	
	/**
	 * 
	 * <p>password Encode</p>
	 * <p>Nov 28, 2020</p>
	 *-------------------
	 * @author macbook
	 * @param password
	 * @return password encode
	 */
	private String passwordEncode(String password) {
		return passwordEncoder.encode(password);
	}
	
}
