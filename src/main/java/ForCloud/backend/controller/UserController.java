package ForCloud.backend.controller;

import ForCloud.backend.dto.UserDto;
import ForCloud.backend.entity.User;
import ForCloud.backend.service.DtoService;
import ForCloud.backend.service.KakaoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@ResponseBody
@RequiredArgsConstructor
public class UserController {

    @Autowired
    DtoService dtoService;

    @Autowired
    KakaoService kakaoService;


    @PostMapping("/api/user/test/{token}")
    public void user(@PathVariable String token){
        System.out.println(token);
        kakaoService.getInfoByKakaoToken(token);
    }

    @PostMapping("/api/user/register/{token}")
    public User registerUser(@PathVariable String token) {

        return kakaoService.getInfoByKakaoToken(token);
    }


    @PostMapping("/api/user/port/save/{user_id}")
    public User savePort(@PathVariable Long user_id, @RequestBody String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(json);
        UserDto userDto = new UserDto();
        userDto = mapper.readValue(json, UserDto.class);
        
        return dtoService.updatePort(user_id, userDto, userDto.getPort_name());
    }


    @PostMapping("/api/user/upload/{user_id}")
    public User uploadFile(MultipartFile multipartFile, @PathVariable Long user_id) throws IOException {
        multipartFile.isEmpty();/* 파일을 업로드 하지 않았을 경우 처리 */
        return dtoService.storeFile(multipartFile,user_id);
    }

    @GetMapping("/api/user/info/{user_id}")
    public User getUserInfoByUserId(@PathVariable Long user_id){
        return dtoService.getUserInfoByUserId(user_id).get();
    }

    @GetMapping("/api/user/attached/{filename}")
    public void download(HttpServletResponse response,@PathVariable String filename) throws Exception {
        try {
            String path = "/Users/bagchanbin/upload/"+filename; // 경로에 접근할 때 역슬래시('\') 사용
            System.out.println(path);
            File file = new File(path);
            response.setHeader("Content-Disposition", "attachment;filename=" + file.getName()); // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더
            System.out.println("check");
            FileInputStream fileInputStream = new FileInputStream(path); // 파일 읽어오기
            System.out.println(fileInputStream);
            OutputStream out = response.getOutputStream();
            System.out.println("check");
            int read = 0;
            byte[] buffer = new byte[1024];
            while ((read = fileInputStream.read(buffer)) != -1) { // 1024바이트씩 계속 읽으면서 outputStream에 저장, -1이 나오면 더이상 읽을 파일이 없음
                out.write(buffer, 0, read);
            }

        } catch (Exception e) {
            throw new Exception("download error");
        }
    }






//    public ResponseEntity<String> upload(MultipartFile file) throws IllegalStateException, IOException{
//        storageService.store(file);
//        return new ResponseEntity<>("", HttpStatus.OK);
//    }


    @ResponseBody
    @GetMapping("/api/user/{user}")
    public Optional<User> getUserInfoByUserID(@PathVariable Long userId){
        return dtoService.getUserInfoByUserId(userId);
    }



}

