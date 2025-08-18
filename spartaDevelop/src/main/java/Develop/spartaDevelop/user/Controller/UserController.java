package Develop.spartaDevelop.user.Controller;

import Develop.spartaDevelop.user.User;
import Develop.spartaDevelop.user.UserService;
import Develop.spartaDevelop.user.dto.UserGetResponse;
import Develop.spartaDevelop.user.dto.UserRequest;
import Develop.spartaDevelop.user.dto.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@NoArgsConstructor
@RequestMapping("/user")
public class UserController {

    UserService userService;

    @PostMapping("/create")
    public UserResponse create(UserRequest request
    ){
        return userService.create(request);
    }

    @GetMapping("/read")
    public ResponseEntity<List<User>> readCalendar(
            @RequestParam(required = false) String userName
    ){
        return ResponseEntity.ok(userService.findAll(userName));
    }

    @PostMapping("/update/{userName}")
    public ResponseEntity<UserGetResponse> update(
            @PathVariable String userName,
            @RequestBody UserRequest request
            ){
        return ResponseEntity.ok(userService.update(userName, request));
    }

    @GetMapping("/delete/{authorName}")
    public void delete(
            @PathVariable String authorName
            ){
        userService.deleteUserByName(authorName);
    }

    @PostMapping("/signup")
    public void singUp(){
        userService.signUp();
    }

    @PostMapping("/login")
    public void login(@RequestBody HttpServletRequest request){

        User user = userService.findOne();

        HttpSession session = request.getSession();
        session.setAttribute("LOGIN_USER", user.getId());
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null){
            session.invalidate();
        }
    }
}
