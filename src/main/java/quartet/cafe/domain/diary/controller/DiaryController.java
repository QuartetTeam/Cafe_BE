package quartet.cafe.domain.diary.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quartet.cafe.domain.diary.dto.DiaryRequest;
import quartet.cafe.domain.diary.dto.DiaryResponse;
import quartet.cafe.domain.diary.service.DiaryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diaries")
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping
    public ResponseEntity<String> createDiary(@RequestBody DiaryRequest request) {
        diaryService.createDiary(request);
        return ResponseEntity.ok("카페 일지 등록 완료.");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DiaryResponse>> getDiariesByUser(@PathVariable Long userId) {
        List<DiaryResponse> diaries = diaryService.getDiariesByUser(userId);
        return ResponseEntity.ok(diaries);
    }
}