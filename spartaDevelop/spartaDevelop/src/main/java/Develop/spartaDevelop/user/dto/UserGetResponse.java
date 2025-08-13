package Develop.spartaDevelop.user.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserGetResponse {

    private final Long id;
    private final String userName;
    private final String email;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;

    public UserGetResponse(Long id, LocalDateTime createAt, String userName, String email, LocalDateTime modifiedAt) {
        this.id = id;
        this.createAt = createAt;
        this.userName = userName;
        this.email = email;
        this.modifiedAt = modifiedAt;
    }
}
