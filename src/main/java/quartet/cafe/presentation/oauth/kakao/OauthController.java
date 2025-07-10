package quartet.cafe.presentation.oauth.kakao;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quartet.cafe.application.oauth.kako.KakaoOauthService;

@RestController
@RequestMapping("/api/oauth")
@RequiredArgsConstructor
public class OauthController {

    private final KakaoOauthService kakaoOauthService;

    @Value("${oauth.kakao.client-id}")
    private String kakaoClientId;

    @Value("${oauth.kakao.redirect-uri}")
    private String redirectUri;

    @Operation(summary = "카카오 로그인", description = "인가코드로 카카오 로그인을 진행합니다.")
    @PostMapping("/kakao")
    public ResponseEntity<String> kakaoLogin(@RequestParam("code") String code) {
        String jwt = kakaoOauthService.kakaoLogin(code);
        return ResponseEntity.ok(jwt);
    }

    @Operation(summary = "카카오 인가코드 URL 생성", description = "Swagger에서 테스트용 인가코드를 받는 임시 API 입니다.")
    @GetMapping("/kakao/authorize-url")
    public String getKakaoAuthorizeUrl() {
        return "https://kauth.kakao.com/oauth/authorize" +
                "?client_id=" + kakaoClientId +
                "&redirect_uri=" + redirectUri +
                "&response_type=code";
    }
}
