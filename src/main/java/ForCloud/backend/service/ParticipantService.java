package ForCloud.backend.service;

import ForCloud.backend.entity.Chatting;
import ForCloud.backend.entity.Participant;
import ForCloud.backend.entity.User;
import ForCloud.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ParticipantService {

    @Autowired
    public ParticipantService(UserRepository userRepository,ChattingRepository chattingRepository, PostRepository postRepository, PostCategoryRepository postCategoryRepository, ApplicantRepository applicantRepository, ParticipantRepository participantRepository) {

        this.userRepository = userRepository;
        this.participantRepository = participantRepository;
        this.chattingRepository = chattingRepository;
    }
    private final ParticipantRepository participantRepository;

    private final UserRepository userRepository;
    private final ChattingRepository chattingRepository;
    @Transactional
    public String updateLastRead(Long memberId,Long roomId,Long lastId){

        User user = userRepository.findById(memberId).get();
        Chatting chatting = chattingRepository.findById(roomId).get();

        // TODO: 예외 처리
        Participant participant=participantRepository.findByPost_Participants_UserAndPost_Participants_Chatting(user,chatting);

        participant.setLast(lastId);

        return "업데이트 성공";
    }

    @Transactional
    public String deleteParticipant(Long memberId,Long roomId){

        User user = userRepository.findById(memberId).get();
        Chatting chatting = chattingRepository.findById(roomId).get();

        Participant participant=participantRepository.findByPost_Participants_UserAndPost_Participants_Chatting(user,chatting);
        participantRepository.delete(participant);
        return "나가기 성공";
    }
}
