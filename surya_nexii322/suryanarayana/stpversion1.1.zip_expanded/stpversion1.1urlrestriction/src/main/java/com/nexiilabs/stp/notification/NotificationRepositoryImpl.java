package com.nexiilabs.stp.notification;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nexiilabs.stp.bench.BenchListResponseDTO;
import com.nexiilabs.stp.bench.BenchRepositoryImpl;
import com.nexiilabs.stp.invoice.InvoiceGenerationDaoImpl;
import com.nexiilabs.stp.prospect.FollowupProspectListResponseDTO;
import com.nexiilabs.stp.prospect.ProspectRepositoryImpl;
import com.nexiilabs.stp.resource.EmployeeListResponseDTO;
import com.nexiilabs.stp.resource.EmployeeRepositoryImpl;

@Transactional
@Repository
public class NotificationRepositoryImpl implements NotificationRepository {
	private static final Logger log = LogManager.getLogger(NotificationRepositoryImpl.class);
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	EmployeeRepositoryImpl employeeRepo;
	@Autowired
	InvoiceGenerationDaoImpl invoiceRepo;
	@Autowired
	BenchRepositoryImpl benchRepo;
	@Autowired
	ProspectRepositoryImpl prospectRepositoryImpl;

	@Override
	public NotificationListResponseDTO getNotificationList(int id) {
		NotificationListResponseDTO notificationListResponseDTO=new NotificationListResponseDTO();
		
		List<BenchListResponseDTO> benchList = benchRepo.getBenchResourcesList(0, id);
		List<EmployeeListResponseDTO> resourceList = employeeRepo.getInActiveResourceList(id);
		int userTypeId=-1;
		List<FollowupProspectListResponseDTO> prospectList = prospectRepositoryImpl.getTodayFollowupList(id,userTypeId);
		notificationListResponseDTO.setBenchCount((benchList.size()>0)?benchList.size():0);
		notificationListResponseDTO.setInactiveCount((resourceList.size()>0)?resourceList.size():0);
		notificationListResponseDTO.setInvoiceRequestCount(invoiceRepo.getInvoiceRequestCount());
		notificationListResponseDTO.setProspectCount((prospectList.size()>0)?prospectList.size():0);
		return notificationListResponseDTO;
	}
}
