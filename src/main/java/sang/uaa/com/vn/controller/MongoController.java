package sang.uaa.com.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sang.uaa.com.vn.common.controller.BaseController;
import sang.uaa.com.vn.common.dto.ResponJson;
import sang.uaa.com.vn.mongodb.Category;
import sang.uaa.com.vn.mongodb.service.CategroyService;

import java.util.List;

@RestController
@RequestMapping("/mongos")
public class MongoController extends BaseController {

    @Autowired
    private CategroyService categroyService;

    @PostMapping()
    public ResponseEntity<ResponJson<Category>> create(@RequestBody Category category) {
        return getResponseEntity(this.categroyService.create(category));
    }

    @GetMapping()
    public ResponseEntity<ResponJson<List<Category>>> findCategorys() {
        return getResponseEntity(this.categroyService.findCategorys());
    }

    @GetMapping("name")
    public ResponseEntity<ResponJson<List<Category>>> findCategorysByName(@RequestParam("name") String name) {
        return getResponseEntity(this.categroyService.findCategorysLikeName(name));
    }
}
