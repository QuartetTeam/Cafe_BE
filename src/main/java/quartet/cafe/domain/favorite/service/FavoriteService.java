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

        // 이미 즐겨찾기 등록된 게 있는지 확인
        Favorite favorite = favoriteRepository.findByUserAndCafe(user, cafe)
                .orElse(Favorite.builder()
                        .user(user)
                        .cafe(cafe)
                        .isFavorited(false)
                        .build());

        // 즐겨찾기 상태 토글
        favorite.toggle();

        // 저장
        Favorite saved = favoriteRepository.save(favorite);

        // 응답으로 반환
        return FavoriteResponse.builder()
                .id(saved.getId())
                .userId((long) saved.getUser().getId())
                .cafeId((long) saved.getCafe().getId())
                .isFavorited(saved.isFavorited())
                .build();
    }
}
