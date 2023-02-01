package ForCloud.backend.repository;


import ForCloud.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.user_name = ?1")
    Optional<User> findByUser_name(String user_name);
    @Query("select u from User u where u.user_email = ?1")
    Optional<User> findByUser_email(String user_email);

    @Query("select u from User u where u.user_token = ?1")
    Optional<User> findByUser_token(String user_token);

    @Query("select u from User u where u.id = ?1")
    Optional<User> findById(Long user_id);


}
