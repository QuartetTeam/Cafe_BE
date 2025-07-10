package quartet.cafe.domain.ownercontent.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import quartet.cafe.domain.ownercontent.dto.OwnerContentResponse;
import quartet.cafe.domain.ownercontent.service.OwnerContentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/owner-content")
public class OwnerContentController {

    private final OwnerContentService ownerContentService;

    // 전체 조회
    @GetMapping
    public List<OwnerContentResponse> getAllOwnerContents() {
        return ownerContentService.getAllOwnerContents();
    }

    // 상세 조회
    @GetMapping("/{id}")
    public OwnerContentResponse getOwnerContent(@PathVariable Long id) {
        return ownerContentService.getOwnerContentById(id);
    }
}
