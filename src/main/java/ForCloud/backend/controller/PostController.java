package ForCloud.backend.controller;

import ForCloud.backend.config.BaseResponse;
import ForCloud.backend.data.ApplicantResponse;
import ForCloud.backend.data.MemberTemperature;
import ForCloud.backend.data.PostResponse;
import ForCloud.backend.data.ProjectResponse;
import ForCloud.backend.entity.Post;
import ForCloud.backend.entity.Post_category;
import ForCloud.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
