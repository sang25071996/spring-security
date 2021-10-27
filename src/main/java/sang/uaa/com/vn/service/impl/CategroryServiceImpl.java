package sang.uaa.com.vn.service.impl;

import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.stereotype.Service;

import sang.uaa.com.vn.common.dto.ErrorParam;
import sang.uaa.com.vn.common.dto.SysError;
import sang.uaa.com.vn.common.service.BaseService;
import sang.uaa.com.vn.constant.Constants;
import sang.uaa.com.vn.content.entites.Categrory;
import sang.uaa.com.vn.dto.CategroryDto;
import sang.uaa.com.vn.exception.BadRequestException;
import sang.uaa.com.vn.mapper.CategroryMapper;
import sang.uaa.com.vn.repository.CategroryRepository;
import sang.uaa.com.vn.service.CategroryService;
import sang.uaa.com.vn.validation.CategoryValidator;
import sang.uaa.com.vn.validation.ValidatorService;

@Service
public class CategroryServiceImpl extends BaseService implements CategroryService {
	
	private static final String ID = "id";
	
	private final CategroryRepository categroryRepository;
	private final CategroryMapper categroryMapper;
	private final ValidatorService validator;
	
	public CategroryServiceImpl(CategroryRepository categroryRepository, CategroryMapper categroryMapper,
			CategoryValidator categoryValidator) {
		super();
		this.categroryRepository = categroryRepository;
		this.categroryMapper = categroryMapper;
		this.validator = categoryValidator;
	}
	
	@Override
	public CategroryDto create(CategroryDto categroryDto) {
		
		Map<String, Object> map = new HashedMap<>();
		map.put(Constants.CREATE, categroryDto);
		validator.validate(Constants.CREATE, map);
		Categrory categrory = categroryMapper.toEntity(categroryDto);
		categroryRepository.save(categrory);
		return categroryMapper.toDto(categrory);
	}
	
	@Override
	public CategroryDto edit(CategroryDto categroryDto) {
		Map<String, Object> map = new HashedMap<>();
		map.put(Constants.CREATE, categroryDto);
		validator.validate(Constants.CREATE, map);
		Optional<Categrory> optional = this.categroryRepository.findById(categroryDto.getId());
		if (!optional.isPresent()) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_NULL, new ErrorParam(ID)));
		}
		
		Categrory categrory = categroryMapper.toEntity(categroryDto);
		categroryRepository.save(categrory);
		return categroryMapper.toDto(categrory);
	}
	
	@Override
	public boolean delete(Long id) {
		Optional<Categrory> optional = this.categroryRepository.findById(id);
		if (!optional.isPresent()) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_NULL, new ErrorParam(ID)));
		}
		this.categroryRepository.delete(optional.get());
		return true;
	}
	
	@Override
	public CategroryDto getById(Long id) {
		Optional<Categrory> optional = this.categroryRepository.findById(id);
		if (!optional.isPresent()) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_NULL, new ErrorParam(ID)));
		}
		return this.categroryMapper.toDto(optional.get());
	}
	
}
