package ForCloud.backend.repository;

import ForCloud.backend.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant,Long> {
    List<Participant> findAllByMember_Id(Long memberId);
    List<Participant> findAllByChatting_Id(Long chattingId);
}
