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

    @GetMapping("/post/{postId}")
    public BaseResponse<PostResponse> getPost(@PathVariable(name="postId") Long postId){
        PostResponse post = postService.getPost(postId);

        return new BaseResponse<>(post);
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

    @GetMapping("/postCategory/{postId}")
    public BaseResponse<List<Post_category>> getPostCategory (@PathVariable(name="postId")Long postId){
        List<Post_category> post_categoryList = postService.getPostCategory(postId);

        return new BaseResponse<>(post_categoryList);
    }

    @GetMapping("/project/{postId}")
    public BaseResponse<ProjectResponse> getProjectInfo (@PathVariable(name="postId") Long postId){
        ProjectResponse projectResponse = postService.getProjectInfo(postId);

        return new BaseResponse<>(projectResponse);
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

    @DeleteMapping("/applicant/{postId}/{memberName}")
    public BaseResponse<DeleteApplicant> deleteApplicant(@PathVariable(name ="postId")Long postId, @PathVariable(name = "memberName")String name){
        DeleteApplicant deleteApplicant = postService.deleteApplicant(postId, name);
        return new BaseResponse<>(deleteApplicant);
    }

    @DeleteMapping("/post/{postId}/{memberId}")
    public BaseResponse<DeletePost> deletePost(@PathVariable(name="postId") Long postId, @PathVariable(name="memberId") Long memberId){
        DeletePost deletePost = postService.deletePost(postId, memberId);

        return new BaseResponse<>(deletePost);
    }

    @PatchMapping("/post/{postId}")
    public BaseResponse<Post> addPostView(@PathVariable(name="postId")Long postId){
        Post post = postService.addView(postId);
        return new BaseResponse<>(post);
    }
}
