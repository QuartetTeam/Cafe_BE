package quartet.cafe.infrastructure.oauth.kakao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import quartet.cafe.infrastructure.oauth.kakao.dto.KakaoTokenResponse;
import quartet.cafe.infrastructure.oauth.kakao.dto.KakaoUserInfo;

@Component
@RequiredArgsConstructor
@Slf4j
public class KakaoApiClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${oauth.kakao.client-id}")
    private String clientId;

    @Value("${oauth.kakao.redirect-uri}")
    private String redirectUri;

    @Value("${oauth.kakao.token-uri}")
    private String tokenUri;

    @Value("${oauth.kakao.user-info-uri}")
    private String userInfoUri;

    public KakaoTokenResponse getToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<KakaoTokenResponse> response = restTemplate.postForEntity(tokenUri, request, KakaoTokenResponse.class);

        return response.getBody();
    }

    public KakaoUserInfo getUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<JsonNode> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                request,
                JsonNode.class
        );

        JsonNode kakaoResponse = response.getBody();

        // 여기서 로그 찍기
        log.info("카카오 사용자 정보 응답: {}", kakaoResponse.toPrettyString());

        // 이후 파싱해서 사용자 정보 추출
        String kakaoId = kakaoResponse.get("id").asText();
        String email = kakaoResponse.get("kakao_account").get("email").asText();

        return new KakaoUserInfo(kakaoId, email);
    }

    private JsonNode parseJson(String body) {
        try {
            return objectMapper.readTree(body);
        } catch (Exception e) {
            throw new RuntimeException("카카오 사용자 정보 파싱 실패", e);
        }
    }
}
