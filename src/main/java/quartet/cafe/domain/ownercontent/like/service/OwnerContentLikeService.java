package quartet.cafe.domain.ownercontent.like.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quartet.cafe.domain.ownercontent.like.model.OwnerContentLike;
import quartet.cafe.domain.ownercontent.like.repository.OwnerContentLikeRepository;
import quartet.cafe.domain.ownercontent.model.OwnerContent;
import quartet.cafe.domain.ownercontent.repository.OwnerContentRepository;
import quartet.cafe.domain.user.model.User;
import quartet.cafe.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class OwnerContentLikeService {

    private final OwnerContentLikeRepository likeRepository;
    private final UserRepository userRepository;
    private final OwnerContentRepository ownerContentRepository;

    //좋아요 토글 (등록, 취소)
    @Transactional
    public boolean toggleLike(Long userId, Long ownerContentId) {
        //유저, 게시글 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
        OwnerContent content = ownerContentRepository.findById(ownerContentId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        //기존 좋아요 여부 확인
        return likeRepository.findByUserIdAndOwnerContentId(userId, ownerContentId)
                .map(existingLike -> {
                    likeRepository.delete(existingLike); //이미 눌렀으면 삭제하기
                    return false; //false = 좋아요 취소됨
                })
                .orElseGet(() -> {
                    likeRepository.save(new OwnerContentLike(user, content)); //없으면 저장하기
                    return true; //true = 좋아요 등록됨
                });
    }

    //게시글 좋아요 수 조회
    public long getLikeCount(Long ownerContentId) {
        return likeRepository.countByOwnerContentId(ownerContentId);
    }
}
