package quartet.cafe.application.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import quartet.cafe.domain.user.model.SocialType;
import quartet.cafe.domain.user.model.User;
import quartet.cafe.domain.user.repository.UserRepository;
import quartet.cafe.infrastructure.security.JwtTokenProvider;
import quartet.cafe.presentation.auth.dto.SignupRequest;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public String loginLocal(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
            .filter(u -> u.getSocialType() == SocialType.NONE)
            .orElseThrow(() -> new IllegalArgumentException("자체 로그인 계정이 존재하지 않습니다."));

        if (user.getPassword() == null || !passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return jwtTokenProvider.createToken(user.getId(), user.getSocialType());
    }

    public void signup(SignupRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = User.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .name(request.getName())
                .phone(request.getPhone())
                .profileUrl(request.getProfileUrl())
                .content(request.getContent())
                .socialType(SocialType.NONE)
                .build();

        userRepository.save(user);
    }

}
