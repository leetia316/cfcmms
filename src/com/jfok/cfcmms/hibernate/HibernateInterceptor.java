package com.jfok.cfcmms.hibernate;

import java.io.Serializable;
import java.util.Iterator;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.EntityMode;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

import com.jfok.cfcmms.hibernate.system._IModuleControlInterface;
import com.jfok.cfcmms.service.SystemAndLoginInfoService;


public class HibernateInterceptor extends EmptyInterceptor {
	private static final long serialVersionUID = 1L;

	@Override
	public void afterTransactionBegin(Transaction tx) {
		// System.out.println("afterTransactionBegin" + tx);
		super.afterTransactionBegin(tx);
	}

	@Override
	public void afterTransactionCompletion(Transaction tx) {
		// System.out.println("afterTransactionCompletion" + tx);
		super.afterTransactionCompletion(tx);
	}

	@Override
	public void beforeTransactionCompletion(Transaction tx) {
		// System.out.println("beforeTransactionCompletion" + tx);

		super.beforeTransactionCompletion(tx);
	}

	@Override
	public int[] findDirty(Object entity, Serializable id, Object[] currentState,
			Object[] previousState, String[] propertyNames, Type[] types) {

		// //System.out.println("findDirty" + entity.getClass().getName());

		return super.findDirty(entity, id, currentState, previousState, propertyNames, types);
	}

	@Override
	public Object getEntity(String entityName, Serializable id) {
		// //System.out.println("getEntity entityName:" + entityName + ",id:" + id
		// );
		return super.getEntity(entityName, id);
	}

	@Override
	public String getEntityName(Object object) {
		// //System.out.println("getEntityName object:" + object );
		return super.getEntityName(object);
	}

	@Override
	public Object instantiate(String entityName, EntityMode entityMode, Serializable id) {
		// System.out.println("instantiate entityName:" + entityName );
		return super.instantiate(entityName, entityMode, id);
	}

	@Override
	public Boolean isTransient(Object entity) {
		// System.out.println("isTransient entity:" + entity );
		return super.isTransient(entity);
	}

	@Override
	public void onCollectionRecreate(Object collection, Serializable key)
			throws CallbackException {
		// System.out.println("onCollectionRecreate collection:" + collection );

		super.onCollectionRecreate(collection, key);
	}

	@Override
	public void onCollectionRemove(Object collection, Serializable key)
			throws CallbackException {
		// System.out.println("onCollectionRemove collection:" + collection );
		super.onCollectionRemove(collection, key);
	}

	@Override
	public void onCollectionUpdate(Object collection, Serializable key)
			throws CallbackException {
		// System.out.println("onCollectionUpdate collection:" + collection );
		super.onCollectionUpdate(collection, key);
	}

	@Override
	public void onDelete(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		// System.out.println("onDelete entity:" + entity );
		//如果是修改的系统数据，那么下次刷新时，要全部更新系统的数据
		if (entity instanceof _IModuleControlInterface)
			SystemAndLoginInfoService.setRefreshAll(true);
		super.onDelete(entity, id, state, propertyNames, types);
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState,
			Object[] previousState, String[] propertyNames, Type[] types) {
		// System.out.println("onFlushDirty entity:" + entity.getClass().getName()
		// );
		//
		//
		// if (currentState != null)
		// for (Object object : currentState)
		// System.out.println("currentState:" + object);
		//
		// if (previousState != null)
		// for (Object object : previousState)
		// System.out.println("previousState:" + object);
		//
		// if (propertyNames != null)
		// for (Object object : propertyNames)
		// System.out.println("propertyNames:" + object);
		//
		// if (types != null)
		// for (Object object : types)
		// System.out.println("types:" + object);
		//如果是修改的系统数据，那么下次刷新时，要全部更新系统的数据
		
		if (entity instanceof _IModuleControlInterface)
			SystemAndLoginInfoService.setRefreshAll(true);
		
		return super.onFlushDirty(entity, id, currentState, previousState, propertyNames,
				types);
	}

	@Override
	public boolean onLoad(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		// //System.out.println("onLoad entity:" + entity );

		// HttpServletRequest request =
		// ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		// //System.out.println ("----------"+
		// request.getSession(false).getAttribute(Constant.USERNAME));

		return super.onLoad(entity, id, state, propertyNames, types);
	}

	@Override
	public String onPrepareStatement(String sql) {
		// System.out.println("onPrepareStatement sql:" + sql );
		return super.onPrepareStatement(sql);
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		//System.out.println("onSave entity:" + entity);
		//如果是修改的系统数据，那么下次刷新时，要全部更新系统的数据
		if (entity instanceof _IModuleControlInterface)
			SystemAndLoginInfoService.setRefreshAll(true);
		return super.onSave(entity, id, state, propertyNames, types);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void postFlush(Iterator entities) {
		super.postFlush(entities);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void preFlush(Iterator entities) {
		// System.out.println("preFlush entities:" + entities );
		super.preFlush(entities);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// System.out.println("clone" );
		return super.clone();
	}

	@Override
	public boolean equals(Object arg0) {
		// System.out.println("equals" );
		return super.equals(arg0);
	}

	@Override
	protected void finalize() throws Throwable {
		// System.out.println("finalize" );
		super.finalize();
	}

	@Override
	public int hashCode() {
		// System.out.println("hashCode" );
		return super.hashCode();
	}

	@Override
	public String toString() {
		// System.out.println("toString" );
		return super.toString();
	}

}
