package quartet.cafe.presentation.auth;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quartet.cafe.application.auth.AuthService;
import quartet.cafe.presentation.auth.dto.LoginRequest;
import quartet.cafe.presentation.auth.dto.SignupRequest;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "자체 로그인", description = "소셜 로그인이 아닌 자체 로그인을 진행합니다.")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String jwt = authService.loginLocal(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(jwt);
    }

    @Operation(summary = "자체 회원가입", description = "자체 회원가입을 진행합니다.")
    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody SignupRequest request) {
        authService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
