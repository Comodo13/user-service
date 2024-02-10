package max.userservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tweets")
public class Tweet {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_id")
    @OneToOne(mappedBy = "id", fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private Long userId;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


}
