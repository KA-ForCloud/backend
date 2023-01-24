package ForCloud.backend.repository;

import ForCloud.backend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findById(Long postId);
    List<Post> findAllById(Long userId);
    @Query("select p from Post p where p.id = ?1 and p.user.id = ?2")
    Optional<Post> findByPost_UserId (Long postId, Long userId);
}
