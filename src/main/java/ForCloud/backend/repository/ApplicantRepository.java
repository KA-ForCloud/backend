package ForCloud.backend.repository;

import ForCloud.backend.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

    List<Applicant> findAllByPostId (Long postId);

    List<Applicant> findAllByUserId (Long userId);


    @Query("select a from Applicant a where a.post.id= ?1 and a.user.name = ?2")
    Optional<Applicant> findByPostId_UserName (Long postId, String name);
}