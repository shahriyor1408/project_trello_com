package uz.meta.dto.auth;

import lombok.*;
import uz.meta.dto.GenericDTO;

import java.sql.Timestamp;

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
    private EmployeeDTO employeeDTO;

    @Builder(builderMethodName = "childBuilder")
    public UserDTO(Long id, Timestamp createdAt, Long createdBy, Timestamp updatedAt, Long updatedBy, boolean deleted, String username, String password, String status, String role, String language, EmployeeDTO employeeDTO) {
        super(id, createdAt, createdBy, updatedAt, updatedBy, deleted);
        this.username = username;
        this.password = password;
        this.status = status;
        this.role = role;
        this.language = language;
        this.employeeDTO = employeeDTO;
    }
}
