package ForCloud.backend.service;

import ForCloud.backend.dto.Chatting.ChattingResponse;
import ForCloud.backend.dto.Participant.ParticipantResponse;
import ForCloud.backend.entity.Chatting;
import ForCloud.backend.entity.Participant;
import ForCloud.backend.entity.User;
import ForCloud.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
public class ChattingService {
    @Autowired
    public ChattingService(UserRepository userRepository, ChattingRepository chattingRepository, PostRepository postRepository, PostCategoryRepository postCategoryRepository, ApplicantRepository applicantRepository, ParticipantRepository participantRepository) {

        this.userRepository = userRepository;
        this.participantRepository = participantRepository;
        this.chattingRepository = chattingRepository;
    }
    private final ChattingRepository chattingRepository;
    private final ParticipantRepository participantRepository;

    private final UserRepository userRepository;
    @Transactional
    public List<ChattingResponse> getChattingList(Long memberId){

        User user = userRepository.findById(memberId).get();
        List<Participant> participatingList=participantRepository.findByPost_Participants_User(user);
        List<ChattingResponse> chattingList=new ArrayList<>();
        for(Participant p:participatingList) {
            Chatting chatting=chattingRepository.findById(p.getChatting().getId()).get();
            List<Participant> participantList=participantRepository.findByPost_Participants_Chatting(chatting);
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

        List<Participant> participantList=participantRepository.findByPost_Participants_Chatting(chatting);
        for(Participant p:participantList){
            participantRepository.delete(p);
        }

        chattingRepository.delete(chatting);
        return "삭제 성공";
    }
}
