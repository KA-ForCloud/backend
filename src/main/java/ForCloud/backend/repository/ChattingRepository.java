package ForCloud.backend.repository;

import ForCloud.backend.entity.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ChattingRepository extends JpaRepository<Chatting,Long> {

    @Override
    Optional<Chatting> findById(Long aLong);
}
