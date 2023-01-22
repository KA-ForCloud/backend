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
            Member member1= createMember("a@gmail.com","aaa",36.5);
            em.persist(member1);

            Member member2= createMember("b@gmail.com","bbb",36.7);
            em.persist(member2);

            Member member3= createMember("c@gmail.com","ccc",36.8);
            em.persist(member3);

            Member member4= createMember("d@gmail.com","ddd",36.4);
            em.persist(member4);

            Member member5= createMember("e@gmail.com","eee",36.9);
            em.persist(member5);

            Post post1 = createPost(member1, "제목1" ,"2023-01-11","2023-01-16", "3", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", PostType.recruiting, 20L);
            em.persist(post1);

            Post post2 = createPost(member2, "제목2", "2023-01-13","2023-01-13", "2", "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb", PostType.recruiting, 30L);
            em.persist(post2);

            Post post3 = createPost(member4, "제목3", "2023-01-14","2023-01-15", "4", "ccccccccccccccccccccccccccccccccccccc", PostType.completed, 40L);
            em.persist(post3);

            Post post5 = createPost(member5, "제목5", "2023-01-14","2023-01-15", "4", "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", PostType.completed, 60L);
            em.persist(post5);

            Applicant applicant1 = createApplicant(member2, post1, "react");
            em.persist(applicant1);

            Applicant applicant2 = createApplicant(member3, post1, "springboot");
            em.persist(applicant2);

            Applicant applicant3 = createApplicant(member4, post1, "springboot");
            em.persist(applicant3);

            Participant participant1 = createParticipant(member1, post3, "react",ProjectType.onGoing);
            em.persist(participant1);


            Participant participant2 = createParticipant(member1, post5, "springboot",ProjectType.completed);
            em.persist(participant2);

            Participant participant3 = createParticipant(member2, post5, "springboot",ProjectType.completed);
            em.persist(participant3);

            Post_category post_category1 = createPost_category(post1, "recruits",2L,0L,0L,2L,0L,0L);
            em.persist(post_category1);

            Post_category post_category2 = createPost_category(post1, "current",0L,0L,0L,0L,0L,0L);
            em.persist(post_category2);

            Post_category post_category3 = createPost_category(post2, "recruits",3L,3L,0L,0L,0L,0L);
            em.persist(post_category3);

            Post_category post_category4 = createPost_category(post2, "current",0L,0L,0L,0L,0L,0L);
            em.persist(post_category4);

            Post_category post_category5 = createPost_category(post3, "recruits",2L,0L,4L,2L,0L,0L);
            em.persist(post_category5);

            Post_category post_category6 = createPost_category(post3, "current",0L,0L,0L,0L,0L,0L);
            em.persist(post_category6);

            Post_category post_category7 = createPost_category(post5, "recruits",1L,0L,0L,1L,0L,0L);
            em.persist(post_category7);

            Post_category post_category8 = createPost_category(post5, "current",0L,0L,0L,0L,0L,0L);
            em.persist(post_category8);
        }

        private Member createMember(String email, String username, double temperature){
           Member member=new Member();
           member.setEmail(email);
           member.setName(username);
           member.setTemperature(temperature);
           return member;
       }

       private Post createPost(Member member, String title, String date, String period, String duration, String contents, PostType type, Long view){
           Post post = new Post();
           post.setMember(member);
           post.setTitle(title);
           post.setDate(date);
           post.setPeriod(period);
           post.setDuration(duration);
           post.setContents(contents);
           post.setStatus(type);
           post.setView(view);
           return post;
       }

       private Applicant createApplicant(Member member, Post post, String requested){
            Applicant applicant = new Applicant();
            applicant.setRequest(requested);
            applicant.setPost(post);
            applicant.setMember(member);
            return applicant;
       }

        private Participant createParticipant(Member member, Post post, String category, ProjectType projectType){
            Participant participant = new Participant();
            participant.setPost(post);
            participant.setCategory(category);
            participant.setMember(member);
            participant.setProjectType(projectType);
            return participant;
        }

       private Post_category createPost_category(Post post, String type, Long react, Long javascript, Long spring
       , Long springboot, Long python, Long java){
            Post_category post_category = new Post_category();
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
    }
}