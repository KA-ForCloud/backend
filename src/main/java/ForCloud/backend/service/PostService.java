package ForCloud.backend.service;

import ForCloud.backend.data.*;
import ForCloud.backend.entity.*;
import ForCloud.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

        private final ParticipantRepository participantRepository;

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
    @Transactional
    public RequestApplicant registerApplicant(RequestApplicant request){
            Applicant applicant = new Applicant();
            Member member = memberRepository.findById(request.getMemberId()).get();
            Post post = postRepository.findById(request.getPostId()).get();
            applicant.setPost(post);
            applicant.setMember(member);
            applicant.setRequest(request.getRequest());
            return new RequestApplicant(applicantRepository.save(applicant));
    }

    @Transactional
    public RequestParticipant registerParticipant(RequestParticipant requestParticipant){
        Participant participant = new Participant();
        Member member = memberRepository.findByName(requestParticipant.getName()).get();
        Post post = postRepository.findById(requestParticipant.getPostId()).get();

        participant.setPost(post);
        participant.setMember(member);
        return new RequestParticipant(participantRepository.save(participant));
    }

    @Transactional
    public DeleteApplicant deleteApplicant (Long postId, String name){
            Applicant applicant = applicantRepository.findByPostId_MemberName(postId, name).get();
            applicantRepository.delete(applicant);

            return new DeleteApplicant(postId, name);
    }

    @Transactional
    public DeletePost deletePost (Long postId, Long memberId){
        Post post = postRepository.findByPost_MemberId(postId, memberId).get();
        postRepository.delete(post);

        return new DeletePost(postId, memberId);
    }

    @Transactional
    public Post addView(Long postId){
            Post post = postRepository.findById(postId).get();
            post.setView(post.getView()+1L);

            return post;
    }
}
