package sang.uaa.com.vn.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Document(collection = "category")
public class Category {

    @Id
    private String id;
    @Field(name = "name")
    private String name;
}
