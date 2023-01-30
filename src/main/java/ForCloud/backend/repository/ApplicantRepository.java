package ForCloud.backend.repository;

import ForCloud.backend.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

    List<Applicant> findAllByPost_id (Long postId);

    List<Applicant> findAllByUser_id (Long userId);


    @Query("select a from Applicant a where a.post.id= ?1 and a.user.user_name = ?2")
    Optional<Applicant> findByPost_id_UserName (Long postId, String name);
}
