package Develop.spartaDevelop.user;

import Develop.spartaDevelop.user.dto.LoginRequest;
import Develop.spartaDevelop.user.dto.UserGetResponse;
import Develop.spartaDevelop.user.dto.UserRequest;
import Develop.spartaDevelop.user.dto.UserResponse;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class UserService {

    UserRepository userRepository;

    @Transactional
    public UserResponse create(UserRequest request) {
       return userRepository.createUser(request);
    }

    @Transactional
    public List<User> findAll(String userName) {
        List<User> userList = new ArrayList<>();

        userList.add(userRepository.findAllByuserName(userName));

        return userList;
    }

    @Transactional
    public UserGetResponse update(String userName, UserRequest request) {
          User user = userRepository.findByUsername(userName).orElseThrow(
                  ()-> new IllegalArgumentException("해당 이름을 가진 정보가 없습니다.")
          );
          user.upDateUser(
                  request.getUserName(),
                  request.getEmail()
          );

          return new UserGetResponse(
                  user.getId(),
                  user.getCreatedAt(),
                  user.getUserName(),
                  user.getEmail(),
                  user.getModifiedAt()
          );
    }

    public void deleteUserByName(String authorName) {
        userRepository.deleteNyAuthorName(authorName);
    }

    @Transactional
    public void signUp() {
        userRepository.save(new User());
    }

    @Transactional(readOnly = true)
    public User findOne(){
        return userRepository.findById(1L).orElseThrow(
                () -> new IllegalArgumentException("user not found")
        );
    }

    @Transactional(readOnly = true)
    public Long handleLogin(LoginRequest dto) {
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(
                () -> new InvalidCredentialException("해당 이메일이 존재하지 않습니다."));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new InvalidCredentialException("비밀번호가 일치하지 않습니다.");
        }
        return user.getId();
    }
}
