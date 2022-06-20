package uz.meta.dto.auth;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDTO {
    private String username;
    private String password;

    @SerializedName(value = "employee")
    private EmployeeCreateDTO employeeCreateDTO;
}
