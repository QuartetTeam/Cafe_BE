package quartet.cafe.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quartet.cafe.domain.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByKakaoId(String kakaoId);
    Optional<User> findByEmail(String email);
}