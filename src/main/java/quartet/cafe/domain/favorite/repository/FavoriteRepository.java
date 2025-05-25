package quartet.cafe.domain.favorite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import quartet.cafe.domain.user.model.User;
import quartet.cafe.domain.cafe.model.Cafe;
import quartet.cafe.domain.favorite.model.Favorite;

import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Optional<Favorite> findByUserAndCafe(User user, Cafe cafe);

}
