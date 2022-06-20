package uz.meta.domains.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.*;
import uz.meta.domains.Auditable;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserSessionEntity extends Auditable {
    @Column(nullable = false)
    private Long userID;

    @Column(nullable = false)
    private Timestamp firstLoggedInTime;

    @Column(nullable = false)
    private Timestamp lastLoggedInTime;

    @Column(columnDefinition = "smallint default 0")
    @Convert(converter = org.hibernate.type.NumericBooleanConverter.class)
    private boolean deletedAccount;
}
