package uz.meta.domains.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import uz.meta.domains.Auditable;
import uz.meta.enums.UserRole;
import uz.meta.enums.UserStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserEntity extends Auditable {
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, columnDefinition = "default ACTIVE")
    private UserStatus userStatus;

    @Column
    private UserRole userRole;

    @Column(nullable = false)
    private String language;
}
