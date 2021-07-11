package sang.uaa.com.vn.mapper;

import org.mapstruct.Mapper;

import sang.uaa.com.vn.content.entites.Post;
import sang.uaa.com.vn.dto.PostDto;

@Mapper(componentModel = "spring", uses = CategroryMapper.class)
public interface PostMapper {
	
	PostDto toDto(Post entity);
	
	Post toEntity(PostDto dto);
}
