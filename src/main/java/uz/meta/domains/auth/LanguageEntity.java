package uz.meta.domains.auth;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LanguageEntity {
    private Long id;
    private String  code;
    private String name;
}
