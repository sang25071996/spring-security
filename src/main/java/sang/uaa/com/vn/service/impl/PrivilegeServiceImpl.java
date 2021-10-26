package sang.uaa.com.vn.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import sang.uaa.com.vn.common.dto.ErrorParam;
import sang.uaa.com.vn.common.dto.SysError;
import sang.uaa.com.vn.common.service.BaseService;
import sang.uaa.com.vn.constant.Constants;
import sang.uaa.com.vn.dto.PrivilegeDto;
import sang.uaa.com.vn.exception.BadRequestException;
import sang.uaa.com.vn.mapper.PrivilegeMapper;
import sang.uaa.com.vn.repository.PrivilegeRepository;
import sang.uaa.com.vn.service.PrivilegeService;
import sang.uaa.com.vn.user.entites.Privilege;
import sang.uaa.com.vn.validation.PrivilegeValidator;
import sang.uaa.com.vn.validation.ValidatorService;

@Service
public class PrivilegeServiceImpl extends BaseService implements PrivilegeService {

	private static final String PRIVILEGE = "Privilege";
	
	private final PrivilegeRepository privilegeRepository;
	private final PrivilegeMapper privilegeMapper;
	private final ValidatorService validator;
	
	public PrivilegeServiceImpl(PrivilegeRepository privilegeRepository, PrivilegeMapper privilegeMapper) {
		this.privilegeRepository = privilegeRepository;
		this.privilegeMapper = privilegeMapper;
		this.validator = new PrivilegeValidator();
	}
	
	@Transactional
	@Override
	public PrivilegeDto create(PrivilegeDto privilegeDto) {
		
		Map<String, Object> map = new HashMap<>();
		map.put(Constants.CREATE, privilegeDto);
		validator.validate(Constants.CREATE, map);
		
		Privilege privilege = new Privilege();
		privilege.setName(privilegeDto.getName());
		setCreateInfo(privilege);
		
		privilegeRepository.save(privilege);
		return this.privilegeMapper.toDto(privilege);
	}

	@Transactional
	@Override
	public PrivilegeDto edit(PrivilegeDto privilegeDto) {

		Map<String, Object> map = new HashMap<>();
		map.put(Constants.UPDATE, privilegeDto);
		validator.validate(Constants.UPDATE, map);
		Optional<Privilege> optionalPrivilege = this.privilegeRepository.findById(privilegeDto.getId());
		if (!optionalPrivilege.isPresent()) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_NULL, new ErrorParam(PRIVILEGE)));
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
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_NULL, new ErrorParam(PRIVILEGE)));
		}
		
		this.privilegeRepository.delete(optionalPrivilege.get());
		return true;
	}

	@Override
	public PrivilegeDto getById(Long id) {
		
		Optional<Privilege> optionalPrivilege = this.privilegeRepository.findById(id);
		if (!optionalPrivilege.isPresent()) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_NULL, new ErrorParam(PRIVILEGE)));
		}
		
		return this.privilegeMapper.toDto(optionalPrivilege.get());
	}
	
	public List<Privilege> getPrivileges() {
		return this.privilegeRepository.findAll();
	}
	
}
