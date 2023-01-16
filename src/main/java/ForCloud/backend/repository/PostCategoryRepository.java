package ForCloud.backend.repository;

import ForCloud.backend.entity.Post_category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostCategoryRepository extends JpaRepository<Post_category, Long> {

    List<Post_category> findAllById(Long postId);
}
