package com.nexiilabs.stp.resource;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class FileViewerRepositoryImpl implements FileViewerRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public FileViewerModel getPOfile(Integer fileId, int userId) {
		FileViewerModel fileViewerModel = null;
		String hql = "select su.file_name,su.upload_path from stp_po_upload su where su.po_upload_id=" + fileId
				+ " and su.created_by " + "in(SELECT user_id FROM stp_user WHERE reporting_hierarchy LIKE '%," + userId
				+ ",%'" + " or reporting_hierarchy like '" + userId + ",%' or reporting_hierarchy like '%," + userId
				+ "' or reporting_hierarchy like '" + userId + "' and delete_status=0)";
		List<Object[]> pofile = entityManager.createNativeQuery(hql).getResultList();
		for (Object[] obj : pofile) {
			fileViewerModel = new FileViewerModel();
			fileViewerModel.setFileName(String.valueOf(obj[0]));
			fileViewerModel.setFilePath(String.valueOf(obj[1]));
		}
		return fileViewerModel;
	}

	@Override
	public FileViewerModel getSOWfile(Integer fileId, int userId) {
		FileViewerModel fileViewerModel = null;
		String hql = "select su.file_name,su.upload_path from stp_sow_upload su where su.sow_upload_id=" + fileId
				+ " and su.created_by " + "in(SELECT user_id FROM stp_user WHERE reporting_hierarchy LIKE '%," + userId
				+ ",%'" + " or reporting_hierarchy like '" + userId + ",%' or reporting_hierarchy like '%," + userId
				+ "' or reporting_hierarchy like '" + userId + "' and delete_status=0)";
		List<Object[]> pofile = entityManager.createNativeQuery(hql).getResultList();
		for (Object[] obj : pofile) {
			fileViewerModel = new FileViewerModel();
			fileViewerModel.setFileName(String.valueOf(obj[0]));
			fileViewerModel.setFilePath(String.valueOf(obj[1]));
		}
		return fileViewerModel;
	}

	@Override
	public FileViewerModel getAgreementfile(Integer fileId, int userId) {
		FileViewerModel fileViewerModel = null;
		String hql = "SELECT su.file_name,su.upload_path from stp_customer_upload su where su.customer_upload_id=" + fileId
				+ " and su.created_by " + "in(SELECT user_id FROM stp_user WHERE reporting_hierarchy LIKE '%," + userId
				+ ",%'" + " or reporting_hierarchy like '" + userId + ",%' or reporting_hierarchy like '%," + userId
				+ "' or reporting_hierarchy like '" + userId + "' and delete_status=0)";
		List<Object[]> pofile = entityManager.createNativeQuery(hql).getResultList();
		for (Object[] obj : pofile) {
			fileViewerModel = new FileViewerModel();
			fileViewerModel.setFileName(String.valueOf(obj[0]));
			fileViewerModel.setFilePath(String.valueOf(obj[1]));
		}
		return fileViewerModel;
	}
}
