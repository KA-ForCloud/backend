package ForCloud.backend;


import ForCloud.backend.entity.*;
import ForCloud.backend.repository.ChattingRepository;
import ForCloud.backend.type.ParticipantType;
import ForCloud.backend.type.PostType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;
    private static final Logger logger= LoggerFactory.getLogger(InitDB.class);

    @PostConstruct
    public void init(){
        initService.initDB();
    }
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final EntityManager em;
        private final ChattingRepository chattingRepository;

        public void initDB(){

            // member
            Member member1=createMember("aaa@gmail.com","aaa", 36.5F); em.persist(member1);
            Member member2=createMember("bbb@gmail.com","bbb",20.5F); em.persist(member2);
            Member member3=createMember("ccc@gmail.com","ccc",40.5F); em.persist(member3);
            Member member4=createMember("ddd@gmail.com","ddd",15.5F); em.persist(member4);
            Member member5=createMember("eee@gmail.com","eee",55.5F); em.persist(member5);

            // post
            Post post1=createPost("title 1","contents 1",1,member1, PostType.모집중);
            Post post2=createPost("title 2","contents 2",2,member1,PostType.모집완료);
            Post post3=createPost("title 3","contents 3",3,member2,PostType.모집중);

            // applicant - 신청자, 신청하는 게시글
            Applicant applicant1=createApplicant(member2,post1); em.persist(applicant1);
            Applicant applicant2=createApplicant(member3,post1); em.persist(applicant2);
            Applicant applicant3=createApplicant(member4,post1); em.persist(applicant3);
            Applicant applicant4=createApplicant(member3,post3); em.persist(applicant4);
            Applicant applicant5=createApplicant(member4,post3); em.persist(applicant5);
            Applicant applicant6=createApplicant(member5,post3); em.persist(applicant6);

            // 게시자가 게시글 신청자를 승인하면 참여자로 승격되며, 채팅방에 초대됨.
            Participant participant1=createParticipant(member2,1L); em.persist(participant1);
            Participant participant2=createParticipant(member3,1L); em.persist(participant2);
            Participant participant3=createParticipant(member4,1L); em.persist(participant3);

            // single chatting
            Chatting schatting1=createSingleChatting(member1,member2); em.persist(schatting1);
            Chatting schatting2=createSingleChatting(member1,member3); em.persist(schatting2);
            Chatting schatting3=createSingleChatting(member1,member4); em.persist(schatting3);

        }

        private Member createMember(String email,String nickname,Float temperature){
            Member member=new Member();
            member.setEmail(email);
            member.setNickname(nickname);
            member.setTemperature(temperature);

            return member;
        }

        private Post createPost(String title,String contents,Integer thumbnail,Member member,PostType status){
            // 게시글을 생성하는 순간
            Post post=new Post();
            post.setThumbnail(thumbnail);
            post.setTitle(title);
            post.setContents(contents);
            post.setMember(member);
            post.setStatus(status);
            em.persist(post);

            // 해당 게시글에 대한 단체채팅방이 생성되고
            Chatting chatting=new Chatting();
            chatting.setPostId(post.getId());
            chatting.setTitle(post.getTitle());
            chatting.setFilePath("filePath");
            em.persist(chatting);

            // 초기에 참여자는 게시자가 팀장으로 들어가서 한명만 존재.
            Participant participant=new Participant();
            participant.setChatting(chatting);
            participant.setMember(post.getMember());
            participant.setType(ParticipantType.팀장);
            em.persist(participant);

            return post;
        }

        private Applicant createApplicant(Member member,Post post){
            Applicant applicant=new Applicant();
            applicant.setPost(post);
            applicant.setMember(member);

            return applicant;
        }

        private Participant createParticipant(Member member,Long chattingId){
            Chatting chatting=chattingRepository.findById(chattingId).get();

            Participant participant=new Participant();
            participant.setMember(member);
            participant.setType(ParticipantType.팀원);
            participant.setChatting(chatting);

            return participant;
        }

        private Chatting createSingleChatting(Member sender,Member receiver){
            Chatting chatting=new Chatting();
            chatting.setTitle(sender.getNickname()+", "+receiver.getNickname());
            chatting.setPostId(null);
            chatting.setFilePath(null);

            Participant p1=new Participant();
            p1.setChatting(chatting);
            p1.setType(ParticipantType.팀장);
            p1.setMember(sender);
            em.persist(p1);

            Participant p2=new Participant();
            p2.setChatting(chatting);
            p2.setType(ParticipantType.팀원);
            p2.setMember(receiver);
            em.persist(p2);

            return chatting;
        }
    }
}
