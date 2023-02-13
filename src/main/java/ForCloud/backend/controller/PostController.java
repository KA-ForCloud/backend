package ForCloud.backend.controller;

import ForCloud.backend.config.BaseResponse;
import ForCloud.backend.data.*;
import ForCloud.backend.dto.PostDto;
import ForCloud.backend.entity.Post;
import ForCloud.backend.entity.PostCategory;
import ForCloud.backend.service.DtoService;
import ForCloud.backend.service.PostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@ResponseBody
@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    @Autowired
    DtoService dtoService;

    @GetMapping("/")
    public BaseResponse<String> test(){
        return new BaseResponse<>("OK!");
    }
    @GetMapping("/api/post")
    public BaseResponse<List<PostResponse>> getAllPosts() {
        log.info("health check");
        List<PostResponse> postResponseList = postService.getAllPosts();
        return new BaseResponse<>(postResponseList);
    }

    @GetMapping("/api/post/{userId}")
    public BaseResponse<List<PostResponse>> getMyPost(@PathVariable(name="userId") Long userId){
        List<PostResponse> postList = postService.getMyPost(userId);

        return new BaseResponse<>(postList);
    }


    @GetMapping("/api/requestedPost/{userId}")
    public BaseResponse<List<PostResponse>> getRequestedPost(@PathVariable(name="userId") Long userId){
        List<PostResponse> postList = postService.getMyRequestedPost(userId);

        return new BaseResponse<>(postList);
    }

    @GetMapping("/api/project/{userId}")
    public BaseResponse<List<GetProjectListResponse>> getProject(@PathVariable(name="userId") Long userId){
        List<GetProjectListResponse> postList = postService.getMyProject(userId);

        return new BaseResponse<>(postList);
    }

    @GetMapping("/api/popularCategory")
    public BaseResponse<List<PopularCategoryResponse>> getPopularCategoryList () {
        List<PopularCategoryResponse> popularCategoryResponseList = postService.getPopularCategory();

        return new BaseResponse<>(popularCategoryResponseList);
    }

    @GetMapping("/api/applicant/{postId}")
    public BaseResponse<List<ApplicantResponse>> getApplicant(@PathVariable(name="postId")Long postId) {
        List<ApplicantResponse> applicantResponses = postService.getApplicant(postId);

        return new BaseResponse<>(applicantResponses);
    }

    @GetMapping("/api/currentParticipant/{postId}")
    public BaseResponse<PostCategoryResponse> getCurrentCategory (@PathVariable(name="postId")Long postId){
        PostCategoryResponse postCategoryResponse = postService.getCurrentCategory(postId);
        return new BaseResponse<>(postCategoryResponse);
    }

    @PostMapping("/api/registerApplicant")
    public BaseResponse<RequestApplicant> createApplicant(@RequestBody RequestApplicant requestApplicant){
        RequestApplicant returnApplicantPost = postService.registerApplicant(requestApplicant);
        return new BaseResponse<>(returnApplicantPost);
    }

    @PostMapping("/api/registerParticipant")
    public BaseResponse<PostParticipantResponse> createApplicant(@RequestBody RequestParticipant requestParticipant){
        PostParticipantResponse returnParticipant = postService.registerParticipant(requestParticipant);

        return new BaseResponse<>(returnParticipant);
    }

    @DeleteMapping("/api/applicant/{postId}/{userId}")
    public BaseResponse<DeleteApplicant> deleteApplicant(@PathVariable(name ="postId")Long postId, @PathVariable(name = "userId")Long userId){
        DeleteApplicant deleteApplicant = postService.deleteApplicant(postId, userId);
        return new BaseResponse<>(deleteApplicant);
    }

    @DeleteMapping("/api/allApplicant/{postId}/{category}")
    public BaseResponse<?> deleteAllApplicant(@PathVariable(name ="postId")Long postId, @PathVariable(name = "category")String category){
        String str = postService.deleteAllApplicant(postId, category);
        return new BaseResponse<>(str);
    }

    @DeleteMapping("/api/post/{postId}/{userId}")
    public BaseResponse<DeletePost> deletePost(@PathVariable(name="postId") Long postId, @PathVariable(name="userId") Long userId){
        DeletePost deletePost = postService.deletePost(postId, userId);

        return new BaseResponse<>(deletePost);
    }

    @PatchMapping("/api/post/{postId}")
    public BaseResponse<Post> addPostView(@PathVariable(name="postId")Long postId){
        Post post = postService.addView(postId);
        return new BaseResponse<>(post);
    }

    @PatchMapping("/api/postCategory/{postId}/{userId}")
    public BaseResponse<PostCategory> updateCategory(@PathVariable(name="postId")Long postId, @PathVariable(name="userId")Long userId){
        PostCategory post_category = postService.updateCurrentCategory(postId, userId);
        return new BaseResponse<>(post_category);
    }

    @PatchMapping("/api/postStatus/{postId}")
    public BaseResponse<Post> updatePostStatus(@PathVariable(name="postId")Long postId){
        Post post = postService.updatePost(postId);
        return new BaseResponse<>(post);
    }

    @PostMapping("/api/post/save/{user_id}")
    public BaseResponse<Long> savePost(@PathVariable Long user_id, @RequestBody PostDto postDto) throws JsonProcessingException {
//        System.out.println(user_id);
        log.info("save post");
        user_id=1L;
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(postDto);
        System.out.println(postDto);
        System.out.println(jsonInString);
        Long chattingId=dtoService.savePost(postDto, user_id);
        log.info("chattingId: {}",chattingId);
        return new BaseResponse<>(chattingId);

    }

    @GetMapping("/api/post/info/{postId}")
    public Post getPost(@PathVariable Long postId){
        return dtoService.getPostByPostId(postId).get();
    }
}
