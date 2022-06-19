package uz.meta.domains.auth;

import lombok.*;
import uz.meta.domains.Auditable;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOTPEntity extends Auditable {
    private String phone;
    private String otp;
    private LocalDateTime expires = LocalDateTime.now().plusMinutes(2);

    @Builder(builderMethodName = "childBuilder")
    public UserOTPEntity(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, int deleted, String phone, String otp, LocalDateTime expires) {
        super(id, createdAt, updatedAt, deleted);
        this.phone = phone;
        this.otp = otp;
        this.expires = expires;
    }

}
