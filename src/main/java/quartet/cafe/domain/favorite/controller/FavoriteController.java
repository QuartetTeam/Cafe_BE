package quartet.cafe.domain.favorite.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quartet.cafe.domain.favorite.dto.FavoriteRequest;
import quartet.cafe.domain.favorite.dto.FavoriteResponse;
import quartet.cafe.domain.favorite.service.FavoriteService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorite")
public class FavoriteController {

    private final FavoriteService favoriteService;

    // 즐겨찾기 등록/해제 (toggle 방식)
    @PostMapping("/toggle")
    public ResponseEntity<FavoriteResponse> toggleFavorite(@RequestBody FavoriteRequest request) {
        FavoriteResponse response = favoriteService.toggleFavorite(request);
        return ResponseEntity.ok(response);
    }
}
