package uz.meta.dto.auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDTO {
    private String firstName;
    private String lastName;
    private String phone;
    private String language;
}
