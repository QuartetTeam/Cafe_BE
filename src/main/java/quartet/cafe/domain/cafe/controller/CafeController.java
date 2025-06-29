package quartet.cafe.domain.cafe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quartet.cafe.domain.cafe.dto.CafeDetailResponse;
import quartet.cafe.domain.cafe.service.CafeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cafes")
public class CafeController {

    private final CafeService cafeService;

    // 카페 상세 조회 API
    @GetMapping("/{cafeId}")
    public ResponseEntity<CafeDetailResponse> getCafeDetail(@PathVariable Long cafeId) {
        CafeDetailResponse response = cafeService.getCafeDetail(cafeId);
        return ResponseEntity.ok(response);
    }
}
