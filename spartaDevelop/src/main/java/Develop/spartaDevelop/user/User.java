package Develop.spartaDevelop.user;

import Develop.spartaDevelop.calendar.dto.baseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class User extends baseEntity {

    @Id
    Long id;

    String userName;
    String email;
    String password;

    public User(String email, Long id, String password, String userName) {
        this.email = email;
        this.id = id;
        this.password = password;
        this.userName = userName;
    }

    public void upDateUser(String userName, String email){
        this.userName = userName;
        this.email = email;
    }
}
