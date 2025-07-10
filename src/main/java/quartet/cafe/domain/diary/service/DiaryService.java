package quartet.cafe.domain.diary.service;

import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quartet.cafe.domain.cafe.model.Cafe;
import quartet.cafe.domain.cafe.repository.CafeRepository;
import quartet.cafe.domain.diary.dto.DiaryRequest;
import quartet.cafe.domain.diary.dto.DiaryResponse;
import quartet.cafe.domain.diary.model.Diary;
import quartet.cafe.domain.diary.repository.DiaryRepository;
import quartet.cafe.domain.user.model.User;
import quartet.cafe.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final CafeRepository cafeRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createDiary(DiaryRequest request) {
        Cafe cafe = cafeRepository.findById(request.getCafeId())
                .orElseThrow(() -> new IllegalArgumentException("해당 카페가 존재하지 않습니다."));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        Diary diary = Diary.builder()
                .imageUrl(request.getImageUrl())
                .content(request.getContent())
                .cafe(cafe)
                .user(user)
                .build();

        diaryRepository.save(diary);
    }
    // ✅ 사용자별 일지 목록 조회
    public List<DiaryResponse> getDiariesByUser(Long userId) {
        // 사용자 존재 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        // 해당 사용자의 일지 목록 조회
        List<Diary> diaries = diaryRepository.findByUser(user);

        // Diary -> DiaryResponse로 변환
        return diaries.stream()
                .map(diary -> DiaryResponse.builder()
                        .id(diary.getId())
                        .imageUrl(diary.getImageUrl())
                        .content(diary.getContent())
                        .cafeId(diary.getCafe().getId())
                        .userId(user.getId())
                        .build())
                .collect(Collectors.toList());
    }
}
