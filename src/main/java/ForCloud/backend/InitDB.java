package ForCloud.backend;

import ForCloud.backend.entity.Applicant;
import ForCloud.backend.entity.Member;
import ForCloud.backend.entity.Post;
import ForCloud.backend.entity.Post_category;
import ForCloud.backend.type.PostType;
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

            Post post1 = createPost(member1, "제목1" ,"2023-01-11","2023-01-16", "3", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", PostType.recruiting);
            em.persist(post1);

            Post post2 = createPost(member2, "제목2", "2023-01-13","2023-01-13", "2", "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb", PostType.recruiting);
            em.persist(post2);

            Post post3 = createPost(member3, "제목3", "2023-01-14","2023-01-15", "4", "ccccccccccccccccccccccccccccccccccccc", PostType.completed);
            em.persist(post3);

            Applicant applicant1 = createApplicant(member2, post1, "react");
            em.persist(applicant1);

            Applicant applicant2 = createApplicant(member3, post1, "spring");
            em.persist(applicant2);

            Applicant applicant3 = createApplicant(member4, post1, "spring");
            em.persist(applicant3);

            Post_category post_category1 = createPost_category(post1, 2,0,0,2,0,0);
            em.persist(post_category1);

            Post_category post_category2 = createPost_category(post2, 3,3,0,0,0,0);
            em.persist(post_category2);

            Post_category post_category3 = createPost_category(post3, 2,0,4,2,0,0);
            em.persist(post_category3);

        }

        private Member createMember(String email, String username, double temperature){
           Member member=new Member();
           member.setEmail(email);
           member.setName(username);
           member.setTemperature(temperature);
           return member;
       }

       private Post createPost(Member member, String title, String date, String period, String duration, String contents, PostType type){
           Post post = new Post();
           post.setMember(member);
           post.setTitle(title);
           post.setDate(date);
           post.setPeriod(period);
           post.setDuration(duration);
           post.setContents(contents);
           post.setStatus(type);
           return post;
       }

       private Applicant createApplicant(Member member, Post post, String requested){
            Applicant applicant = new Applicant();
            applicant.setRequest(requested);
            applicant.setPost(post);
            applicant.setMember(member);
            return applicant;
       }

       private Post_category createPost_category(Post post, Integer react, Integer javascript, Integer spring
       , Integer springboot, Integer python, Integer java){
            Post_category post_category = new Post_category();
            post_category.setPost(post);
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