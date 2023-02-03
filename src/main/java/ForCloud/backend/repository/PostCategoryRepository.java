package ForCloud.backend.repository;

import ForCloud.backend.entity.Post;
import ForCloud.backend.entity.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostCategoryRepository extends JpaRepository<PostCategory, Long> {

    List<PostCategory> findAllByPostId(Long postId);

    @Query("select p from PostCategory p where p.type = ?1")
    List<PostCategory> findAllByType(String type);

    @Query("select p from PostCategory p where p.post.id = ?1 and p.type = ?2")
    Optional<PostCategory> findById(Long postId, String type);
}
