package ForCloud.backend.service;

import ForCloud.backend.entity.Participant;
import ForCloud.backend.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ParticipantService {
    private final ParticipantRepository participantRepository;
    @Transactional
    public String updateLastRead(Long memberId,Long roomId,Long lastId){

        // TODO: 예외 처리
        Participant participant=participantRepository.findByMember_IdAndChatting_Id(memberId,roomId);

        participant.setLast(lastId);

        return "업데이트 성공";
    }

    @Transactional
    public String deleteParticipant(Long memberId,Long roomId){
        Participant participant=participantRepository.findByMember_IdAndChatting_Id(memberId,roomId);
        participantRepository.delete(participant);
        return "나가기 성공";
    }
}
