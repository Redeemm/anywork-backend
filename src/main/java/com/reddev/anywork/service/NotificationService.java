package com.reddev.anywork.service;


import com.reddev.anywork.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
        private final NotificationRepository notificationRepository;
        private final MessageService messageService;


}
