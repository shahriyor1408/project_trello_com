package uz.meta.domains.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import uz.meta.domains.Auditable;
import uz.meta.enums.UserRole;
import uz.meta.enums.UserStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
@Table(schema = "hr")
public class UserEntity extends Auditable {
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status", nullable = false, columnDefinition = "default ACTIVE")
    private UserStatus userStatus;

    @Column(name = "role",nullable = false)
    private UserRole userRole;

    @Column(name = "language", nullable = false)
    private String language;
}
