package ForCloud.backend.repository;

import ForCloud.backend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface PostRepository extends JpaRepository<Post, Long> {

//    Optional<Post> findByPost_id(Long postId);
    List<Post> findAllByUser_Id(Long user_id);



    @Query("select p from Post p where p.id = ?1 and p.user.id = ?2")
    Optional<Post> findByIdAndUser_Id (Long post_id, Long user_id);

    Optional<Post> findById(Long post_id);


}
