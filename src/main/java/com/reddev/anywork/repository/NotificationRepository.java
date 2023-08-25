package com.reddev.anywork.repository;



import com.reddev.anywork.model.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface NotificationRepository extends JpaRepository<Notification, UUID> {
//    List<Object> findAllByCompanyId(UUID id);

}
