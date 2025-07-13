package quartet.cafe.domain.cafe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quartet.cafe.domain.cafe.dto.CafeRequest;
import quartet.cafe.domain.cafe.model.Cafe;
import quartet.cafe.domain.cafe.repository.CafeRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/admin/cafes")
@RequiredArgsConstructor
public class CafeAdminController {

    private final CafeRepository cafeRepository;

    @PostMapping
    public ResponseEntity<Long> createCafe(@RequestBody CafeRequest request) {
        Cafe cafe = Cafe.builder()
                .name(request.getName())
                .imageUrl(request.getImageUrl())
                .introduction(request.getIntroduction())
                .postcode(request.getPostcode())
                .score(request.getScore() != null ? request.getScore() : BigDecimal.ZERO)
                .startAt(LocalDateTime.now())
                .endAt(LocalDateTime.now().plusHours(12))  // 임시 영업시간
                .owner(null)  // Owner는 null로 둠 (필요 시 나중에 처리)
                .build();

        Cafe savedCafe = cafeRepository.save(cafe);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCafe.getId());
    }
}
