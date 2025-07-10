package quartet.cafe.domain.diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quartet.cafe.domain.diary.model.Diary;
import quartet.cafe.domain.user.model.User;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

    // 사용자 객체를 이용한 조회 (User 엔티티가 연관관계에 있는 경우 권장)
    List<Diary> findByUser(User user);

    // 또는 userId를 통한 조회도 가능
    List<Diary> findByUserId(Long userId);
}
