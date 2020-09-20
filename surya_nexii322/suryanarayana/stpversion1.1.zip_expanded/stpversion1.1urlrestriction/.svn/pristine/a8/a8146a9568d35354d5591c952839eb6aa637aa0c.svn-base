package com.nexiilabs.stp.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
	@Autowired
	NotificationRepository notificationRepository;

	@Override
	public NotificationListResponseDTO getNotificationList(int id) {
		return notificationRepository.getNotificationList(id);
	}
}
