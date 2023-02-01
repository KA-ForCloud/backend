package ForCloud.backend.service;

import ForCloud.backend.dto.Participant.GetLastReadResponse;
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

    /**
     * 마지막으로 읽은 메세지 인덱스 업데이트
     * @param memberId
     * @param roomId
     * @param lastId
     * @return
     */
    @Transactional
    public String updateLastRead(Long memberId,Long roomId,Long lastId){

        User user = userRepository.findById(memberId).get();
        Chatting chatting = chattingRepository.findById(roomId).get();

        // TODO: 예외 처리
        Participant participant=participantRepository.findByUser_IdAndChatting_Id(memberId,roomId);

        participant.setLast(lastId);

        return "업데이트 성공";
    }

    /**
     * 참여자의 채팅방 나가기
     * @param memberId
     * @param roomId
     * @return
     */
    @Transactional
    public String deleteParticipant(Long memberId,Long roomId){

        Participant participant=participantRepository.findByUser_IdAndChatting_Id(memberId,roomId);

        participantRepository.delete(participant);
        return "나가기 성공";
    }

    /**
     * 마지막으로 읽은 메세지 조회
     * @param memberId
     * @param roomId
     * @return
     */
    @Transactional
    public GetLastReadResponse getLastRead(Long memberId, Long roomId){
        User user = userRepository.findById(memberId).get();
        Chatting chatting = chattingRepository.findById(roomId).get();

        Participant participant=participantRepository.findByUser_IdAndChatting_Id(memberId,roomId);

        GetLastReadResponse response=new GetLastReadResponse();
        response.setMemberId(memberId);
        response.setLastReadIndex(participant.getLast());

        return response;
    }
}
