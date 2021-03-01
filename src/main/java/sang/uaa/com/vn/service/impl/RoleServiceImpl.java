package sang.uaa.com.vn.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sang.uaa.com.vn.common.MessageEnum;
import sang.uaa.com.vn.common.dto.ErrorParam;
import sang.uaa.com.vn.common.dto.SysError;
import sang.uaa.com.vn.common.service.BaseService;
import sang.uaa.com.vn.constant.Constants;
import sang.uaa.com.vn.dto.RoleDto;
import sang.uaa.com.vn.entites.Role;
import sang.uaa.com.vn.exception.NotFoundException;
import sang.uaa.com.vn.mapper.RoleMapper;
import sang.uaa.com.vn.repository.RoleRepository;
import sang.uaa.com.vn.service.RoleService;
import sang.uaa.com.vn.utils.MessageUtils;

@Service
public class RoleServiceImpl extends BaseService implements RoleService {
	
	private static final Logger LOG = LoggerFactory.getLogger(RoleServiceImpl.class);
	@Autowired
	private RoleRepository roleRepository;
	private RoleMapper roleMapper;
	
	public RoleServiceImpl() {
		this.roleMapper = getInstanceMappger(RoleMapper.class);
	}
	
	@Override
	public RoleDto create(RoleDto roleDto) {
		Role role = new Role();
		role.setName(convertRole(roleDto.getName()));
		setCreateInfo(role);
		role = roleRepository.save(role);
		return this.roleMapper.roleToRoleDto(role);
	}
	
	/**
	 * 
	 * <p>get By Id</p>
	 * <p>Mar 1, 2021</p>
	 *-------------------
	 * @author macbook
	 * @param id Long
	 * @return RoleDto
	 */
	@Override
	public RoleDto getById(Long id) {
		
		Role role = roleRepository.findByRoleId(id);
		if (ObjectUtils.isEmpty(role)) {
			String message = MessageUtils.getMessage(MessageEnum.MSGCODE3.getValue(), Constants.ROLE_STR);
			LOG.error(message);
			String idMessage = MessageUtils.getMessage(MessageEnum.MSGCODE2.getValue(), Constants.ID_STR);
			throw new NotFoundException(new SysError(idMessage, new ErrorParam(Constants.ID_STR)));
		}
		return this.roleMapper.roleToRoleDto(role);
		
	}
	
	/**
	 * 
	 * <p>edit</p>
	 * <p>Mar 1, 2021</p>
	 *-------------------
	 * @author macbook
	 * @param roleDto RoleDto
	 * @return RoleDto
	 */
	@Override
	public RoleDto edit(RoleDto roleDto) {
		
		Role role;
		if (ObjectUtils.isEmpty(roleDto.getId())) {
			String message = MessageUtils.getMessage(MessageEnum.MSGCODE2.getValue(), roleDto.getId().toString());
			throw new NotFoundException(new SysError(message, new ErrorParam(Constants.ID_STR)));
		}
		
		role = roleRepository.findByRoleId(roleDto.getId());
		if (ObjectUtils.isEmpty(role)) {
			String message = MessageUtils.getMessage(MessageEnum.MSGCODE2.getValue(), Constants.ROLE_STR);
			throw new NotFoundException(new SysError(message, new ErrorParam(Constants.ID_STR)));
			
		}
		
		role.setName(convertRole(roleDto.getName()));
		setUpdateInfo(role);
		
		roleRepository.save(role);
		
		return this.roleMapper.roleToRoleDto(role);
	}
	
	/**
	 * 
	 * <p>get Roles</p>
	 * <p>Mar 1, 2021</p>
	 *-------------------
	 * @author macbook
	 *
	 */
	@Override
	public List<RoleDto> getRoles() {
		List<RoleDto> roleDtos = new ArrayList<>();
		List<Role> roles = roleRepository.findAll();
		roles.forEach(role -> roleDtos.add(this.roleMapper.roleToRoleDto(role)));
		return roleDtos;
	}
	
	/**
	 * 
	 * <p>delete</p>
	 * <p>Mar 1, 2021</p>
	 *-------------------
	 * @author macbook
	 * @param id Long
	 */
	@Override
	public boolean delete(Long id) {
		
		Role role = this.roleRepository.findByRoleId(id);
		if (ObjectUtils.isEmpty(role)) {
			throw new NotFoundException("Role not found in database");
		}
		
		this.roleRepository.delete(role);
		
		return true;
	}
	
	private String convertRole(String role) {
		return String.join(Constants.UNDERLINE, Constants.ROLE, role.toUpperCase());
	}
}
