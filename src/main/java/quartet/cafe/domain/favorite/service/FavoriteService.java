package quartet.cafe.domain.favorite.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quartet.cafe.domain.favorite.dto.FavoriteRequest;
import quartet.cafe.domain.favorite.dto.FavoriteResponse;
import quartet.cafe.domain.favorite.model.Favorite;
import quartet.cafe.domain.favorite.repository.FavoriteRepository;
import quartet.cafe.domain.user.model.User;
import quartet.cafe.domain.user.repository.UserRepository;
import quartet.cafe.domain.cafe.model.Cafe;
import quartet.cafe.domain.cafe.repository.CafeRepository;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final CafeRepository cafeRepository;

    @Transactional
    public FavoriteResponse toggleFavorite(FavoriteRequest request) {
        // 유저와 카페 찾기
        User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        Cafe cafe = cafeRepository.findById(request.getCafeId())
            .orElseThrow(() -> new IllegalArgumentException("카페를 찾을 수 없습니다."));

        // 즐겨찾기 존재 여부 확인
        Optional<Favorite> optionalFavorite = favoriteRepository.findByUserAndCafe(user, cafe);

        if (optionalFavorite.isPresent()) {
                // 있으면 삭제
                favoriteRepository.delete(optionalFavorite.get());

                return FavoriteResponse.builder()
                        .id(optionalFavorite.get().getId())
                        .userId(user.getId())
                        .cafeId(cafe.getId())
                        .isFavorited(false) // 현재 상태: 해제됨
                        .build();
        } else {
                // 없으면 등록
                Favorite saved = favoriteRepository.save(
                        Favorite.builder()
                                .user(user)
                                .cafe(cafe)
                                .build()
                );

                return FavoriteResponse.builder()
                        .id(saved.getId())
                        .userId(user.getId())
                        .cafeId(cafe.getId())
                        .isFavorited(true) // 현재 상태: 등록됨
                        .build();
        }
    }
}
