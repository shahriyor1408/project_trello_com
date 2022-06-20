package uz.meta.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "comment")
@Table(schema = "task")
public class CommentEntity extends Auditable {
    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "task_id")
    private String taskId;
}
