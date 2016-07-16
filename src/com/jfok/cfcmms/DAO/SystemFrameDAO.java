package com.jfok.cfcmms.DAO;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.jfok.cfcmms.hibernate.system.module._ModuleAdditionField;
import com.jfok.cfcmms.hibernate.system.module._ModuleField;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleDetailScheme;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleDetailSchemeField;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleFormScheme;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleFormSchemeGroup;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleFormSchemeGroupField;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleGridScheme;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleGridSchemeGroup;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleGridSchemeGroupField;
import com.jfok.cfcmms.share.TreeNodeRecordChecked;
import com.jfok.cfcmms.share.module.FieldAggregationType;



@Repository
public class SystemFrameDAO {

	@Resource
	private SystemBaseDAO systemBaseDAO;

	/**
	 * 查找某个gridgroup是否包含某个字段,如果包含该字段，那么把checked设置为true,颜色设计为蓝色，
	 * 
	 * 如果是包含在本方案的其他组中，那么颜色设置为红色
	 * 
	 * @param gridGroupId
	 * @param fieldId
	 * @param tag
	 * @return true or false
	 */
	public Boolean isGridGroupHasField(_ModuleGridSchemeGroup group, Integer fieldId,
			TreeNodeRecordChecked node, FieldAggregationType type) {
		for (_ModuleGridSchemeGroupField field : group.getTf_groupFields())
			if (field.getTf_fieldId().equals(fieldId)
					&& (type == null || type.getValue().equals(field.getTf_aggregate()))) {
				node.setText("<span style=\"color:blue;\">" + node.getText() + "</span>");
				node.setChecked(true);
				return true;
			}
		if (isGridSchemeHasField(group, fieldId, node, type))
			node.setText("<span style=\"color:red;\">" + node.getText() + "</span>");
		return false;
	}

	/**
	 * 查找某个gridScheme是否包含某个字段
	 * 
	 * @param gridGroupId
	 * @param fieldId
	 * @param tag
	 * @return true or false
	 */
	public Boolean isGridSchemeHasField(_ModuleGridSchemeGroup group, Integer fieldId,
			TreeNodeRecordChecked node, FieldAggregationType type) {
		for (_ModuleGridSchemeGroup group1 : group.getTf_ModuleGridScheme().getTf_schemeGroups())
			for (_ModuleGridSchemeGroupField field : group1.getTf_groupFields())
				if (field.getTf_fieldId().equals(fieldId)
						&& (type == null || type.getValue().equals(field.getTf_aggregate())))
					return true;
		return false;
	}

	/**
	 * 查找某个gridgroup是否包含某个字段
	 * 
	 * @param gridGroupId
	 * @param fieldId
	 * @param tag
	 * @return true or false
	 */
	@SuppressWarnings("unchecked")
	public Boolean isDetailGroupHasField(Integer detailId, Integer fieldId) {
		List<_ModuleDetailSchemeField> fields = (List<_ModuleDetailSchemeField>) systemBaseDAO
				.findByPropertyWithOtherCondition(_ModuleDetailSchemeField.class,
						"tf_ModuleDetailScheme.tf_detailId", detailId, " tf_ModuleField.tf_fieldId = "
								+ fieldId);
		return fields.size() > 0;
	}

	/**
	 * 查找某个formgroup是否包含某个字段
	 * 
	 * @param formGroupId
	 * @param fieldId
	 * @param tag
	 * @return true or false
	 */
	public Boolean isFormGroupHasField(_ModuleFormSchemeGroup group, Integer fieldId,
			TreeNodeRecordChecked node) {
		for (_ModuleFormSchemeGroupField field : group.getTf_groupFields())
			if (field.getTf_fieldId().equals(fieldId)) {
				node.setText("<span style=\"color:blue;\">" + node.getText() + "</span>");
				node.setChecked(true);
				return true;
			}
		if (isFormSchemeHasField(group, fieldId))
			node.setText("<span style=\"color:red;\">" + node.getText() + "</span>");
		return false;
	}

	/**
	 * 查找某个formScheme是否包含某个字段
	 * 
	 * @param formGroupId
	 * @param fieldId
	 * @param tag
	 * @return true or false
	 */
	public Boolean isFormSchemeHasField(_ModuleFormSchemeGroup group, Integer fieldId) {
		for (_ModuleFormSchemeGroup group1 : group.getTf_ModuleFormScheme().getTf_schemeGroups())
			for (_ModuleFormSchemeGroupField field : group1.getTf_groupFields())
				if (field.getTf_fieldId().equals(fieldId))
					return true;
		return false;
	}

	/**
	 * 查找模块中有没有附加字段的设置
	 * 
	 * @param moduleId
	 * @param fieldId
	 * @param tag
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Boolean isModuleHasAdditionField(String moduleId, Integer fieldId,
			FieldAggregationType mode) {
		String otherCond = "tf_fieldId=" + fieldId.toString() + " and tf_aggregate = '"
				+ mode.getValue() + "' ";

		List<_ModuleAdditionField> additions = (List<_ModuleAdditionField>) systemBaseDAO
				.findByPropertyWithOtherCondition(_ModuleAdditionField.class, "tf_moduleId", moduleId,
						otherCond);
		return additions.size() > 0;
	}

	/**
	 * 新增或删除，模块中附加字段的设置
	 * 
	 * @param moduleId
	 * @param fieldId
	 * @param tag
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Boolean addorDeleteAdditionField(String moduleId, Integer fieldId,
			FieldAggregationType mode, Boolean isSelected) {
		String otherCond = "tf_fieldId=" + fieldId.toString() + " and tf_aggregate = '"
				+ mode.getValue() + "' ";
		List<_ModuleAdditionField> additions = (List<_ModuleAdditionField>) systemBaseDAO
				.findByPropertyWithOtherCondition(_ModuleAdditionField.class, "tf_moduleId", moduleId,
						otherCond);
		if (isSelected) {
			if (additions.size() > 0)
				return true;
			else {
				_ModuleAdditionField field = new _ModuleAdditionField();
				field.setTf_aggregate(mode.getValue());
				field.setTf_moduleId(moduleId);
				field.setTf_fieldId(fieldId);
				systemBaseDAO.save(field);
			}
		} else {
			if (additions.size() == 0)
				return true;
			else
				systemBaseDAO.delete(additions.get(0));
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<_ModuleField> get_ModuleFields(String moduleId) {
		Session session = systemBaseDAO.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(_ModuleField.class);
		Criteria moduleCriteria = criteria.createCriteria("tf_Module");
		moduleCriteria.add(Restrictions.eq("tf_moduleId", moduleId));
		return (List<_ModuleField>) criteria.list();

	}

	/**
	 * 在自动新增一个grid方案时，取得模块的下一个grid方案的序号
	 * 
	 * @param moduleId
	 * @return
	 */
	public Integer getNextGridSchemeOrder(String moduleId) {
		Session session = systemBaseDAO.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(_ModuleGridScheme.class);
		Criteria moduleCriteria = criteria.createCriteria("tf_Module");
		moduleCriteria.add(Restrictions.eq("tf_moduleId", moduleId));
		criteria.setProjection(Projections.max("tf_schemeOrder"));
		List<?> results = criteria.list();
		if (results.get(0) == null)
			return 1;
		else
			return (Integer) results.get(0) + 1;
	}

	/**
	 * 取得一个模块的字段的最大序号
	 * 
	 * @param moduleId
	 * @return
	 */
	public Integer getMaxModuleFieldId(String moduleId) {
		Session session = systemBaseDAO.getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(_ModuleField.class);
			Criteria moduleCriteria = criteria.createCriteria("tf_Module");
			moduleCriteria.add(Restrictions.eq("tf_moduleId", moduleId));
			criteria.setProjection(Projections.max("tf_fieldId"));
			List<?> results = criteria.list();
			if (results.get(0) == null)
				return Integer.parseInt(moduleId) * 10000 + 10;
			else
				return (Integer) results.get(0) + 10;
		} finally {
			session.close();
		}
	}

	/**
	 * 在自动新增一个Form方案时，取得模块的下一个grid方案的序号
	 * 
	 * @param moduleId
	 * @return
	 */
	public Integer getNextFormSchemeOrder(String moduleId) {
		Session session = systemBaseDAO.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(_ModuleFormScheme.class);
		Criteria moduleCriteria = criteria.createCriteria("tf_Module");
		moduleCriteria.add(Restrictions.eq("tf_moduleId", moduleId));
		criteria.setProjection(Projections.max("tf_schemeOrder"));
		List<?> results = criteria.list();
		if (results.get(0) == null)
			return 1;
		else
			return (Integer) results.get(0) + 1;
	}

	/**
	 * 用户选择了 grid scheme group 的字段之后，进行保存
	 * 
	 * @param gridGroupId
	 * @param parseInt
	 * @param aggregationType
	 *          //聚合类型
	 * @param isSelected
	 */
	public void addorDeleteGridGroupFields(int gridGroupId, int fieldId, String aggregationType,
			Boolean isSelected) {
		Session session = systemBaseDAO.getSessionFactory().openSession();
		session.beginTransaction();
		try {

			// 取得gridgroup最大的序号，下面新增的就最大序号加10
			Query query = session
					.createQuery(" select max(tf_gridFieldOrder) from _ModuleGridSchemeGroupField "
							+ "where tf_gridGroupId = ? ");
			query.setParameter(0, gridGroupId);
			Integer maxOrder = (Integer) query.uniqueResult();
			if (maxOrder == null)
				maxOrder = 10;
			else
				maxOrder += 10;
			Integer gridFieldId = null;
			if (aggregationType != null && aggregationType.length() > 1) {
				query = session.createQuery(" select tf_gridFieldId from _ModuleGridSchemeGroupField "
						+ "where tf_gridGroupId = ? and tf_fieldId = ? and tf_aggregate = ? ");
				query.setParameter(0, gridGroupId);
				query.setParameter(1, fieldId);
				query.setParameter(2, aggregationType);
				gridFieldId = (Integer) query.uniqueResult();
			} else {
				query = session.createQuery(" select tf_gridFieldId from _ModuleGridSchemeGroupField "
						+ "where tf_gridGroupId = ? and tf_fieldId = ?");
				query.setParameter(0, gridGroupId);
				query.setParameter(1, fieldId);
				gridFieldId = (Integer) query.uniqueResult();
			}
			if (isSelected) {
				if (gridFieldId == null) {
					_ModuleGridSchemeGroupField field = new _ModuleGridSchemeGroupField();
					field.setTf_ModuleField(new _ModuleField(fieldId));
					field.setTf_ModuleGridSchemeGroup(new _ModuleGridSchemeGroup(gridGroupId));
					field.setTf_aggregate(aggregationType);
					field.setTf_gridFieldOrder(maxOrder);
					session.save(field);
				}
			} else {
				if (gridFieldId != null) {
					query = session.createQuery(" delete _ModuleGridSchemeGroupField "
							+ "where tf_gridFieldId = ? ");
					query.setParameter(0, gridFieldId);
					query.executeUpdate();
				}
			}
		} finally {
			session.getTransaction().commit();
			session.close();
		}

	}

	/**
	 * 用户选择了 grid scheme group 的字段之后，进行保存
	 * 
	 * @param gridGroupId
	 * @param parseInt
	 * @param isSelected
	 */
	public void addorDeleteDetailGroupFields(int detailId, int fieldId, Boolean isSelected) {
		Session session = systemBaseDAO.getSessionFactory().openSession();
		session.beginTransaction();
		try {
			Query query = session.createQuery(" select max(tf_order) from _ModuleDetailSchemeField "
					+ "where tf_detailId = ? ");
			query.setParameter(0, detailId);
			Integer maxOrder = (Integer) query.uniqueResult();
			if (maxOrder == null)
				maxOrder = 10;
			else
				maxOrder += 10;

			query = session.createQuery(" select tf_detailFieldId from _ModuleDetailSchemeField "
					+ "where tf_detailId = ? and tf_fieldId = ?");
			query.setParameter(0, detailId);
			query.setParameter(1, fieldId);
			Integer detailFieldId = (Integer) query.uniqueResult();

			if (isSelected) {
				if (detailFieldId == null) {
					_ModuleDetailSchemeField field = new _ModuleDetailSchemeField();
					field.setTf_ModuleField(new _ModuleField(fieldId));
					field.setTf_ModuleDetailScheme(new _ModuleDetailScheme(detailId));
					field.setTf_order(maxOrder);
					session.save(field);
				}
			} else {
				if (detailFieldId != null)
					session.delete(new _ModuleDetailSchemeField(detailFieldId));
			}
		} finally {
			session.getTransaction().commit();
			session.close();
		}

	}

	/**
	 * 用户选择了 form scheme group 的字段之后，进行保存
	 * 
	 * @param formGroupId
	 * @param parseInt
	 * @param isSelected
	 */
	public void addorDeleteFormGroupFields(int formGroupId, int fieldId, Boolean isSelected) {
		Session session = systemBaseDAO.getSessionFactory().openSession();
		session.beginTransaction();
		try {
			Query query = session
					.createQuery(" select max(tf_formFieldOrder) from _ModuleFormSchemeGroupField "
							+ "where tf_formGroupId = ? ");
			query.setParameter(0, formGroupId);
			Integer maxOrder = (Integer) query.uniqueResult();
			if (maxOrder == null)
				maxOrder = 10;
			else
				maxOrder += 10;

			query = session.createQuery(" select tf_formFieldId from _ModuleFormSchemeGroupField "
					+ "where tf_formGroupId = ? and tf_fieldId = ?");
			query.setParameter(0, formGroupId);
			query.setParameter(1, fieldId);
			Integer gridFieldId = (Integer) query.uniqueResult();

			if (isSelected) {
				if (gridFieldId == null) {
					_ModuleFormSchemeGroupField field = new _ModuleFormSchemeGroupField();
					field.setTf_ModuleField(new _ModuleField(fieldId));
					field.setTf_ModuleFormSchemeGroup(new _ModuleFormSchemeGroup(formGroupId));
					field.setTf_formFieldOrder(maxOrder);
					session.save(field);
				}
			} else {
				if (gridFieldId != null) {
					query = session.createQuery(" delete _ModuleFormSchemeGroupField "
							+ "where tf_formFieldId = ? ");
					query.setParameter(0, gridFieldId);
					query.executeUpdate();
					// session.delete(new _ModuleFormSchemeGroupField(gridFieldId));
				}
			}
		} finally {
			session.getTransaction().commit();
			session.close();
		}

	}

}
