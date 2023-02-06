package ForCloud.backend.service;

import ForCloud.backend.data.*;
import ForCloud.backend.entity.*;
import ForCloud.backend.repository.*;
import ForCloud.backend.type.ParticipantType;
import ForCloud.backend.type.PostType;
import ForCloud.backend.type.ProjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService {

        @Autowired
        public PostService(UserRepository userRepository, PostRepository postRepository, PostCategoryRepository postCategoryRepository, ApplicantRepository applicantRepository, ParticipantRepository participantRepository, ChattingRepository chattingRepository) {

        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.postCategoryRepository = postCategoryRepository;
        this.applicantRepository = applicantRepository;
        this.participantRepository = participantRepository;
            this.chattingRepository = chattingRepository;
        }
        private final PostRepository postRepository;
        private final ApplicantRepository applicantRepository;
        private final PostCategoryRepository postCategoryRepository;
        private final UserRepository userRepository;

        private final ParticipantRepository participantRepository;
        private final ChattingRepository chattingRepository;

        public List<PostResponse> getAllPosts(){
            try {
                List<Post> postList = postRepository.findAll();
                List<PostResponse> postResponseList = postList.stream()
                        .map(p -> new PostResponse(p))
                        .collect(Collectors.toList());
                return postResponseList;
            } catch (NoSuchElementException e ){
                throw new NoSuchElementException();
            }
        }

        public List<PostResponse> getMyPost(Long userId){
            try {
                List<Post> postList = postRepository.findAllByUser_Id(userId);
                List<PostResponse> postResponseList = postList.stream()
                        .map(p -> new PostResponse(p))
                        .collect(Collectors.toList());
                return postResponseList;
            } catch (NoSuchElementException e ){
                throw new NoSuchElementException();
            }
        }

        public List<PostResponse> getMyRequestedPost(Long userId){
            try {
                List<Applicant> applicantList = applicantRepository.findAllByUser_id(userId);
                List<PostResponse> postResponseList = new ArrayList<>();
                for (Applicant applicant : applicantList) {
                    postResponseList.add(new PostResponse(applicant.getPost()));
                }
                return postResponseList;
            } catch (NoSuchElementException e ){
                throw new NoSuchElementException();
            }
        }

    public List<GetProjectListResponse> getMyProject(Long userId){
            try{
        List<Participant> participantList = participantRepository.findAllByUser_Id(userId);
        List<GetProjectListResponse> getProjectListResponseList = new ArrayList<>();
        for(Participant participant : participantList){
            // 채팅 Entity에 type이 생기면 변경할 예정
            GetProjectListResponse getProjectListResponse = new GetProjectListResponse();
            getProjectListResponse.setId(participant.getPost().getId());
            getProjectListResponse.setContents(participant.getPost().getContents());
            getProjectListResponse.setName(participant.getUser().getUser_name());
            getProjectListResponse.setDuration(participant.getPost().getDuration());
            getProjectListResponse.setPost_category(participant.getPost().getPost_category());
            getProjectListResponse.setPost_name(participant.getPost().getPost_name());
            getProjectListResponse.setPostType(participant.getPost().getPostType());
            getProjectListResponse.setViews(participant.getPost().getViews());
            getProjectListResponse.setEnd_time(participant.getPost().getEnd_time());
            getProjectListResponse.setStart_time(participant.getPost().getStart_time());
            getProjectListResponse.setProjectType(participant.getChatting().getProjectType());

            getProjectListResponseList.add(getProjectListResponse);

        }
        return getProjectListResponseList;
            } catch (NoSuchElementException e ){
                throw new NoSuchElementException();
            }
    }

        public List<ApplicantResponse> getApplicant (Long postId){
            try {
                List<Applicant> applicant = applicantRepository.findAllByPost_id(postId);

                List<ApplicantResponse> applicantResponses = applicant.stream()
                        .map(p -> new ApplicantResponse(p))
                        .collect(Collectors.toList());

                return applicantResponses;
            } catch (NoSuchElementException e ){
                throw new NoSuchElementException();
            }
        }

        public PostCategoryResponse getCurrentCategory (Long postId){
            try {
                PostCategory postCategory = postCategoryRepository.findById(postId, "current").get();
                PostCategoryResponse postCategoryResponse = new PostCategoryResponse(postCategory);
                return postCategoryResponse;
            } catch (NoSuchElementException e ){
                throw new NoSuchElementException();
            }
        }

        public List<PopularCategoryResponse> getPopularCategory (){
            try {
                List<PostCategory> postCategoryList = postCategoryRepository.findAllByType("recruits");
                List<PopularCategoryResponse> popularCategoryResponseList = new ArrayList<>();
                Map<String, Integer> map = new HashMap<>();
                map.put("react", 0);
                map.put("java", 0);
                map.put("javascript", 0);
                map.put("python", 0);
                map.put("spring", 0);
                map.put("springboot", 0);

                for (PostCategory postCategory : postCategoryList) {
                    if (postCategory.getReact() > 0) {
                        map.put("react", map.get("react") + 1);
                    }
                    if (postCategory.getJava() > 0) {
                        map.put("java", map.get("java") + 1);
                    }
                    if (postCategory.getJavascript() > 0) {
                        map.put("javascript", map.get("javascript") + 1);
                    }
                    if (postCategory.getPython() > 0) {
                        map.put("python", map.get("python") + 1);
                    }
                    if (postCategory.getSpring() > 0) {
                        map.put("spring", map.get("spring") + 1);
                    }
                    if (postCategory.getSpringboot() > 0) {
                        map.put("springboot", map.get("springboot") + 1);
                    }
                }
                List<String> keySet = new ArrayList<>(map.keySet());
                keySet.sort((o1, o2) -> map.get(o2).compareTo(map.get(o1)));

                int i = 0;
                for (String key : keySet) {
                    if (i == 3) break;
                    PopularCategoryResponse popularCategoryResponse = new PopularCategoryResponse();
                    popularCategoryResponse.setImg("");
                    popularCategoryResponse.setCategory_name(key);
                    popularCategoryResponseList.add(popularCategoryResponse);
                    i++;
                }
                return popularCategoryResponseList;
            }
            catch (NoSuchElementException e ){
                throw new NoSuchElementException();
            }
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

    public PostParticipantResponse registerParticipant(RequestParticipant requestParticipant){
        Participant participant = new Participant();

        User user = userRepository.findById(requestParticipant.getUserId()).get();
        Post post = postRepository.findById(requestParticipant.getPostId()).get();
        Chatting chatting=chattingRepository.findByPostId(post.getId());

        participant.setPost(post);
        participant.setUser(user);

        participant.setProjectType(ProjectType.onGoing);
        participant.setChatting(chatting);
        participant.setType(ParticipantType.팀원);
        participant.setLast(0L);
        participant.setCategory(requestParticipant.getCategory());
        participantRepository.save(participant);
        PostParticipantResponse response=new PostParticipantResponse(user.getId(),post.getId(),user.getUser_name(),chatting.getId());

        return response;
    }

    @Transactional
    public DeleteApplicant deleteApplicant (Long postId, Long userId){
            try {
                Applicant applicant = applicantRepository.findByPost_UserId(postId, userId).get();
                applicantRepository.delete(applicant);

                return new DeleteApplicant(postId, userId);
            }catch (Exception e){
                throw new IllegalStateException();
            }
    }

    @Transactional
    public DeletePost deletePost (Long postId, Long userId){
            try {
                Post post = postRepository.findByIdAndUser_Id(postId, userId).get();
                postRepository.delete(post);

                return new DeletePost(postId, userId);
            }catch (Exception e){
                throw new IllegalStateException();
            }
    }

    @Transactional
    public Post addView(Long postId){
            try {
                Post post = postRepository.findById(postId).get();
                post.setViews(post.getViews() + 1);

                return post;
            }catch (Exception e){
                throw new IllegalStateException();
            }
    }

    @Transactional
    public PostCategory updateCurrentCategory(Long postId, Long userId){
        List<PostCategory> post_categoryList = postCategoryRepository.findAllByPostId(postId);
        List<Applicant> applicantList = applicantRepository.findAllByPost_id(postId);
        String category = "";
        for(int i=0; i<applicantList.size(); i++){
            if(userId == applicantList.get(i).getUser().getId()){
                category = applicantList.get(i).getRequest();
                break;
            }
        }
        PostCategory post_category = new PostCategory();
        for(int i=0; i<post_categoryList.size(); i++){
            if(post_categoryList.get(i).getType().equals("current")){
                post_category = post_categoryList.get(i);
                break;
            }
        }

        if(category.equals("react")){
            post_category.setReact(post_category.getReact()+1);
        }else if(category.equals("spring")){
            post_category.setSpring(post_category.getSpring()+1);
        }else if(category.equals("java")){
            post_category.setJava(post_category.getJava()+1);
        }else if(category.equals("python")){
            post_category.setPython(post_category.getPython()+1);
        }else if(category.equals("springboot")){
            post_category.setSpringboot(post_category.getSpringboot()+1);
        }else if(category.equals("javascript")){
            post_category.setJavascript(post_category.getJavascript()+1);
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
