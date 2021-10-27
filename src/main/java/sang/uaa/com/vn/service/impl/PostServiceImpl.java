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
import sang.uaa.com.vn.content.entites.Post;
import sang.uaa.com.vn.dto.PostDto;
import sang.uaa.com.vn.exception.BadRequestException;
import sang.uaa.com.vn.mapper.PostMapper;
import sang.uaa.com.vn.repository.CategroryRepository;
import sang.uaa.com.vn.repository.PostRepository;
import sang.uaa.com.vn.service.PostService;
import sang.uaa.com.vn.validation.PostValidator;
import sang.uaa.com.vn.validation.ValidatorService;

@Service
public class PostServiceImpl extends BaseService implements PostService {
	
	private static final String NAME = "name";
	private static final String ID = "id";
	
	public PostServiceImpl(CategroryRepository categroryRepository, PostRepository postRepository,
			PostMapper postMapper, PostValidator postValidator) {
		super();
		this.categroryRepository = categroryRepository;
		this.postRepository = postRepository;
		this.postMapper = postMapper;
		this.validator = postValidator;
	}
	
	private final CategroryRepository categroryRepository;
	private final PostRepository postRepository;
	private final PostMapper postMapper;
	private final ValidatorService validator;
	
	@Override
	public PostDto create(PostDto postDto) {
		
		Map<String, Object> map = new HashedMap<>();
		map.put(Constants.CREATE, postDto);
		validator.validate(Constants.CREATE, map);
		Optional<Categrory> optional = this.categroryRepository.findById(postDto.getCategroryDto().getId());
		if (!optional.isPresent()) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_NULL, new ErrorParam(ID)));
		}
		
		Post post = postMapper.toEntity(postDto);
		post.setCategrory(optional.get());
		postRepository.save(post);
		return postMapper.toDto(post);
	}
	
	@Override
	public PostDto edit(PostDto postDto) {
		
		Map<String, Object> map = new HashedMap<>();
		map.put(Constants.UPDATE, postDto);
		validator.validate(Constants.UPDATE, map);
		Optional<Categrory> optional = this.categroryRepository.findById(postDto.getCategroryDto().getId());
		if (!optional.isPresent()) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_NULL, new ErrorParam(ID)));
		}
		
		Optional<Post> optionalPost = this.postRepository.findById(postDto.getId());
		if (!optionalPost.isPresent()) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_NULL, new ErrorParam(ID)));
		}
		
		Post post = postMapper.toEntity(postDto);
		post.setCategrory(optional.get());
		postRepository.save(post);
		return postMapper.toDto(post);
	}
	
	@Override
	public boolean delete(Long id) {
		Optional<Post> optional = this.postRepository.findById(id);
		if (!optional.isPresent()) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_NULL, new ErrorParam(ID)));
		}
		this.postRepository.delete(optional.get());
		return true;
	}
	
	@Override
	public PostDto getById(Long id) {
		Optional<Post> optional = this.postRepository.findById(id);
		if (!optional.isPresent()) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_NULL, new ErrorParam(ID)));
		}
		Post paragraphs = this.postRepository.getPragraphs("test1");
		String[] a = this.postRepository.getPragraphs();
		return postMapper.toDto(optional.get());
	}
	
}
