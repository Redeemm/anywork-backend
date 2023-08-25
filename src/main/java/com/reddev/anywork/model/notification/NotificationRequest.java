package com.reddev.anywork.model.notification;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {
    private NotificationType type;
    private String token;
    private String phoneNumber;
    private String name;
    private String number;
    private String orderNumber;
    private String title;
    private String message;
    private String url;
    private UUID cartId;

}
