package uz.meta.domains.auth;

import lombok.*;
import uz.meta.domains.Auditable;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends Auditable {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String language;

    @Builder(builderMethodName = "childBuilder")
    public UserEntity(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, int deleted, String username, String password, String email, String firstName, String lastName, String language) {
        super(id, createdAt, updatedAt, deleted);
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.language = language;
    }
}
