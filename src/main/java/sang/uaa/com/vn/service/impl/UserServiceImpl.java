package sang.uaa.com.vn.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import sang.uaa.com.vn.common.MessageEnum;
import sang.uaa.com.vn.common.service.BaseService;
import sang.uaa.com.vn.constant.Constants;
import sang.uaa.com.vn.dto.RoleDto;
import sang.uaa.com.vn.dto.UserDto;
import sang.uaa.com.vn.entites.Authorizer;
import sang.uaa.com.vn.entites.Role;
import sang.uaa.com.vn.entites.User;
import sang.uaa.com.vn.exception.NotFoundException;
import sang.uaa.com.vn.mapper.UserMapper;
import sang.uaa.com.vn.repository.UserRepository;
import sang.uaa.com.vn.service.UserService;
import sang.uaa.com.vn.utils.MessageUtils;

@Service
public class UserServiceImpl extends BaseService implements UserService, UserDetailsService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	private UserMapper userMapper;
	
	public UserServiceImpl() {
		this.userMapper = getInstanceMappger(UserMapper.class);
	}
	
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
	 * <p>
	 * create user
	 * </p>
	 * Nov 13, 2020 -------------------
	 * 
	 * @author macbook
	 *
	 */
	@Override
	public UserDto create(UserDto userDto) {
		
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setPassword(passwordEncode(userDto.getPassword()));
		Set<Role> roles = new HashSet<>();
		for (RoleDto roleDto : userDto.getRoles()) {
			roles.add(new Role(roleDto.getId(), roleDto.getName()));
		}
		user.setRoles(roles);
		if (ObjectUtils.isEmpty(user)) {
			String message = MessageUtils.getMessage(MessageEnum.MSGCODE3.getValue(), Constants.USER_STR);
			LOG.error(message);
			throw new NotFoundException(
					MessageUtils.getMessage(MessageEnum.MSGCODE4.getValue(), new String[] { "User", "Password" }));
		}
		
		setCreateInfo(user);
		userRepository.save(user);
		
		return userMapper.userToUserDto(user);
	}
	
	/**
	 * 
	 * <p>
	 * find All User
	 * </p>
	 * Nov 28, 2020 -------------------
	 * 
	 * @author macbook
	 *
	 */
	@Override
	public List<UserDto> getUsers() {
		
		List<UserDto> userDtos = new ArrayList<>();
		List<User> users = userRepository.getAllUsers();
		
		users.stream().forEach(user -> userDtos.add(userMapper.userToUserDto(user)));
		return userDtos;
	}
	
	/**
	 * 
	 * <p>
	 * password Encode
	 * </p>
	 * <p>
	 * Nov 28, 2020
	 * </p>
	 * -------------------
	 * 
	 * @author macbook
	 * @param password
	 * @return password encode
	 */
	private String passwordEncode(String password) {
		return passwordEncoder.encode(password);
	}
	
	@Override
	public UserDto edit(UserDto e) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean delete(Long id) {
		
		User user = this.userRepository.getOne(id);
		if (ObjectUtils.isEmpty(user)) {
			throw new NotFoundException("User not Found in database");
		}
		
		this.userRepository.delete(user);
		
		return true;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")//SecurityExpressionRoot
	@PostAuthorize("hasPermission(@privilegeServiceImpl.getPrivileges(),'USER_READ')")
	@Override
	public UserDto getById(Long id) {
		User user = this.userRepository.getOne(id);
		return userMapper.userToUserDto(user);
	}
	
}
