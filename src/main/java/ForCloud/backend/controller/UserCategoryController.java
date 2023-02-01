package ForCloud.backend.controller;

import ForCloud.backend.entity.User;
import ForCloud.backend.entity.UserCategory;
import ForCloud.backend.service.DtoService;
import ForCloud.backend.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@ResponseBody
@RequiredArgsConstructor
public class UserCategoryController {
    @Autowired
    DtoService dtoService;


    @GetMapping("/userCategory/{user_id}")
    public UserCategory getUserCategoryByUserId(@PathVariable Long user_id){
        return dtoService.getUserCategoryByUserId(user_id).get();
    }
}
