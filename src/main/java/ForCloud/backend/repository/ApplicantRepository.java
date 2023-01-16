package ForCloud.backend.repository;

import ForCloud.backend.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    @Query("select a from Applicant a where a.post.id = ?1 and a.member.id =?2")
    List<Applicant> findAllByPostMemberId(Long postId, Long memberId);
}
