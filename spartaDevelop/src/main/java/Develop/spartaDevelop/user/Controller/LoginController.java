package Develop.spartaDevelop.user.Controller;

import Develop.spartaDevelop.user.UserService;
import Develop.spartaDevelop.user.dto.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService; // final로 선언해서 하나의 인스턴스를 생성

    @PostMapping("/login")
    public ResponseEntity<String> login // ResponseEntity로 HTTP응답을 객체로서 반환할수 있도록 도와
            (@RequestBody LoginRequest dto, // @RequestBody Body로 메세지를 요청해서 메세지에 값을 담을 수 있도록 함
                                        HttpServletRequest request  // HttpServletRequest 객체를 활용해서 웹 상 request를 받음
    ) {
        Long userId = userService.handleLogin(dto); // 해당 회원의 이메일과 비밀번호를 검증하는 메서드

                // 검증 통과시 HttpServletRequest의 객체인 request를 활용해서 세션을 얻어서
                // HttpSession 타입의 session 변수를 생성 후에 이 session에 attribute를 지정해서 이름과 userId를 받아서 return
        HttpSession session = request.getSession(); // Http
        session.setAttribute("Login_user", userId);


        return ResponseEntity.ok("ok");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("로그아웃 성공");
    }
}
