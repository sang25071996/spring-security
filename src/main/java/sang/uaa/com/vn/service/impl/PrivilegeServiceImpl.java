package sang.uaa.com.vn.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sang.uaa.com.vn.common.dto.ErrorParam;
import sang.uaa.com.vn.common.dto.SysError;
import sang.uaa.com.vn.common.service.BaseService;
import sang.uaa.com.vn.constant.Constants;
import sang.uaa.com.vn.dto.PrivilegeDto;
import sang.uaa.com.vn.entites.Privilege;
import sang.uaa.com.vn.exception.BadRequestException;
import sang.uaa.com.vn.mapper.PrivilegeMapper;
import sang.uaa.com.vn.repository.PrivilegeRepository;
import sang.uaa.com.vn.service.PrivilegeService;

@Service
public class PrivilegeServiceImpl extends BaseService implements PrivilegeService {

	@Autowired
	private PrivilegeRepository privilegeRepository;
	@Autowired
	private PrivilegeMapper privilegeMapper;
	
	@Transactional
	@Override
	public PrivilegeDto create(PrivilegeDto privilegeDto) {
		
		if (ObjectUtils.isEmpty(privilegeDto)) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_EMPTY, new ErrorParam("PrivilegeDto")));
		}
		
		if (StringUtils.isBlank(privilegeDto.getName())) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_EMPTY, new ErrorParam("name")));
		}
		Privilege privilege = new Privilege();
		privilege.setName(privilegeDto.getName());
		setCreateInfo(privilege);
		
		privilegeRepository.save(privilege);
		return this.privilegeMapper.toDto(privilege);
	}

	@Transactional
	@Override
	public PrivilegeDto edit(PrivilegeDto privilegeDto) {

		if (ObjectUtils.isEmpty(privilegeDto)) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_EMPTY, new ErrorParam("PrivilegeDto")));
		}
		
		if (StringUtils.isBlank(privilegeDto.getName())) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_EMPTY, new ErrorParam("name")));
		}
		Optional<Privilege> optionalPrivilege = this.privilegeRepository.findById(privilegeDto.getId());
		if (!optionalPrivilege.isPresent()) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_NULL, new ErrorParam("Privilege")));
		}
		
		Privilege privilege = optionalPrivilege.get();
		privilege.setName(privilegeDto.getName());
		setCreateInfo(privilege);
		
		privilegeRepository.save(privilege);
		return this.privilegeMapper.toDto(privilege);
	}

	@Transactional
	@Override
	public boolean delete(Long id) {

		Optional<Privilege> optionalPrivilege = this.privilegeRepository.findById(id);
		if (!optionalPrivilege.isPresent()) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_NULL, new ErrorParam("Privilege")));
		}
		
		this.privilegeRepository.delete(optionalPrivilege.get());
		return true;
	}

	@Override
	public PrivilegeDto getById(Long id) {
		
		Optional<Privilege> optionalPrivilege = this.privilegeRepository.findById(id);
		if (!optionalPrivilege.isPresent()) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_NULL, new ErrorParam("Privilege")));
		}
		
		return this.privilegeMapper.toDto(optionalPrivilege.get());
	}
	
}
