package quartet.cafe.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quartet.cafe.domain.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}