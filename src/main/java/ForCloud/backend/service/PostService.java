package ForCloud.backend.service;

import ForCloud.backend.data.ApplicantResponse;
import ForCloud.backend.data.MemberTemperature;
import ForCloud.backend.data.PostResponse;
import ForCloud.backend.data.ProjectResponse;
import ForCloud.backend.entity.Applicant;
import ForCloud.backend.entity.Member;
import ForCloud.backend.entity.Post;
import ForCloud.backend.entity.Post_category;
import ForCloud.backend.repository.ApplicantRepository;
import ForCloud.backend.repository.MemberRepository;
import ForCloud.backend.repository.PostCategoryRepository;
import ForCloud.backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
        private final PostRepository postRepository;
        private final ApplicantRepository applicantRepository;
        private final PostCategoryRepository postCategoryRepository;
        private final MemberRepository memberRepository;

        public List<PostResponse> getAllPosts(){
            List<Post> postList = postRepository.findAll();
            List<PostResponse> postResponseList = postList.stream()
                    .map(p -> new PostResponse(p))
                    .collect(Collectors.toList());
            return postResponseList;
        }

        public PostResponse getPost(Long postId){
            Post post = postRepository.findById(postId).get();
            PostResponse postResponse = new PostResponse(post);

            return postResponse;
        }

        public List<MemberTemperature> getTemperature(){
           List<Member> allNameSortedByTemperature =
                   memberRepository.findAll(Sort.by("temperature").descending());

            List<MemberTemperature> memberTemperatureList = new ArrayList<>();

            int i=0;
            for(Member m : allNameSortedByTemperature){
                if(i==5) break;
                MemberTemperature memberTemperature = new MemberTemperature();
                memberTemperature.setName(m.getName());
                memberTemperature.setTemperature(m.getTemperature());

                memberTemperatureList.add(memberTemperature);
                i++;
            }

            return memberTemperatureList;
        }

        public List<ApplicantResponse> getApplicant (Long postId){
            List<Applicant> applicant = applicantRepository.findAllByPostId(postId);

            List<ApplicantResponse> applicantResponses = applicant.stream()
                    .map(p -> new ApplicantResponse(p))
                    .collect(Collectors.toList());

            return applicantResponses;
        }

        public List<Post_category> getPostCategory (Long postId){
            List<Post_category> post_categoryList = postCategoryRepository.findAllById(postId);

            return post_categoryList;
        }

        public ProjectResponse getProjectInfo (Long postId){
            Post post = postRepository.findById(postId).get();
            ProjectResponse projectResponse = new ProjectResponse();
            projectResponse.setName(post.getMember().getName());
            projectResponse.setTitle(post.getTitle());
            projectResponse.setPeriod(post.getPeriod());
            projectResponse.setDuration(post.getDuration());
            projectResponse.setContents(post.getContents());

            return projectResponse;
    }
}
