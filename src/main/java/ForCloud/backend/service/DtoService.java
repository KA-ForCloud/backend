package ForCloud.backend.service;



import ForCloud.backend.dto.PostDto;
import ForCloud.backend.dto.UserDto;
import ForCloud.backend.entity.Post;
import ForCloud.backend.entity.PostCategory;
import ForCloud.backend.entity.User;
import ForCloud.backend.entity.UserCategory;
import ForCloud.backend.repository.PostCategoryRepository;
import ForCloud.backend.repository.PostRepository;
import ForCloud.backend.repository.UserCategoryRepository;
import ForCloud.backend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class DtoService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    private final PostCategoryRepository postCategoryRepository;

    private final UserCategoryRepository userCategoryRepository;


    @Autowired
    public DtoService(UserRepository userRepository, PostRepository postRepository, PostCategoryRepository postCategoryRepository, UserCategoryRepository userCategoryRepository) {

        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.postCategoryRepository = postCategoryRepository;
        this.userCategoryRepository = userCategoryRepository;
    }

    public Optional<User> getUserInfoByUserId(Long user_id){
        Optional<User> user = userRepository.findById(user_id);
        return user;
    }

    public User storeFile(MultipartFile multipartFile, Long user_id) throws IOException {
        System.out.println(multipartFile);

        String fileId = (new Date().getTime()) + "" + (new Random().ints(1000, 9999).findAny().getAsInt()); // 현재 날짜와 랜덤 정수값으로 새로운 파일명 만들기
        String originName = multipartFile.getOriginalFilename(); // ex) 파일.jpg
        String fileExtension = originName.substring(originName.lastIndexOf(".") + 1); // ex) jpg
        originName = originName.substring(0, originName.lastIndexOf(".")); // ex) 파일
        long fileSize = multipartFile.getSize(); // 파일 사이즈

        File fileSave = new File("/Users/bagchanbin/upload", fileId + "." + fileExtension); // ex) fileId.jpg
        if(!fileSave.exists()) { // 폴더가 없을 경우 폴더 만들기
            fileSave.mkdirs();
        }

        multipartFile.transferTo(fileSave); // fileSave의 형태로 파일 저장


        User user = userRepository.findById(user_id).get();
        user.setPortsave_name(fileId+'.'+fileExtension);
        userRepository.save(user);
        System.out.println("fileId= " + fileId);
        System.out.println("originName= " + originName);
        System.out.println("fileExtension= " + fileExtension);
        System.out.println("fileSize= " + fileSize);
        System.out.println("givename"+user.getPortsave_name());
        return user;
    }

    public void savePost(PostDto postDto, Long user_id){
        try{
            Post post = new Post();
            post.setByDto(postDto);
            User user = userRepository.findById(user_id).get();
            post.setUser(user);
            postRepository.save(post);

            PostCategory postCategory = new PostCategory();

            postCategory.setByDto(postDto.getPostCategoryDto(),post);
            postCategoryRepository.save(postCategory);

//            ChatDto chatDto = new ChatDto(user_id, post.getPost_id());
//            Chat chat = new Chat();
//            chat.setByDto(chatDto);
//            Create_Chatting(user_id);
        }
        catch (Exception e){
            throw new IllegalStateException();
        }
    }

    public User updatePort(Long user_id, UserDto userDto, String portname){

            User user = userRepository.findById(user_id).get();
            user.setSchool(userDto.getSchool());
            UserCategory userCategory = new UserCategory();
            if(userCategoryRepository.findByUser_id(user_id).isPresent()) {
                userCategory = userCategoryRepository.findByUser_id(user_id).get();
            }

            userCategory.setByDto(userDto.getUserCategoryDto());
            System.out.println("dddd"+userCategory);
            userCategoryRepository.save(userCategory);

            String tmptech = "";

            if (userCategory.getJavascript() == 1){
                tmptech += "JavaScript, ";
            }
            if (userCategory.getSpringboot() == 1){
                tmptech += "Springboot, ";
            }
            if (userCategory.getReact() == 1){
                tmptech += "React, ";
            }
            if (userCategory.getSpring()== 1){
                tmptech += "Spring, ";
            }
            if (userCategory.getJava()== 1){
                tmptech += "Java, ";
            }
            if (userCategory.getPython() == 1){
                tmptech += "Python, ";
            }

            if(tmptech.length() > 3){
                tmptech = tmptech.substring(0,tmptech.length()-2);
            }

            user.setTech(tmptech);
            user.setPort(portname);

            System.out.println("check");
            userRepository.save(user);
            return user;

    }

    public User saveUser(User user) {
        String email = user.getUser_email();
        this.userRepository.findByUser_email(email)
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
        return userRepository.save(user);
    }


    public String Create_Chatting(Long user_id){
        String json = "{\"user_id\":\""+user_id+"\","+"\"}";
        System.out.println("JSON: "+ json);
        try{
            HttpClient client = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost("http://localhost:8083/chatting");
            postRequest.setEntity(new StringEntity(json,"UTF-8"));
            HttpResponse response = client.execute(postRequest);
            if (response.getStatusLine().getStatusCode() == 204) {
                return "Sucess";
            }
            else{
                return "Fail";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
