package uz.meta.dto.auth;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeCreateDTO {
    @SerializedName(value = "full_name")
    private String fullName;

    @SerializedName(value = "phone_number")
    private String phoneNumber;
    private String email;
}
