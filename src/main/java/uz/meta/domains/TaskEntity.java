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
@Entity(name = "task")
@Table(schema = "task")
public class TaskEntity extends Auditable {
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "board_column_id", nullable = false)
    private Long boardColumnId;

    @Column(name = "order", nullable = false)
    private Long order;
}
