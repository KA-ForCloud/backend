package ForCloud.backend;

import ForCloud.backend.entity.*;
import ForCloud.backend.type.PostType;
import ForCloud.backend.type.ProjectType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;
    @PostConstruct
    public void init(){
        initService.dbInit1();
    }
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final EntityManager em;

        public void dbInit1(){
            User user1 = createMember("a@gmail.com","aaa",36.5);
            em.persist(user1);

            User user2 = createMember("b@gmail.com","bbb",36.7);
            em.persist(user2);

            User user3 = createMember("c@gmail.com","ccc",36.8);
            em.persist(user3);

            User user4 = createMember("d@gmail.com","ddd",36.4);
            em.persist(user4);

            User user5 = createMember("e@gmail.com","eee",36.9);
            em.persist(user5);

            Post post1 = createPost(user1, "제목1" ,"2023-01-11","2023-01-16", "3", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", PostType.recruiting, 20L);
            em.persist(post1);

            Post post2 = createPost(user2, "제목2", "2023-01-13","2023-01-13", "2", "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb", PostType.recruiting, 30L);
            em.persist(post2);

            Post post3 = createPost(user4, "제목3", "2023-01-14","2023-01-15", "4", "ccccccccccccccccccccccccccccccccccccc", PostType.completed, 40L);
            em.persist(post3);

            Post post5 = createPost(user5, "제목5", "2023-01-14","2023-01-15", "4", "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", PostType.completed, 60L);
            em.persist(post5);

            Chatting chatting1 = createChatting(1L, "채팅방1", "abcd", ProjectType.onGoing);
            em.persist(chatting1);

            Chatting chatting2 = createChatting(2L, "채팅방2", "abcde", ProjectType.onGoing);
            em.persist(chatting2);

            Chatting chatting3 = createChatting(3L, "채팅방3", "abcdf", ProjectType.completed);
            em.persist(chatting3);

            Chatting chatting4 = createChatting(4L, "채팅방4", "abcdg", ProjectType.completed);
            em.persist(chatting4);

            Applicant applicant1 = createApplicant(user2, post1, "react");
            em.persist(applicant1);

            Applicant applicant2 = createApplicant(user3, post1, "springboot");
            em.persist(applicant2);

            Applicant applicant3 = createApplicant(user4, post1, "springboot");
            em.persist(applicant3);

            Participant participant1 = createParticipant(user1, post3, chatting3, "react");
            em.persist(participant1);

            Participant participant2 = createParticipant(user1, post5, chatting4,"springboot");
            em.persist(participant2);

            Participant participant3 = createParticipant(user2, post5,chatting4, "springboot");

            em.persist(participant3);

            PostCategory post_category1 = createPost_category(post1, "recruits",2L,0L,0L,2L,0L,0L);
            em.persist(post_category1);

            PostCategory post_category2 = createPost_category(post1, "current",0L,0L,0L,0L,0L,0L);
            em.persist(post_category2);

            PostCategory post_category3 = createPost_category(post2, "recruits",3L,3L,0L,0L,0L,0L);
            em.persist(post_category3);

            PostCategory post_category4 = createPost_category(post2, "current",0L,0L,0L,0L,0L,0L);
            em.persist(post_category4);

            PostCategory post_category5 = createPost_category(post3, "recruits",2L,0L,4L,2L,0L,0L);
            em.persist(post_category5);

            PostCategory post_category6 = createPost_category(post3, "current",0L,0L,0L,0L,0L,0L);
            em.persist(post_category6);

            PostCategory post_category7 = createPost_category(post5, "recruits",1L,0L,0L,1L,0L,0L);
            em.persist(post_category7);

            PostCategory post_category8 = createPost_category(post5, "current",0L,0L,0L,0L,0L,0L);
            em.persist(post_category8);
        }

        private User createMember(String email, String username, double temperature){
            User user =new User();
            user.setUser_email(email);
            user.setUser_name(username);
            user.setTemperature(temperature);
            return user;
        }

        private Post createPost(User user, String title, String date, String period, String duration, String contents, PostType type, Long view){
            Post post = new Post();
            post.setUser(user);
            post.setPost_name(title);
            post.setStart_time(date);
            post.setEnd_time(period);
            post.setDuration(Long.valueOf(duration));
            post.setContents(contents);
            post.setPostType(type);
            post.setViews(view);
            return post;
        }

        private Applicant createApplicant(User user, Post post, String requested) {
            Applicant applicant = new Applicant();
            applicant.setRequest(requested);
            applicant.setPost(post);
            applicant.setUser(user);
            return applicant;

        }

        private Participant createParticipant(User user, Post post, Chatting chatting, String category){
            Participant participant = new Participant();
            participant.setPost(post);
            participant.setCategory(category);
            participant.setChatting(chatting);
            participant.setUser(user);
            return participant;
        }

        private PostCategory createPost_category(Post post, String type, Long react, Long javascript, Long spring
                , Long springboot, Long python, Long java){
            PostCategory post_category = new PostCategory();
            post_category.setPost(post);
            post_category.setType(type);
            post_category.setReact(react);
            post_category.setJava(java);
            post_category.setPython(python);
            post_category.setJavascript(javascript);
            post_category.setSpringboot(springboot);
            post_category.setSpring(spring);
            return post_category;

        }
        private Chatting createChatting(Long postId, String title, String filePath, ProjectType projectType){
            Chatting chatting = new Chatting();
            chatting.setPostId(postId);
            chatting.setTitle(title);
            chatting.setFilePath(filePath);
            chatting.setProjectType(projectType);
            return chatting;
        }
    }
}