package quartet.cafe.application.oauth.kako;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quartet.cafe.infrastructure.security.JwtTokenProvider;
import quartet.cafe.domain.user.model.SocialType;
import quartet.cafe.domain.user.model.User;
import quartet.cafe.domain.user.repository.UserRepository;
import quartet.cafe.infrastructure.oauth.kakao.KakaoApiClient;
import quartet.cafe.infrastructure.oauth.kakao.dto.KakaoTokenResponse;
import quartet.cafe.infrastructure.oauth.kakao.dto.KakaoUserInfo;

@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoOauthService {

    private final KakaoApiClient kakaoApiClient;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public String kakaoLogin(String code) {
        log.info("카카오 로그인 시도: code={}", code);

        // 1. 카카오 토큰 요청
        KakaoTokenResponse tokenResponse = kakaoApiClient.getToken(code);
        log.info("카카오 액세스 토큰 응답 받음: {}", tokenResponse);

        // 2. 카카오 사용자 정보 요청
        KakaoUserInfo userInfo = kakaoApiClient.getUserInfo(tokenResponse.getAccessToken());
        log.info("카카오 사용자 정보 응답: {}", userInfo);

        // 3. 사용자 DB 조회 및 신규 저장
        User user = userRepository.findByKakaoId(userInfo.getKakaoId())
                .orElseGet(() -> {
                    log.info("신규 사용자 저장: kakaoId={}, email={}", userInfo.getKakaoId(), userInfo.getEmail());
                    return userRepository.save(
                            User.builder()
                                    .kakaoId(userInfo.getKakaoId())
                                    .email(userInfo.getEmail())
                                    .socialType(SocialType.KAKAO)
                                    .build()
                    );
                });

        // 4. JWT 토큰 생성 및 반환
        String token = jwtTokenProvider.createToken(user.getId(), user.getSocialType());
        log.info("JWT 토큰 생성 완료: userId={}, token={}", user.getId(), token);

        return token;
    }
}

