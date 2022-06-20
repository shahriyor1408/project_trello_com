package uz.meta.domains.auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id",nullable = false)
    private Long userId;

    @Column(name = "full_name",nullable = false)
    private String fullName;

    @Column(name = "phone_number",unique = true,nullable = false)
    private String phoneNumber;

    @Column(name = "email",nullable = false)
    private String email;
}
