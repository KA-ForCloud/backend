package ForCloud.backend.service;



import ForCloud.backend.dto.PostCategoryDto;
import ForCloud.backend.dto.PostDto;
import ForCloud.backend.dto.UserDto;
import ForCloud.backend.entity.*;
import ForCloud.backend.repository.*;
import ForCloud.backend.type.ParticipantType;
import ForCloud.backend.type.PostType;
import ForCloud.backend.type.ProjectType;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import java.io.*;
import java.nio.file.Files;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

@Service
public class DtoService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    private final PostCategoryRepository postCategoryRepository;

    private final UserCategoryRepository userCategoryRepository;

    private final ChattingRepository chattingRepository;
    private final ParticipantRepository participantRepository;


    @Autowired
    public DtoService(UserRepository userRepository, PostRepository postRepository, PostCategoryRepository postCategoryRepository, UserCategoryRepository userCategoryRepository, ChattingRepository chattingRepository, ParticipantRepository participantRepository) {

        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.postCategoryRepository = postCategoryRepository;
        this.userCategoryRepository = userCategoryRepository;
        this.chattingRepository = chattingRepository;
        this.participantRepository = participantRepository;
    }

    public Optional<User> getUserInfoByUserId(Long user_id){
        Optional<User> user = userRepository.findById(user_id);
        return user;
    }

    public Optional<UserCategory> getUserCategoryByUserId(Long user_id){
        try {
            Optional<UserCategory> userCategory = userCategoryRepository.findByUser_id(user_id);
            return userCategory;
        }
        catch (NoSuchElementException e ){
            throw new NoSuchElementException();
        }
    }

    public Optional<Post> getPostByPostId(Long post_id){
        try {
            Optional<Post> post = postRepository.findById(post_id);
            return post;
        }
        catch (NoSuchElementException e ){
            throw new NoSuchElementException();
        }
    }

    public User storeFile(File file, Long user_id) throws IOException {
        FileItem fileItem = new DiskFileItem("file", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length() , file.getParentFile());

        InputStream input = new FileInputStream(file);
        OutputStream os = fileItem.getOutputStream();
        IOUtils.copy(input,os);

        MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
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

    public Long savePost(PostDto postDto, Long user_id){
        try{
            Post post = new Post();


            if(postDto.getId() != null){
                post = postRepository.findById(postDto.getId()).get();
            }
            post.setByDto(postDto);
            User user = userRepository.findById(user_id).get();
            post.setUser(user);
            post.setPostType(PostType.recruiting);
            postRepository.save(post);

            PostCategory postCategory = new PostCategory();
            postCategory.setType("recruits");
            if (postDto.getPostCategoryDto().getId() != null){
                postCategory = postCategoryRepository.findById(postDto.getPostCategoryDto().getId()).get();
            }
            else{
                PostCategory postCategory2 = new PostCategory();
                postCategory2.setType("current");
                PostCategoryDto postCategoryDto = new PostCategoryDto(null, 0L,0L,0L,0L,0L,0L,null);
                postCategory2.setByDto(postCategoryDto,post);
                postCategoryRepository.save(postCategory2);
            }
            postCategory.setByDto(postDto.getPostCategoryDto(),post);
            postCategoryRepository.save(postCategory);

            Chatting chatting=new Chatting();
            chatting.setPostId(post.getId());
            chatting.setTitle(post.getPost_name());
            chatting.setProjectType(ProjectType.onGoing);
            chatting.setFilePath("filePath");
            chattingRepository.save(chatting);

            Participant participant=new Participant();
            participant.setChatting(chatting);
            participant.setUser(user);
            participant.setType(ParticipantType.팀장);
            participant.setPost(post);
            participant.setLast(0L);
            participantRepository.save(participant);

            return chatting.getId();
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
