package com.jfok.cfcmms.logic;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.hibernate.system.attachment._Attachment;
import com.jfok.cfcmms.service.SystemInfoService;
import com.jfok.cfcmms.share.grid.GridFilterData;

@Service
public class _AttachmentLogic extends BaseOperateLogic<_Attachment> {

	@Resource
	private SystemBaseDAO systemBaseDAO;

	/**
	 * 根据模块id和主键号取得 附件的个数
	 * 
	 * @param moduleId
	 * @param keyValue
	 * @return
	 */
	public Integer getAttachmentCount(String moduleId, String keyValue) {
	Session session = systemBaseDAO.getSessionFactory().openSession();
	Integer maxOrder = 0;
	try {
		Criteria criteria = session.createCriteria(_Attachment.class);
		criteria.add(Restrictions.eq(_Attachment.MODULEID, moduleId));
		criteria.add(Restrictions.eq(_Attachment.MODULEKEYID, Integer.parseInt(keyValue)));
		criteria.setProjection(Projections.count(_Attachment.MODULEKEYID));
		maxOrder = ((Long) criteria.uniqueResult()).intValue();
	} catch (Exception e) {

	} finally {
		session.close();
	}
	return maxOrder == null ? 0 : maxOrder;
	}

	/**
	 * 在改变了主键过后，如果下面有附件，那么就把id改成新的值
	 * 
	 * @param moduleId
	 * @param oldkey
	 * @param newkey
	 */
	public int changeAttachmentIdkey(String moduleId, String oldkey, String newkey) {
	if (getAttachmentCount(moduleId, oldkey) > 0) {
		Session session = systemBaseDAO.getSessionFactory().getCurrentSession();
		Query query1 = session.createSQLQuery("update _Attachment set tf_moduleIdvalue = :newkey "
			+ " where tf_moduleId=:mid and tf_moduleIdvalue=:oldkey ");
		query1.setParameter("newkey", Integer.parseInt(newkey));
		query1.setParameter("mid", moduleId);
		query1.setParameter("oldkey", Integer.parseInt(oldkey));
		int count = query1.executeUpdate();
		// session.close();
		return count;
	} else
		return 0;
	}

	@Override
	public Map<String, Object> getNewDefultValue(HttpServletRequest request,
		GridFilterData gridFilterData) {
	Map<String, Object> result = new HashMap<String, Object>();
	result.put("tf_moduleId", gridFilterData.getParentModuleFilter().getModuleId());
	result.put("tf_moduleIdvalue", gridFilterData.getParentModuleFilter().getEqualsValue());
	// System.out.print(gridFilterData);
	String moduleId = gridFilterData.getParentModuleFilter().getModuleId();
	String keyValue = gridFilterData.getParentModuleFilter().getEqualsValue();
	Session session = systemBaseDAO.getSessionFactory().openSession();
	Criteria criteria = session.createCriteria(_Attachment.class);
	criteria.add(Restrictions.eq(_Attachment.MODULEID, moduleId));
	criteria.add(Restrictions.eq(_Attachment.MODULEKEYID, Integer.parseInt(keyValue)));
	criteria.setProjection(Projections.max(_Attachment.ATTACHMENTORDER));
	Integer maxOrder = (Integer) criteria.uniqueResult();
	if (maxOrder != null) {
		result.put(_Attachment.ATTACHMENTORDER, maxOrder + 1);
		criteria = session.createCriteria(_Attachment.class);
		criteria.add(Restrictions.eq(_Attachment.MODULEID, moduleId));
		criteria.add(Restrictions.eq(_Attachment.MODULEKEYID, Integer.parseInt(keyValue)));
		criteria.add(Restrictions.eq(_Attachment.ATTACHMENTORDER, maxOrder));
		_Attachment Attachment = (_Attachment) criteria.list().get(0);
		String n = Attachment.getTf_name();
		int pos = n.indexOf("◆");
		if (pos != -1)
		n = n.substring(0, pos);
		result.put(_Attachment.ATTACHMENTNAME, n + "◆" + (maxOrder + 1));
		result.put("_t9502___tf_typeId",
			"" + Attachment.getTf_AttachmentType().getTf_typeId());

		if (Attachment.getTf_AttachmentFileType() != null)
		result.put("_t9503___tf_fileTypeId",
			"" + Attachment.getTf_AttachmentFileType().getTf_fileTypeId());

	} else {
		result.put(_Attachment.ATTACHMENTORDER, 1);
	}
	session.close();

	if (SystemInfoService.getSysteminfo().getTf_AttachmentReduceMode() != null)
		result.put("_t9504___tf_reduceModeId", ""
			+ SystemInfoService.getSysteminfo().getTf_AttachmentReduceMode().getTf_reduceModeId());
	return result;
	}
}
