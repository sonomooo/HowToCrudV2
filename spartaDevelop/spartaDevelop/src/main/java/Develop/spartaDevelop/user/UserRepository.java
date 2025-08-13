package Develop.spartaDevelop.user;

import Develop.spartaDevelop.user.dto.UserRequest;
import Develop.spartaDevelop.user.dto.UserResponse;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User , Id> {


    UserResponse createUser(UserRequest request);

    User findAllByuserName(String userName);

    Optional<User> findByUsername(String userName);


    void deleteNyAuthorName(String authorName);
}
