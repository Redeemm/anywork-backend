package com.reddev.anywork.model.notification;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(
        name = "notification"
)

public class Notification {
    @Id
    @GeneratedValue

    private UUID Id;
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime readAt;

    private NotificationType type;
    private String message;
    private String url;
    private String orderNumber;
    private String img_url;

}
