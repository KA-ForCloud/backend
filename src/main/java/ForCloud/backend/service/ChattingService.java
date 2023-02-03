package ForCloud.backend.service;

import ForCloud.backend.dto.Chatting.ChattingResponse;
import ForCloud.backend.dto.Participant.ParticipantResponse;
import ForCloud.backend.entity.Chatting;
import ForCloud.backend.entity.Participant;
import ForCloud.backend.entity.Post;
import ForCloud.backend.entity.User;
import ForCloud.backend.repository.*;
import ForCloud.backend.type.ProjectType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChattingService {

//    @Autowired
//    public ChattingService(UserRepository userRepository, ChattingRepository chattingRepository, PostRepository postRepository, PostCategoryRepository postCategoryRepository, ApplicantRepository applicantRepository, ParticipantRepository participantRepository) {
//
//        this.userRepository = userRepository;
//        this.participantRepository = participantRepository;
//        this.chattingRepository = chattingRepository;
//        this.postRepository = postRepository;
//    }
    private final ChattingRepository chattingRepository;
    private final ParticipantRepository participantRepository;

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    @Transactional
    public List<ChattingResponse> getChattingList(Long memberId){

        User user = userRepository.findById(memberId).get();
        List<Participant> participatingList=participantRepository.findAllByUser_Id(memberId);
        List<ChattingResponse> chattingList=new ArrayList<>();
        for(Participant p:participatingList) {
            Chatting chatting=chattingRepository.findById(p.getChatting().getId()).get();
            List<Participant> participantList=participantRepository.findAllByChatting_Id(chatting.getId());
            List<ParticipantResponse> participantResponse=participantList.stream()
                    .map(pl->new ParticipantResponse(pl))
                    .collect(toList());
            ChattingResponse res=new ChattingResponse(chatting,participantResponse,p.getLast());
            chattingList.add(res);
        }
        return chattingList;
    }

    @Transactional
    public String deleteRoom(Long memberId,Long roomId){
        Chatting chatting=chattingRepository.findById(roomId).get();
        Post post=postRepository.findById(chatting.getPostId()).get();
        postRepository.delete(post);
        List<Participant> participantList=participantRepository.findAllByChatting_Id(roomId);
        for(Participant p:participantList){
            participantRepository.delete(p);
        }

        chattingRepository.delete(chatting);
        return "삭제 성공";
    }

    @Transactional
    public String endRoom(Long memberId,Long roomId){

        Chatting chatting=chattingRepository.findById(roomId).get();
        chatting.setProjectType(ProjectType.completed);

        return "종료 성공";
    }
}
