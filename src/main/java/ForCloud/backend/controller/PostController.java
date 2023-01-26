package ForCloud.backend.controller;

import ForCloud.backend.config.BaseResponse;
import ForCloud.backend.data.*;
import ForCloud.backend.entity.Applicant;
import ForCloud.backend.entity.Post;
import ForCloud.backend.entity.Post_category;
import ForCloud.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post")
    public BaseResponse<List<PostResponse>> getAllPosts() {
        List<PostResponse> postResponseList = postService.getAllPosts();
        return new BaseResponse<>(postResponseList);
    }

    @GetMapping("/post/{userId}")
    public BaseResponse<List<PostResponse>> getMyPost(@PathVariable(name="userId") Long userId){
        List<PostResponse> postList = postService.getMyPost(userId);

        return new BaseResponse<>(postList);
    }

    @GetMapping("/requestedPost/{userId}")
    public BaseResponse<List<PostResponse>> getRequestedPost(@PathVariable(name="userId") Long userId){
        List<PostResponse> postList = postService.getMyRequestedPost(userId);

        return new BaseResponse<>(postList);
    }

    @GetMapping("/project/{userId}")
    public BaseResponse<List<GetProjectListResponse>> getProject(@PathVariable(name="userId") Long userId){
        List<GetProjectListResponse> postList = postService.getMyProject(userId);

        return new BaseResponse<>(postList);
    }


    @GetMapping("/temperature")
    public BaseResponse<List<MemberTemperature>> getTemperatureList () {
        List<MemberTemperature> memberTemperatureList = postService.getTemperature();

        return new BaseResponse<>(memberTemperatureList);
    }

    @GetMapping("/applicant/{postId}")
    public BaseResponse<List<ApplicantResponse>> getApplicant(@PathVariable(name="postId")Long postId) {
        List<ApplicantResponse> applicantResponses = postService.getApplicant(postId);

        return new BaseResponse<>(applicantResponses);
    }

    @PostMapping("/registerApplicant")
    public BaseResponse<RequestApplicant> createApplicant(@RequestBody RequestApplicant requestApplicant){
        RequestApplicant returnApplicantPost = postService.registerApplicant(requestApplicant);
        return new BaseResponse<>(returnApplicantPost);
    }

    @PostMapping("/registerParticipant")
    public BaseResponse<RequestParticipant> createApplicant(@RequestBody RequestParticipant requestParticipant){
        RequestParticipant returnParticipant = postService.registerParticipant(requestParticipant);
        return new BaseResponse<>(returnParticipant);
    }

    @DeleteMapping("/applicant/{postId}/{userName}")
    public BaseResponse<DeleteApplicant> deleteApplicant(@PathVariable(name ="postId")Long postId, @PathVariable(name = "userName")String name){
        DeleteApplicant deleteApplicant = postService.deleteApplicant(postId, name);
        return new BaseResponse<>(deleteApplicant);
    }

    @DeleteMapping("/post/{postId}/{userId}")
    public BaseResponse<DeletePost> deletePost(@PathVariable(name="postId") Long postId, @PathVariable(name="userId") Long userId){
        DeletePost deletePost = postService.deletePost(postId, userId);

        return new BaseResponse<>(deletePost);
    }

    @PatchMapping("/post/{postId}")
    public BaseResponse<Post> addPostView(@PathVariable(name="postId")Long postId){
        Post post = postService.addView(postId);
        return new BaseResponse<>(post);
    }

    @PatchMapping("/postCategory/{postId}/{name}")
    public BaseResponse<Post_category> updateCategory(@PathVariable(name="postId")Long postId, @PathVariable(name="name")String name){
        Post_category post_category = postService.updateCurrentCategory(postId, name);
        return new BaseResponse<>(post_category);
    }

    @PatchMapping("/postStatus/{postId}")
    public BaseResponse<Post> updatePostStatus(@PathVariable(name="postId")Long postId){
        Post post = postService.updatePost(postId);
        return new BaseResponse<>(post);
    }
}