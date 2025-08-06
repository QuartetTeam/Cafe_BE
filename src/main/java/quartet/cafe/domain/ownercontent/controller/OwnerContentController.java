package quartet.cafe.domain.ownercontent.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quartet.cafe.domain.ownercontent.dto.OwnerContentRequest;
import quartet.cafe.domain.ownercontent.dto.OwnerContentResponse;
import quartet.cafe.domain.ownercontent.service.OwnerContentService;

import java.util.List;

@RestController // 이 클래스가 REST API 요청을 처리하는 컨트롤러임을 명시함
@RequiredArgsConstructor //이 컨트롤러 내 모든 메서드는 /api/owner-content 경로 아래에 매핑됨
@RequestMapping("/api/owner-content") //final로 선언된 OwnerContentService를 생성자 주입해줌
public class OwnerContentController {

    private final OwnerContentService ownerContentService;

    // 특정 카페의 게시글 목록 조회 (최신순으로 6개 페이지네이션)
    @Operation(summary = "특정 카페 사장님 게시글 목록 조회", description = "카페 ID에 해당하는 사장님의 게시글을 최신순으로 6개씩 조회합니다.")
    @GetMapping("/cafe/{cafeId}")
    public List<OwnerContentResponse> getOwnerContentsByCafe(
            @PathVariable Long cafeId,
            @PageableDefault(size = 6, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        return ownerContentService.getOwnerContentsByCafeId(cafeId, pageable);
    }

    // 게시글 ID로 1개씩 상세 조회
    @Operation(summary = "사장님 게시글 상세 조회", description = "게시글 ID로 특정 사장님 게시글을 조회합니다.")
    @GetMapping("/{id}")
    public OwnerContentResponse getOwnerContent(@PathVariable Long id) {
        return ownerContentService.getOwnerContentDetail(id);
    }

    // 게시글 등록
    @Operation(summary = "사장님 게시글 등록", description = "사장님이 카페 소개글을 작성해서 등록")
    @PostMapping
    public ResponseEntity<OwnerContentResponse> createOwnerContent(@RequestBody OwnerContentRequest request) {
        OwnerContentResponse response = ownerContentService.createOwnerContent(request);
        return ResponseEntity.ok(response);
    }
    // 요청 본문(JSON)으로 사장님 게시글 정보를 받아 DB에 등록
    //등록된 결과를 OwnerContentResponse로 응답
    //ResponseEntity로 감싸서 추후 HTTP 상태 코드도 제어 가능

}
