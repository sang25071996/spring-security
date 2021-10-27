package sang.uaa.com.vn.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sang.uaa.com.vn.common.dto.ErrorParam;
import sang.uaa.com.vn.common.dto.SysError;
import sang.uaa.com.vn.common.service.BaseService;
import sang.uaa.com.vn.constant.Constants;
import sang.uaa.com.vn.dto.ProductCategroriesDto;
import sang.uaa.com.vn.exception.BadRequestException;
import sang.uaa.com.vn.mapper.ProductCategroriesMapper;
import sang.uaa.com.vn.product.entites.ProductCategrories;
import sang.uaa.com.vn.repository.ProductCategroriesRepository;
import sang.uaa.com.vn.service.ProductCategroriesService;

@Service
@RequiredArgsConstructor
public class ProductCategroryServiceImpl extends BaseService implements ProductCategroriesService {
	
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String PRODUCT_CATEGRORIES_DTO = "ProductCategroriesDto";
	
	private final ProductCategroriesRepository productCategroriesRepository;
	private final ProductCategroriesMapper productCategroriesMapper;
	
	@Override
	public ProductCategroriesDto create(ProductCategroriesDto categroriesDto) {
		
		ProductCategrories productCategrories = productCategroriesMapper.toEntity(categroriesDto);
		productCategroriesRepository.save(productCategrories);
		return productCategroriesMapper.toDto(productCategrories);
	}
	
	@Override
	public ProductCategroriesDto edit(ProductCategroriesDto categroriesDto) {
		
		Optional<ProductCategrories> optional = this.productCategroriesRepository.findById(categroriesDto.getId());
		if (!optional.isPresent()) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_NULL, new ErrorParam(ID)));
		}
		
		ProductCategrories productCategrories = productCategroriesMapper.toEntity(categroriesDto);
		productCategroriesRepository.save(productCategrories);
		return productCategroriesMapper.toDto(productCategrories);
	}
	
	@Override
	public boolean delete(Long id) {
		Optional<ProductCategrories> optional = this.productCategroriesRepository.findById(id);
		if (!optional.isPresent()) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_NULL, new ErrorParam(ID)));
		}
		this.productCategroriesRepository.delete(optional.get());
		return true;
	}
	
	@Override
	public ProductCategroriesDto getById(Long id) {
		Optional<ProductCategrories> optional = this.productCategroriesRepository.findById(id);
		if (!optional.isPresent()) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_NULL, new ErrorParam(ID)));
		}
		return this.productCategroriesMapper.toDto(optional.get());
	}
	
}
