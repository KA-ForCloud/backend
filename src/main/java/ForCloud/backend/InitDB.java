////package ForCloud.backend;
////
////import ForCloud.backend.entity.*;
////import ForCloud.backend.type.PostType;
////import ForCloud.backend.type.ProjectType;
////import lombok.RequiredArgsConstructor;
////import org.springframework.stereotype.Component;
////
////import javax.annotation.PostConstruct;
////import javax.persistence.*;
////import javax.transaction.Transactional;
////
////@Component
////@RequiredArgsConstructor
////public class InitDB {
////    private final InitService initService;
////    @PostConstruct
////    public void init(){
////        initService.dbInit1();
////    }
////    @Component
////    @Transactional
////    @RequiredArgsConstructor
////    static class InitService{
////        private final EntityManager em;
////
////        public void dbInit1(){
////            User user1 = createMember("a@gmail.com","aaa",36.5);
////            em.persist(user1);
////
////            User user2 = createMember("b@gmail.com","bbb",36.7);
////            em.persist(user2);
////
////            User user3 = createMember("c@gmail.com","ccc",36.8);
////            em.persist(user3);
////
////            User user4 = createMember("d@gmail.com","ddd",36.4);
////            em.persist(user4);
////
////            User user5 = createMember("e@gmail.com","eee",36.9);
////            em.persist(user5);
////
////            Post post1 = createPost(user1, "제목1" ,"2023-01-11","2023-01-16", "3", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", PostType.recruiting, 20L);
////            em.persist(post1);
////
////            Post post2 = createPost(user2, "제목2", "2023-01-13","2023-01-13", "2", "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb", PostType.recruiting, 30L);
////            em.persist(post2);
////
////            Post post3 = createPost(user4, "제목3", "2023-01-14","2023-01-15", "4", "ccccccccccccccccccccccccccccccccccccc", PostType.completed, 40L);
////            em.persist(post3);
////
////            Post post5 = createPost(user5, "제목5", "2023-01-14","2023-01-15", "4", "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", PostType.completed, 60L);
////            em.persist(post5);
////
////            Applicant applicant1 = createApplicant(user2, post1, "react");
////            em.persist(applicant1);
////
////            Applicant applicant2 = createApplicant(user3, post1, "springboot");
////            em.persist(applicant2);
////
////            Applicant applicant3 = createApplicant(user4, post1, "springboot");
////            em.persist(applicant3);
////
////            Participant participant1 = createParticipant(user1, post3, "react",ProjectType.onGoing);
////            em.persist(participant1);
////
////
////            Participant participant2 = createParticipant(user1, post5, "springboot",ProjectType.completed);
////            em.persist(participant2);
////
////            Participant participant3 = createParticipant(user2, post5, "springboot",ProjectType.completed);
////            em.persist(participant3);
////
////            PostCategory post_category1 = createPost_category(post1, "recruits",2L,0L,0L,2L,0L,0L);
////            em.persist(post_category1);
////
////            PostCategory post_category2 = createPost_category(post1, "current",0L,0L,0L,0L,0L,0L);
////            em.persist(post_category2);
////
////            PostCategory post_category3 = createPost_category(post2, "recruits",3L,3L,0L,0L,0L,0L);
////            em.persist(post_category3);
////
////            PostCategory post_category4 = createPost_category(post2, "current",0L,0L,0L,0L,0L,0L);
////            em.persist(post_category4);
////
////            PostCategory post_category5 = createPost_category(post3, "recruits",2L,0L,4L,2L,0L,0L);
////            em.persist(post_category5);
////
////            PostCategory post_category6 = createPost_category(post3, "current",0L,0L,0L,0L,0L,0L);
////            em.persist(post_category6);
////
////            PostCategory post_category7 = createPost_category(post5, "recruits",1L,0L,0L,1L,0L,0L);
////            em.persist(post_category7);
////
////            PostCategory post_category8 = createPost_category(post5, "current",0L,0L,0L,0L,0L,0L);
////            em.persist(post_category8);
////        }
////
////        private User createMember(String email, String username, double temperature){
////           User user =new User();
////           user.setUser_email(email);
////           user.setUser_name(username);
////           user.setTemperature(temperature);
////           return user;
////       }
////
////       private Post createPost(User user, String title, String date, String period, String duration, String contents, PostType type, Long view){
////           Post post = new Post();
////           post.setUser(user);
////           post.setPost_name(title);
////           post.setStart_time(date);
////           post.setEnd_time(period);
////           post.setDuration(duration);
////           post.setContents(contents);
////           post.setPostType(type);
////           post.setViews(view);
////           return post;
////       }
////
////       private Applicant createApplicant(User user, Post post, String requested){
////            Applicant applicant = new Applicant();
////            applicant.setRequest(requested);
////            applicant.setPost(post);
////            applicant.setUser(user);
////            return applicant;
////       }
////
////        private Participant createParticipant(User user, Post post, String category, ProjectType projectType){
////            Participant participant = new Participant();
////            participant.setPost(post);
////            participant.setCategory(category);
////            participant.setUser(user);
////            participant.setProjectType(projectType);
////            return participant;
////        }
////
////       private PostCategory createPost_category(Post post, String type, Long react, Long javascript, Long spring
////       , Long springboot, Long python, Long java){
////            PostCategory post_category = new PostCategory();
////            post_category.setPost(post);
////            post_category.setType(type);
////            post_category.setReact(react);
////            post_category.setJava(java);
////            post_category.setPython(python);
////            post_category.setJavascript(javascript);
////            post_category.setSpringboot(springboot);
////            post_category.setSpring(spring);
////            return post_category;
////       }
////    }
////}
//
//package ForCloud.backend;
//
//
//import ForCloud.backend.entity.*;
//import ForCloud.backend.repository.ChattingRepository;
//import ForCloud.backend.type.ParticipantType;
//import ForCloud.backend.type.PostType;
//import ForCloud.backend.type.ProjectType;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.PostConstruct;
//import javax.persistence.EntityManager;
//
//@Component
//@RequiredArgsConstructor
//public class InitDB {
//
//    private final InitService initService;
//    private static final Logger logger= LoggerFactory.getLogger(InitDB.class);
//
//    @PostConstruct
//    public void init(){
//        initService.initDB();
//    }
//    @Component
//    @Transactional
//    @RequiredArgsConstructor
//    static class InitService{
//
//        private final EntityManager em;
//        private final ChattingRepository chattingRepository;
//
//        public void initDB(){
//
//            // member
//            User member1=createMember("aaa@gmail.com","aaa", 36.5F); em.persist(member1);
//            User member2=createMember("bbb@gmail.com","bbb",20.5F); em.persist(member2);
//            User member3=createMember("ccc@gmail.com","ccc",40.5F); em.persist(member3);
//            User member4=createMember("ddd@gmail.com","ddd",15.5F); em.persist(member4);
//            User member5=createMember("eee@gmail.com","eee",55.5F); em.persist(member5);
//
//            // post
//            Post post1=createPost("title 1","contents 1",1,member1, PostType.recruiting);
//            Post post2=createPost("title 2","contents 2",2,member1,PostType.recruiting);
//            Post post3=createPost("title 3","contents 3",3,member2,PostType.recruiting);
//
//            // applicant - 신청자, 신청하는 게시글
//            Applicant applicant1=createApplicant(member2,post1); em.persist(applicant1);
//            Applicant applicant2=createApplicant(member3,post1); em.persist(applicant2);
//            Applicant applicant3=createApplicant(member4,post1); em.persist(applicant3);
//            Applicant applicant4=createApplicant(member3,post3); em.persist(applicant4);
//            Applicant applicant5=createApplicant(member4,post3); em.persist(applicant5);
//            Applicant applicant6=createApplicant(member5,post3); em.persist(applicant6);
//
//            // 게시자가 게시글 신청자를 승인하면 참여자로 승격되며, 채팅방에 초대됨.
//            Participant participant1=createParticipant(member2,1L); em.persist(participant1);
//            Participant participant2=createParticipant(member3,1L); em.persist(participant2);
//            Participant participant3=createParticipant(member4,1L); em.persist(participant3);
//
//            // single chatting
////            Chatting schatting1=createSingleChatting(member1,member2); em.persist(schatting1);
////            Chatting schatting2=createSingleChatting(member1,member3); em.persist(schatting2);
////            Chatting schatting3=createSingleChatting(member1,member4); em.persist(schatting3);
//
//        }
//
//        private User createMember(String email,String nickname,Float temperature){
//            User member=new User();
//            member.setUser_email(email);
//            member.setUser_name(nickname);
//            member.setTemperature(temperature);
//
//            return member;
//        }
//
//        private Post createPost(String title,String contents,Integer thumbnail,User member,PostType status){
//            // 게시글을 생성하는 순간
//            Post post=new Post();
////            post.setThumbnail(thumbnail);
//            post.setPost_name(title);
//            post.setContents(contents);
//            post.setUser(member);
//            post.setPostType(status);
////            post.setStatus(status);
//            em.persist(post);
//
//            // 해당 게시글에 대한 단체채팅방이 생성되고
//            Chatting chatting=new Chatting();
//            chatting.setPostId(post.getId());
//            chatting.setTitle(post.getPost_name());
//            chatting.setProjectType(ProjectType.onGoing);
//            chatting.setFilePath("filePath");
//            em.persist(chatting);
//
//            // 초기에 참여자는 게시자가 팀장으로 들어가서 한명만 존재.
//            Participant participant=new Participant();
//            participant.setChatting(chatting);
//            participant.setUser(post.getUser());
//            participant.setType(ParticipantType.팀장);
//            participant.setLast(0L);
//            em.persist(participant);
//
//            return post;
//        }
//
//        private Applicant createApplicant(User member,Post post){
//            Applicant applicant=new Applicant();
//            applicant.setPost(post);
//            applicant.setUser(member);
//
//            return applicant;
//        }
//
//        private Participant createParticipant(User member,Long chattingId){
//            Chatting chatting=chattingRepository.findById(chattingId).get();
//
//            Participant participant=new Participant();
//            participant.setUser(member);
//            participant.setType(ParticipantType.팀원);
//            participant.setChatting(chatting);
//            participant.setLast(0L);
//            return participant;
//        }
//
////        private Chatting createSingleChatting(User sender,User receiver){
////            Chatting chatting=new Chatting();
////            chatting.setTitle(sender.getU()+", "+receiver.getNickname());
////            chatting.setPostId(null);
////            chatting.setFilePath(null);
////
////            Participant p1=new Participant();
////            p1.setChatting(chatting);
////            p1.setType(ParticipantType.팀장);
////            p1.setMember(sender);
////            p1.setLast(0L);
////            em.persist(p1);
////
////            Participant p2=new Participant();
////            p2.setChatting(chatting);
////            p2.setType(ParticipantType.팀원);
////            p2.setMember(receiver);
////            p2.setLast(0L);
////            em.persist(p2);
////
////            return chatting;
////        }
//    }
//}
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

        private Applicant createApplicant(User user, Post post, String requested){
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