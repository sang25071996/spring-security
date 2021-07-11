package sang.uaa.com.vn.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
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

@Service
@RequiredArgsConstructor
public class CategroryServiceImpl extends BaseService implements CategroryService {

	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String PRODUCT_CATEGRORIES_DTO = "ProductCategroriesDto";

	private final CategroryRepository categroryRepository;
	private final CategroryMapper categroryMapper;
	
	@Override
	public CategroryDto create(CategroryDto categroryDto) {

		validatorObjectIsEmpty(categroryDto, PRODUCT_CATEGRORIES_DTO);
		validatorFieldIsBlank(categroryDto.getName(), NAME);
		Categrory categrory = categroryMapper.toEntity(categroryDto);
		categroryRepository.save(categrory);
		return categroryMapper.toDto(categrory);
	}

	@Override
	public CategroryDto edit(CategroryDto categroryDto) {
		validatorFieldIsBlank(categroryDto.getId(), ID);
		Optional<Categrory> optional = this.categroryRepository.findById(categroryDto.getId());
		if (!optional.isPresent()) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_NULL, new ErrorParam(ID)));
		}
		
		validatorObjectIsEmpty(categroryDto, PRODUCT_CATEGRORIES_DTO);
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
