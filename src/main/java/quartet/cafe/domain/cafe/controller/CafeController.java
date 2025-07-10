package quartet.cafe.domain.cafe.controller;

import io.swagger.v3.oas.annotations.Operation; // Swagger 문서화를 위한 어노테이션
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quartet.cafe.domain.cafe.dto.CafeDetailResponse;
import quartet.cafe.domain.cafe.model.Cafe;
import quartet.cafe.domain.cafe.repository.CafeRepository;
import quartet.cafe.domain.cafe.repository.CategoryCafeRepository;
import quartet.cafe.domain.cafe.service.CafeService;
import quartet.cafe.domain.category.model.CategoryCafe;

import java.util.List;

@RestController // 이 클래스가 REST API 컨트롤러임을 나타냄
@RequiredArgsConstructor // final로 선언된 필드에 대해 생성자를 자동 생성
@RequestMapping("/api/cafes") // 기본 URL 매핑
public class CafeController {

    private final CafeService cafeService; // 카페 상세 조회 비즈니스 로직 담당
    private final CategoryCafeRepository categoryCafeRepository; // 카테고리별 카페 목록 조회용

    /**
     * 카페 상세 조회 API
     * - 카페 ID를 받아 해당 카페의 상세 정보를 반환
     * - Swagger 문서에는 summary 및 description 표시
     * - 예: GET /api/cafes/1
     */
    @Operation(summary = "카페 상세 조회", description = "카페 ID를 이용해 해당 카페의 상세 정보를 조회합니다.")
    @GetMapping("/{cafeId}")
    public ResponseEntity<CafeDetailResponse> getCafeDetail(@PathVariable Long cafeId) {
        CafeDetailResponse response = cafeService.getCafeDetail(cafeId); // 서비스 레이어에 위임
        return ResponseEntity.ok(response); // HTTP 200 응답
    }

    /**
     * 카테고리별 카페 목록 조회 API
     * - 특정 카테고리에 속한 카페들을 리스트로 반환
     * - 예: GET /api/cafes/category/2
     */
    @Operation(summary = "카테고리별 카페 목록 조회", description = "카테고리 ID에 해당하는 카페 목록을 조회합니다.")
    @GetMapping("/category/{categoryId}")
    public List<Cafe> getCafeByCategory(@PathVariable("categoryId") Long categoryId) {
        return categoryCafeRepository.findCafesByCategoryId(categoryId); // 쿼리 메서드 호출
    }
}
