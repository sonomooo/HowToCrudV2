package Develop.spartaDevelop.user;

import Develop.spartaDevelop.user.dto.UserRequest;
import Develop.spartaDevelop.user.dto.UserResponse;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User , Id> {


    UserResponse createUser(UserRequest request);

    User findAllByuserName(String userName);

    Optional<User> findByUsername(String userName);


    void deleteNyAuthorName(String authorName);

    Optional<User> findById(long l);

    Optional<Object> findByEmail(@NotBlank(message = "이메일은 필수 입력 항목입니다.") @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "유효한 이메일 형식이 아닙니다.") String email);

}
