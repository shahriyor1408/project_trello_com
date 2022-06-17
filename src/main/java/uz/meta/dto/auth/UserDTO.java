package uz.meta.dto.auth;

import lombok.*;
import uz.meta.dto.GenericDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends GenericDTO {
    private String username;
    private String password;
    private String status;
    private String role;
    private String language;

    @Builder(builderMethodName = "childBuilder")
    public UserDTO(Long id, String username, String password, String status, String role, String language) {
        super(id);
        this.username = username;
        this.password = password;
        this.status = status;
        this.role = role;
        this.language = language;
    }

}
