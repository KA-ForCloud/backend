package ForCloud.backend.repository;

import ForCloud.backend.entity.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChattingRepository extends JpaRepository<Chatting,Long> {
}
