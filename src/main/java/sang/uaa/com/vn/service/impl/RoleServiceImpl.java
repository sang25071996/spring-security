package sang.uaa.com.vn.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import sang.uaa.com.vn.common.MessageEnum;
import sang.uaa.com.vn.common.dto.ErrorParam;
import sang.uaa.com.vn.common.dto.RequestPagingBuilder;
import sang.uaa.com.vn.common.dto.SysError;
import sang.uaa.com.vn.common.service.BaseService;
import sang.uaa.com.vn.constant.Constants;
import sang.uaa.com.vn.dto.PrivilegeDto;
import sang.uaa.com.vn.dto.RoleDto;
import sang.uaa.com.vn.dto.SystemPermission;
import sang.uaa.com.vn.exception.BadRequestException;
import sang.uaa.com.vn.exception.NotFoundException;
import sang.uaa.com.vn.mapper.PrivilegeMapper;
import sang.uaa.com.vn.mapper.RoleMapper;
import sang.uaa.com.vn.repository.PrivilegeRepository;
import sang.uaa.com.vn.repository.RoleRepository;
import sang.uaa.com.vn.service.RoleService;
import sang.uaa.com.vn.user.entites.Privilege;
import sang.uaa.com.vn.user.entites.Role;
import sang.uaa.com.vn.utils.MessageUtils;

@Service
public class RoleServiceImpl extends BaseService implements RoleService {
	
	private static final Logger LOG = LoggerFactory.getLogger(RoleServiceImpl.class);
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private PrivilegeMapper privilegeMapper;
	@Autowired
	private PrivilegeRepository privilegeRepository;
	
	public RoleServiceImpl() {
//		this.roleMapper = getInstanceMappger(RoleMapper.class);
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
	 * <p>
	 * get By Id
	 * </p>
	 * <p>
	 * Mar 1, 2021
	 * </p>
	 * -------------------
	 * 
	 * @author macbook
	 * @param id Long
	 * @return RoleDto
	 */
	
	@Cacheable(key = "#id", value = "RoleDto")
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
	 * <p>
	 * edit
	 * </p>
	 * <p>
	 * Mar 1, 2021
	 * </p>
	 * -------------------
	 * 
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
	 * <p>
	 * get Roles
	 * </p>
	 * <p>
	 * Mar 1, 2021
	 * </p>
	 * -------------------
	 * 
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
	 * <p>
	 * delete
	 * </p>
	 * <p>
	 * Mar 1, 2021
	 * </p>
	 * -------------------
	 * 
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
	
	@Override
	public Page<RoleDto> filterPaging(RequestPagingBuilder<RoleDto> requestPagingBuilder) {
		
		String name = "";
		String[] fields = { "id" };
		
		name = defaultIfNotBlank(requestPagingBuilder.getFilters().getName(), name);
		
		if (ArrayUtils.isEmpty(requestPagingBuilder.getFieldsOrderBy())) {
			fields = requestPagingBuilder.getFieldsOrderBy();
		}
		
		Sort.Direction sortDirection;
		sortDirection = Direction.ASC;
		if (StringUtils.isNotEmpty(requestPagingBuilder.getSortBy().name())) {
			sortDirection = requestPagingBuilder.getSortBy();
		}
		
		Pageable pageable = PageRequest.of(requestPagingBuilder.getPage(), requestPagingBuilder.getSize(),
				Sort.by(sortDirection, fields));
		
		Page<Role> page = this.roleRepository.filterPaging(name, pageable);
		
		return page.map(content -> this.roleMapper.roleToRoleDto(content));
	}
	
	@Transactional
	public SystemPermission setManagementPermission(SystemPermission systemPermission) {
		
		Optional<Role> optionalRole = this.roleRepository.findById(systemPermission.getRoleDto().getId());
		if (!optionalRole.isPresent()) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_NULL, new ErrorParam("Role")));
		}
		List<Privilege> privileges = new ArrayList<>();
		for (PrivilegeDto privilegeDto : systemPermission.getPrivilegeDtos()) {
			Optional<Privilege> optionalPrivilege = this.privilegeRepository.findById(privilegeDto.getId());
			if (!optionalPrivilege.isPresent()) {
				throw new BadRequestException(new SysError(Constants.ERROR_DATA_NULL, new ErrorParam("Privilege")));
			}
			privileges.add(optionalPrivilege.get());
		}
		
		Role role = optionalRole.get();
		Set<Privilege> set = new HashSet<>(privileges);
		role.setPrivileges(set);
		
		this.roleRepository.save(role);
		SystemPermission sysPermission = new SystemPermission();
		sysPermission.setPrivilegeDtos(this.privilegeMapper.toDto(role.getPrivileges()));
		sysPermission.setRoleDto(this.roleMapper.roleToRoleDto(role));
		return sysPermission;
	}
	
	public List<SystemPermission> getManagementPermission() {
		
		List<Role> roles = this.roleRepository.getPrivileges();
		SystemPermission systemPermission;
		List<SystemPermission> systemPermissions = new ArrayList<>();
		for (Role role : roles) {
			
			systemPermission = new SystemPermission();
			systemPermission.setRoleDto(this.roleMapper.roleToRoleDto(role));
			systemPermission.setPrivilegeDtos(this.privilegeMapper.toDto(role.getPrivileges()));
			systemPermissions.add(systemPermission);
		}
		return systemPermissions;
	}
}
