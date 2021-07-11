package sang.uaa.com.vn.service.impl;

import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sang.uaa.com.vn.common.dto.ErrorParam;
import sang.uaa.com.vn.common.dto.SysError;
import sang.uaa.com.vn.common.service.BaseService;
import sang.uaa.com.vn.constant.Constants;
import sang.uaa.com.vn.dto.ProductDto;
import sang.uaa.com.vn.exception.BadRequestException;
import sang.uaa.com.vn.mapper.ProductMapper;
import sang.uaa.com.vn.product.entites.Product;
import sang.uaa.com.vn.product.entites.ProductCategrories;
import sang.uaa.com.vn.repository.ProductCategroriesRepository;
import sang.uaa.com.vn.repository.ProductRepository;
import sang.uaa.com.vn.service.ProductService;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl extends BaseService implements ProductService {
	
	private static final String NAME = "name";
	private static final String ID = "id";
	
	private final ProductCategroriesRepository productCategroriesRepository;
	private final ProductRepository productRepository;
	private final ProductMapper productMapper;
	
	@Override
	public ProductDto create(ProductDto productDto) {
		
		Map<Object, String> map = new HashedMap<>();
		map.put(productDto, ID);
		map.put(productDto.getName(), NAME);
		map.put(productDto.getCategrories().getId(), ID);
		validatorObjectIsEmpty(map);
		Optional<ProductCategrories> optional = this.productCategroriesRepository.findById(productDto.getCategrories().getId());
		if (!optional.isPresent()) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_NULL, new ErrorParam(ID)));
		}
		
		Product product = productMapper.toEntity(productDto);
		product.setCategrories(optional.get());
		productRepository.save(product);
		return productMapper.toDto(product);
	}
	
	@Override
	public ProductDto edit(ProductDto productDto) {
		
		Map<Object, String> map = new HashedMap<>();
		map.put(productDto, ID);
		map.put(productDto.getName(), NAME);
		map.put(productDto.getCategrories().getId(), ID);
		validatorObjectIsEmpty(map);
		Optional<ProductCategrories> optional = this.productCategroriesRepository.findById(productDto.getCategrories().getId());
		if (!optional.isPresent()) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_NULL, new ErrorParam(ID)));
		}
		
		Optional<Product> optionalProduct = this.productRepository.findById(productDto.getId());
		if (!optionalProduct.isPresent()) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_NULL, new ErrorParam(ID)));
		}
		
		Product product = productMapper.toEntity(productDto);
		product.setCategrories(optional.get());
		productRepository.save(product);
		return productMapper.toDto(product);
	}
	
	@Override
	public boolean delete(Long id) {
		Optional<Product> optional = this.productRepository.findById(id);
		if (!optional.isPresent()) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_NULL, new ErrorParam(ID)));
		}
		this.productRepository.delete(optional.get());
		return true;
	}
	
	@Override
	public ProductDto getById(Long id) {
		Optional<Product> optional = this.productRepository.findById(id);
		if (!optional.isPresent()) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_NULL, new ErrorParam(ID)));
		}
		String test = this.productRepository.getProperties("hp");
		return productMapper.toDto(optional.get());
	}
	
}
