package ForCloud.backend.repository;

import ForCloud.backend.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant,Long> {
    List<Participant> findAllByMember_Id(Long memberId);
    List<Participant> findAllByChatting_Id(Long chattingId);
    Participant findByMember_IdAndChatting_Id(Long memberId,Long chattingId);
}
