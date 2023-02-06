package ForCloud.backend;

import ForCloud.backend.data.*;
import ForCloud.backend.entity.*;
import ForCloud.backend.repository.*;
import ForCloud.backend.service.PostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class PostTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private ApplicantRepository applicantRepository;
    @Autowired
    private PostCategoryRepository postCategoryRepository;
    @Autowired
    private PostService postService;

    @Order(1)
    @Test
    @DisplayName("모든 게시글 받아오기 테스트")
    void getAllPostTest(){
        List<PostResponse> postList = postService.getAllPosts();
        assertThat(postList.size()).isEqualTo(4);
    }

    @Order(2)
    @Test
    @DisplayName("내가 작성한 게시글 받아오기")
    void getMyPostTest(){
        List<PostResponse> postList = postService.getMyPost(1L);
        assertThat(postList.size()).isEqualTo(1);
    }

    @Order(3)
    @Test
    @DisplayName("내가 신청한 게시글 받아오기")
    void getMyRequestedPostTest(){
        List<PostResponse> postResponseList = postService.getMyRequestedPost(1L);
        assertThat(postResponseList.size()).isEqualTo(0);
    }

    @Order(4)
    @Test
    @DisplayName("게시글 열람 페이지에서 신청자 리스트 받아오기")
    void getApplicantTest(){
        List<ApplicantResponse> applicantResponses = postService.getApplicant(1L);
        assertThat(applicantResponses.size()).isEqualTo(1);
    }

    @Order(5)
    @Test
    @DisplayName("인기있는 카테고리 받아오기")
    void getPopularCategoryTest(){
        List<PopularCategoryResponse> popularCategoryResponseList = postService.getPopularCategory();
        System.out.println(popularCategoryResponseList);
        assertThat(popularCategoryResponseList.size()).isEqualTo(3);
    }

    @Order(6)
    @Test
    @DisplayName("신청자 등록하기")
    void registerApplicantTest() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"postId\": 1, \"userId\":3, \"request\": \"react\"}";
        RequestApplicant requestApplicant = objectMapper.readValue(json, RequestApplicant.class);
        postService.registerApplicant(requestApplicant);

        Applicant applicant = applicantRepository.findByPost_UserId(1L, 3L).get();
        System.out.println(applicant);
        assertThat(applicant.getRequest()).isEqualTo("react");
    }

    @Order(7)
    @Test
    @DisplayName("프로젝트 참가자 등록하기")
    void registerParticipantTest() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"postId\": 1, \"userId\":3}";
        RequestParticipant requestParticipant = objectMapper.readValue(json, RequestParticipant.class);
        postService.registerParticipant(requestParticipant);

        Participant participant = participantRepository.findByPost_User_Id(1L,3L).get();
        System.out.println(participant);
        assertThat(participant.getUser().getId()).isEqualTo(3L);
    }

    @Order(8)
    @Test
    @DisplayName("모집현황 업데이트")
    void updateCurrentCategoryTest() {
        PostCategory beforePostCategory = postCategoryRepository.findById(1L, "current").get();
        PostCategory updatePostCategory = postService.updateCurrentCategory(1L, 3L);

        System.out.println(beforePostCategory.getReact());
        System.out.println(updatePostCategory.getReact());

        assertThat(beforePostCategory.getReact()).isEqualTo(updatePostCategory.getReact());
    }

    @Order(9)
    @Test
    @DisplayName("신청자 리스트에서 제거하기")
    void deleteApplicantTest() {
        postService.deleteApplicant(1L, 3L);
        assertThat(applicantRepository.findByPost_UserId(1L, 3L).isEmpty()).isEqualTo(true);
    }


    @Order(12)
    @Test
    @DisplayName("게시글 제거하기")
    void deletePostTest() {
        postService.deletePost(1L, 1L);
        assertThat(postRepository.findById(1L).isEmpty()).isEqualTo(true);
    }

    @Order(10)
    @Test
    @DisplayName("조회수 증가하기")
    void addViewTest() {
        Post post1 = postRepository.findById(2L).get();
        Long view1 = post1.getViews();

        Post post2 = postService.addView(2L);
        Long view2 = post2.getViews();

        assertThat(view1 + 1).isEqualTo(view2);
    }


    @Order(11)
    @Test
    @DisplayName("게시글 상태 변화시키기 (모집중 -> 모집완료)")
    void updatePostTest() {
        Post beforePost = postRepository.findById(2L).get();
        Post updatePost = postService.updatePost(2L);

        assertThat(beforePost.getPostType()).isNotEqualTo(updatePost.getPostType());
    }
}
