package uz.meta.domains.auth;

import lombok.*;
import uz.meta.domains.Auditable;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSessionEntity extends Auditable {
    private Long userID;
    private LocalDateTime firstLoggedInTime;
    private LocalDateTime lastLoggedInTime;
    private boolean deletedAccount;

    @Builder(builderMethodName = "childBuilder")
    public UserSessionEntity(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, int deleted, Long userID, LocalDateTime firstLoggedInTime, LocalDateTime lastLoggedInTime, boolean deletedAccount) {
        super(id, createdAt, updatedAt, deleted);
        this.userID = userID;
        this.firstLoggedInTime = firstLoggedInTime;
        this.lastLoggedInTime = lastLoggedInTime;
        this.deletedAccount = deletedAccount;
    }
}
