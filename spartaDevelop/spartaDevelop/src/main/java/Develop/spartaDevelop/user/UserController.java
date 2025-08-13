package Develop.spartaDevelop.user;

import Develop.spartaDevelop.calendar.dto.CalendarRequest;
import Develop.spartaDevelop.calendar.dto.CalendarUpdateRequest;
import Develop.spartaDevelop.user.dto.UserGetResponse;
import Develop.spartaDevelop.user.dto.UserRequest;
import Develop.spartaDevelop.user.dto.UserResponse;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
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

    @PostMapping("/update")
    public ResponseEntity<UserGetResponse> update(
            @PathVariable String userName,
            @RequestBody UserRequest request
            ){
        return ResponseEntity.ok(userService.update(userName, request));
    }

    @GetMapping("/delete")
    public void delete(
            @PathVariable String authorName
            ){
        userService.deleteUserByName(authorName);
    }
}
