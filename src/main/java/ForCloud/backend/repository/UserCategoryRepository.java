package ForCloud.backend.repository;

import ForCloud.backend.entity.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {
    @Query("select u from UserCategory u where u.user_id = ?1")
    Optional<UserCategory> findByUser_id(Long user_id);
}
