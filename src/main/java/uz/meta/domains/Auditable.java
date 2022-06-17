package uz.meta.domains;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Auditable {
    protected Long id;
    protected LocalDateTime createdAt = LocalDateTime.now();
    protected LocalDateTime updatedAt;
    protected int deleted;
}
