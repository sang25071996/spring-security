package sang.uaa.com.vn.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sang.uaa.com.vn.common.dto.ErrorParam;
import sang.uaa.com.vn.common.dto.SysError;
import sang.uaa.com.vn.common.service.BaseService;
import sang.uaa.com.vn.constant.Constants;
import sang.uaa.com.vn.dto.RoleDto;
import sang.uaa.com.vn.entites.Role;
import sang.uaa.com.vn.exception.NotFoundException;
import sang.uaa.com.vn.repository.RoleRepository;
import sang.uaa.com.vn.service.RoleService;

@Service
public class RoleServiceImpl extends BaseService implements RoleService {
	
	private static final Logger LOG = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public RoleDto save(RoleDto roleDto) {
		Role role = new Role();
		role.setName(convertRole(roleDto.getName()));
		setCreateInfo(role);
		role = roleRepository.save(role);
		return dozerBeanMapper.map(role, RoleDto.class);
	}
	
	@Override
	public RoleDto getRoleById(Long id) {
		
		Role role = roleRepository.findByRoleId(id);
		if (ObjectUtils.isEmpty(role)) {
			LOG.error("Role {} is Empty", role);
			throw new NotFoundException(new SysError("ID NOT FOUND", new ErrorParam("id")));
		}
		return dozerBeanMapper.map(role, RoleDto.class);
		
	}
	
	@Override
	public RoleDto edit(RoleDto roleDto) {
		Role role = new Role();
		if (ObjectUtils.isEmpty(roleDto.getId())) {
			throw new NotFoundException(new SysError("ID NOT FOUND", new ErrorParam("id")));
		} else {
//			role = roleRepository.findByRoleId(roleDto.getId());
			role.setName(convertRole(roleDto.getName()));
			setUpdateInfo(role);
			if (!ObjectUtils.isEmpty(role)) {
				roleRepository.save(role);
			} else {
				throw new NotFoundException(new SysError("ROLE NOT FOUND", new ErrorParam("id")));
			}
		}
		return dozerBeanMapper.map(role, RoleDto.class);
	}
	
	@Override
	public List<RoleDto> findAll() {
		List<RoleDto> roleDtos = new ArrayList<>();
		List<Role> roles = roleRepository.findAll();
		roles.forEach(role -> roleDtos.add(dozerBeanMapper.map(role, RoleDto.class)));
		return roleDtos;
	}
	
	private String convertRole(String role) {
		return String.join("_", Constants.ROLE, role.toUpperCase());
	}
}
