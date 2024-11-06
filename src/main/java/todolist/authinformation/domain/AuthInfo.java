package todolist.authinformation.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name = "authinformation")
public class AuthInfo {
    
    @Id
    @Column(name = "auth_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auth_id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private Long user_id;
    private LocalDateTime token_create_time;
    private LocalDateTime token_expire_time;

    @PrePersist
    public void prePersist()
    {
        this.token_create_time = LocalDateTime.now();
        this.token_expire_time = this.token_create_time.plusHours(1);
    }
}
