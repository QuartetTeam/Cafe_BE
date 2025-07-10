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

import org.springframework.web.bind.annotation.*;
import quartet.cafe.domain.cafe.repository.CategoryCafeRepository;
import quartet.cafe.domain.category.model.CategoryCafe;

import org.springframework.http.ResponseEntity;
import quartet.cafe.domain.cafe.model.Cafe;
import quartet.cafe.domain.cafe.repository.CafeRepository;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/cafes")
public class CafeController {
    //private final CafeRepository cafeRepository;

    private final CategoryCafeRepository categoryCafeRepository;

    public CafeController(CategoryCafeRepository categoryCafeRepository) {
        this.categoryCafeRepository = categoryCafeRepository;
    }

    @GetMapping("/category/{categoryId}")
    public List<Cafe> getCafe(@PathVariable("categoryId") Long categoryId) {
        return categoryCafeRepository.findCafesByCategoryId(categoryId);
    }
}
