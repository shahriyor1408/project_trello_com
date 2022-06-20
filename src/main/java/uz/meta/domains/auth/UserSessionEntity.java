package uz.meta.domains.auth;

import jakarta.persistence.*;
import lombok.*;
import uz.meta.domains.Auditable;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_session")
@Table(schema = "hr")
public class UserSessionEntity {
    @Id
    @SequenceGenerator(name = "user_session_seq", schema = "hr",allocationSize = 1)
    private Long id;

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
