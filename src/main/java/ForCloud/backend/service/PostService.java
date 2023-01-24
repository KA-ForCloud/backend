package ForCloud.backend.service;

import ForCloud.backend.data.*;
import ForCloud.backend.entity.*;
import ForCloud.backend.repository.*;
import ForCloud.backend.type.PostType;
import ForCloud.backend.type.ProjectType;
import lombok.RequiredArgsConstructor;
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
        private final UserRepository userRepository;

        private final ParticipantRepository participantRepository;

        public List<PostResponse> getAllPosts(){
            List<Post> postList = postRepository.findAll();
            List<PostResponse> postResponseList = postList.stream()
                    .map(p -> new PostResponse(p))
                    .collect(Collectors.toList());
            return postResponseList;
        }

        public List<PostResponse> getMyPost(Long userId){
            List<Post> postList = postRepository.findAllById(userId);
            List<PostResponse> postResponseList = postList.stream()
                    .map(p -> new PostResponse(p))
                    .collect(Collectors.toList());
            return postResponseList;
        }
        public List<PostResponse> getMyRequestedPost(Long userId){
            List<Applicant> applicantList = applicantRepository.findAllByUserId(userId);
            List<PostResponse> postResponseList = new ArrayList<>();
            for(Applicant applicant : applicantList){
                postResponseList.add(new PostResponse(applicant.getPost()));
            }
            return postResponseList;
        }

        public List<GetProjectListResponse> getMyProject(Long userId){
            List<Participant> participantList = participantRepository.findAllByUserId(userId);
            List<GetProjectListResponse> getProjectListResponseList = new ArrayList<>();
            for(Participant participant : participantList){
                GetProjectListResponse getProjectListResponse = new GetProjectListResponse();
                getProjectListResponse.setPostResponse(new PostResponse(participant.getPost()));
                getProjectListResponse.setProjectType(participant.getProjectType());

                getProjectListResponseList.add(getProjectListResponse);
            }
            return getProjectListResponseList;
        }


        public List<MemberTemperature> getTemperature(){
           List<User> allNameSortedByTemperature =
                   userRepository.findAll(Sort.by("temperature").descending());

            List<MemberTemperature> memberTemperatureList = new ArrayList<>();

            int i=0;
            for(User m : allNameSortedByTemperature){
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

    @Transactional
    public RequestApplicant registerApplicant(RequestApplicant request){
            Applicant applicant = new Applicant();
            User user = userRepository.findById(request.getUserId()).get();
            Post post = postRepository.findById(request.getPostId()).get();
            applicant.setPost(post);
            applicant.setUser(user);
            applicant.setRequest(request.getRequest());
            return new RequestApplicant(applicantRepository.save(applicant));
    }

    @Transactional
    public RequestParticipant registerParticipant(RequestParticipant requestParticipant){
        Participant participant = new Participant();
        User user = userRepository.findByName(requestParticipant.getName()).get();
        Post post = postRepository.findById(requestParticipant.getPostId()).get();

        participant.setPost(post);
        participant.setUser(user);
        participant.setProjectType(ProjectType.onGoing);
        return new RequestParticipant(participantRepository.save(participant));
    }

    @Transactional
    public DeleteApplicant deleteApplicant (Long postId, String name){
            Applicant applicant = applicantRepository.findByPostId_UserName(postId, name).get();
            applicantRepository.delete(applicant);

            return new DeleteApplicant(postId, name);
    }

    @Transactional
    public DeletePost deletePost (Long postId, Long userId){
        Post post = postRepository.findByPost_UserId(postId, userId).get();
        postRepository.delete(post);

        return new DeletePost(postId, userId);
    }

    @Transactional
    public Post addView(Long postId){
            Post post = postRepository.findById(postId).get();
            post.setViews(post.getViews()+1L);

            return post;
    }

    @Transactional
    public Post_category updateCurrentCategory(Long postId, String name){
        List<Post_category> post_categoryList = postCategoryRepository.findAllByPostId(postId);
        User user = userRepository.findByName(name).get();
        List<Applicant> applicantList = applicantRepository.findAllByPostId(postId);
        String category = "";
        for(int i=0; i<applicantList.size(); i++){
            if(user.getName().equals(applicantList.get(i).getUser().getName())){
                category = applicantList.get(i).getRequest();
                break;
            }
        }
        Post_category post_category = new Post_category();
        for(int i=0; i<post_categoryList.size(); i++){
            if(post_categoryList.get(i).getType().equals("current")){
                post_category = post_categoryList.get(i);
                break;
            }
        }

        if(category.equals("react")){
            post_category.setReact(post_category.getReact()+1L);
        }else if(category.equals("spring")){
            post_category.setSpring(post_category.getSpring()+1L);
        }else if(category.equals("java")){
            post_category.setJava(post_category.getJava()+1L);
        }else if(category.equals("python")){
            post_category.setPython(post_category.getPython()+1L);
        }else if(category.equals("springboot")){
            post_category.setSpringboot(post_category.getSpringboot()+1L);
        }else if(category.equals("javascript")){
            post_category.setJavascript(post_category.getJavascript()+1L);
        }
        return post_category;
    }
    @Transactional
    public Post updatePost(Long postId){
        Post post = postRepository.findById(postId).get();
        post.setPostType(PostType.completed);

        return post;
    }

}
