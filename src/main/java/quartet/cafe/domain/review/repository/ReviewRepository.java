package quartet.cafe.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import quartet.cafe.domain.review.model.Review;

import java.math.BigDecimal;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    //특정 카페 ID로 별점 평균을 계산해주는 기능
    @Query("SELECT AVG(r.score) FROM Review r WHERE r.cafe.id = :cafeId")
    BigDecimal findAverageScoreByCafeId(@Param("cafeId") Long cafeId);
    
    List<Review> findByCafeId(Long cafeId); // 특정 카페에 달린 리뷰 전체 조회
}
