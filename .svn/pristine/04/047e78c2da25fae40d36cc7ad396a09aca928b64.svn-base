package com.lyht.base.hibernate.basedao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.hql.internal.ast.QueryTranslatorImpl;
import org.hibernate.transform.Transformers;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.util.CommonUtil;

public abstract class HibernateBaseDao <T> {
	
	    @Resource private SessionFactory sessionFactory;
	    protected Class<T> entityClass;
	 
	    public HibernateBaseDao() {
	     
	    }
	 
	    
	    @SuppressWarnings({ "unchecked", "rawtypes" })
		protected Class getEntityClass() {
	        if (entityClass == null) {
	            entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	        }
	        return entityClass;
	    }	
	    
	    
	    /**
	     * @return the sessionFactory
	     */
	    public SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }
	 
	    /**
	     * @param sessionFactory the sessionFactory to set
	     */
	    public void setSessionFactory(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }
	     
	    /**
	     * 
	     * @return session
	     */
	    public Session getSession() {
	        return sessionFactory.getCurrentSession();
	    }	    
	    
	    /**
	     * <保存实体>
	     * <完整保存实体>
	     * @param t 实体参数
	     */
	    public void save(T t) {
	        this.getSession().save(t);
	    }	    
	    
	    /**  游离态处理
	     * @param t
	     */
	    public void merge(T t){
	    	this.getSession().merge(t);
	    }
	    
	    public void flush(T t){
	    	this.getSession().flush();
	    }
	    
	    /**  去除强关系
	     * @param t
	     */
	    public void evict(T t){
	    	this.getSession().evict(t);
	    }
	    
	    /**
	     * <保存或者更新实体>
	     * @param t 实体
	     */
	    public void saveOrUpdate(T t) {
	        this.getSession().saveOrUpdate(t);
	    }	

	    /**
	     * <load>
	     * <加载实体的load方法>
	     * @param id 实体的id
	     * @return 查询出来的实体
	     */
	    @SuppressWarnings("unchecked")
		public T load(Serializable id) {
	        T load = (T) this.getSession().load(getEntityClass(), id);
	        return load;
	    }
	     
	    /**
	     * <get>
	     * <查找的get方法>
	     * @param id 实体的id
	     * @return 查询出来的实体
	     * @see com.itv.launcher.util.IBaseDao#get(java.io.Serializable)
	     */
	    @SuppressWarnings("unchecked")
		public T get(Serializable id) {
	        T load = (T) this.getSession().get(getEntityClass(), id);
	        return load;
	    }
	     
	    /**
	     * <contains>
	     * @param t 实体
	     * @return 是否包含
	     * @see com.itv.launcher.util.IBaseDao#contains(java.lang.Object)
	     */
	    public boolean contains(T t) {
	        return this.getSession().contains(t);
	    }
	    
	    
	    /**
	     * <refresh>
	     * @param t 实体
	     */
	    public void refresh(T t) {
	        this.getSession().refresh(t);
	    }
	 
	    /**
	     * <update>
	     * @param t 实体
	     */
	    public void update(T t) {
	        this.getSession().update(t);
	    }	    
	 
	    /**
	     * <delete>
	     * <删除表中的t数据>
	     * @param t 实体
	     */
	    public void delete(T t) {
	        this.getSession().delete(t);
	    }
	     
	    /**
	     * <根据ID删除数据>
	     * @param Id 实体id
	     * @return 是否删除成功
	     */
	    public boolean deleteById(Serializable Id) {
	         T t = get(Id);
	         if(t == null){
	             return false;
	         }
	         delete(t);
	        return true;
	    }
	 
	    /**
	     * <删除所有>
	     * @param entities 实体的Collection集合
	     */
	    public void deleteAll(Collection<T> entities) {
	        for(Object entity : entities) {
	            this.getSession().delete(entity);
	        }
	    }	    
	    
	   
	    
	    
	    /**
	     * <执行Hql语句>
	     * @param hqlString hql
	     * @param values 不定参数数组
	     */
	    public int excuteHql(String hql, Object[] values) {
	        Query query = this.getSession().createQuery(hql);
	        if (values != null)
	        {
	            for (int i = 0; i < values.length; i++)
	            {
	                query.setParameter(i, values[i]);
	            }
	        }
	        return query.executeUpdate();
	    }	    
	    
	    
	    /**
	     * <执行Sql语句>
	     * @param sqlString sql
	     * @param values 不定参数数组
	     */
	    public int excuteSql(String sql, Object[] values) { 
	        Query query = this.getSession().createSQLQuery(sql);
	        if (values != null)
	        {
	            for (int i = 0; i < values.length; i++)
	            {
	                query.setParameter(i, values[i]);
	            }
	        }
	        return query.executeUpdate();
	    }
	 
	    /**
	     * <根据HQL语句查找唯一实体>
	     * @param hqlString HQL语句
	     * @param values 不定参数的Object数组
	     * @return 查询实体
	     */
	    @SuppressWarnings("unchecked")
		public T getByHQL(String hql, Object[] values) {
	        Query query = this.getSession().createQuery(hql);
	        if (values != null)
	        {
	            for (int i = 0; i < values.length; i++)
	            {
	                query.setParameter(i, values[i]);
	            }
	        }
	        return (T) query.uniqueResult();
	    }
	 
	    /**
	     * <根据SQL语句查找唯一实体>
	     * @param sqlString SQL语句
	     * @param values 不定参数的Object数组
	     * @return 查询实体
	     */
	    @SuppressWarnings("unchecked")
		public T getBySQL(String sql, Object[] values) {
	        Query query = this.getSession().createSQLQuery(sql);
	        if (values != null)
	        {
	            for (int i = 0; i < values.length; i++)
	            {
	                query.setParameter(i, values[i]);
	            }
	        }
	        return (T) query.uniqueResult();
	    }
	    /**
	     * <根据HQL语句，得到对应的list>
	     * @param hqlString HQL语句
	     * @param values 不定参数的Object数组
	     * @return 查询多个实体的List集合
	     * @see com.itv.launcher.util.IBaseDao#getListByHQL(java.lang.String, java.lang.Object[])
	     */
	    @SuppressWarnings({ "unchecked", "rawtypes" })
		public List<Map> getMapListByHQL(String hql, Object[] values) {
	        Query query = this.getSession().createQuery(hql);
	        if (values != null)
	        {
	            for (int i = 0; i < values.length; i++)
	            {
	                query.setParameter(i, values[i]);
	            }
	        }
	        return query.list();
	    }
	    /**
	     * <根据HQL语句，得到对应的list>
	     * @param hqlString HQL语句
	     * @param values 不定参数的Object数组
	     * @return 查询多个实体的List集合
	     * @see com.itv.launcher.util.IBaseDao#getListByHQL(java.lang.String, java.lang.Object[])
	     */
	    @SuppressWarnings("unchecked")
		public List<T> getListByHQL(String hql, Object[] values) {
	        Query query = this.getSession().createQuery(hql);
	        if (values != null)
	        {
	            for (int i = 0; i < values.length; i++)
	            {
	                query.setParameter(i, values[i]);
	            }
	        }
	        return query.list();
	    }
	 
	    /**
	     * <根据SQL语句，得到对应的list>
	     * @param sqlString HQL语句
	     * @param values 不定参数的Object数组
	     * @return 查询多个实体的List集合
	     * @see com.itv.launcher.util.IBaseDao#getListBySQL(java.lang.String, java.lang.Object[])
	     */
	    @SuppressWarnings("unchecked")
		public List<T> getListBySQL(String sql, Object[] values ) {
	        Query query = this.getSession().createSQLQuery(sql);
	        if (values != null)
	        {
	            for (int i = 0; i < values.length; i++)
	            {
	                query.setParameter(i, values[i]);
	            }
	        }
	        return query.list();
	    }
	     
	    /**
	     * <根据HQL得到记录数>
	     * @param hql HQL语句
	     * @param values 不定参数的Object数组
	     * @return 记录总数
	     */
	    public Long countByHql(String hql, Object[] values) {
	        Query query = this.getSession().createQuery(hql);
	        if(values != null){
	            for(int i = 0; i < values.length; i++) {
	                query.setParameter(i, values[i]);
	            }
	        }
	        return (Long) query.uniqueResult();
	    }
	    public Long countBySql(String sql,Object[] values){
	    	Query query = this.getSession().createSQLQuery(sql);
	        if(values != null){
	            for(int i = 0; i < values.length; i++) {
	                query.setParameter(i, values[i]);
	            }
	        }
	        return (Long) query.uniqueResult();
	    }
	    private int getOrderByIndex(String sql){
	    	String ss = sql.toUpperCase();
	    	int index = ss.indexOf(" ORDER ");
	    	while(index>0){
	    		int byidx = ss.substring(index).indexOf(" BY ");
	    		if(byidx>0){
	    			String oderby = ss.substring(index,index+byidx);
	    			if(CommonUtil.trim(oderby).equals("ORDER")){
	    				return index;
	    			}
	    		}
	    		index = ss.substring(index+7).indexOf(" ORDER ");
	    	}
	    	return -1;
	    }
	    
	    @SuppressWarnings({ "rawtypes", "unchecked" })
		public PageResults<Map> findPageByFetchedSql(String sql,String countSql,int pageNo,int pageSize,Object[] values){
	    	PageResults<Map> retValue = new PageResults<Map>();
	    	if (countSql == null)
	        {
	    		int orderindex=getOrderByIndex(sql);
	    		if(orderindex>0){
	    			countSql="select count(*) as SL from ("+sql.substring(0, orderindex)+") _A where 1=1 ";
	    		}else{
	    			countSql="select count(*) as SL from ("+sql+") _A where 1=1 ";
	    		}
	        }
	    	Session session = this.getSession();
	    	Query query = session.createSQLQuery(countSql);
	        if(values != null){
	            for(int i = 0; i < values.length; i++) {
	                query.setParameter(i, values[i]);
	            }
	        }
	        Object obj = query.uniqueResult();
	        int count = obj!=null?CommonUtil.getIntValue(obj.toString()):0;
	        retValue.setTotalCount(count);
	        Query queryA=session.createSQLQuery(sql);
	        if(values != null){
	            for(int i = 0; i < values.length; i++) {
	            	queryA.setParameter(i, values[i]);
	            }
	        }
	        
	        int currentPage = pageNo > 1 ? pageNo : 1;
	        queryA.setFirstResult((currentPage - 1) * pageSize);
	        queryA.setMaxResults(pageSize);
	        //需要将hibernate的数据库方言设置明确，不能笼统为SQL Server，需要具体到2005或2008等具体版本，否则执行会出错误
	        List<Map> itemList = queryA.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	        if (itemList == null)
	        {
	            itemList = new ArrayList<Map>();
	        }
	        retValue.setResults(itemList);
	        return retValue;
	    }
	    private String generSqlByHql(String hql){
	    	QueryTranslatorImpl queryTranslator=new QueryTranslatorImpl(hql,hql,Collections.EMPTY_MAP,(SessionFactoryImplementor)getSessionFactory());
	        queryTranslator.compile(Collections.EMPTY_MAP, false);
	        return queryTranslator.getSQLString();
	    }
	    /**
	     * <HQL分页查询>
	     * @param hql HQL语句
	     * @param countHql 查询记录条数的HQL语句
	     * @param pageNo 下一页
	     * @param pageSize 一页总条数
	     * @param values 不定Object数组参数
	     * @return PageResults的封装类，里面包含了页码的信息以及查询的数据List集合
	     */
	    @SuppressWarnings({ "unchecked", "rawtypes" })
		public PageResults<Map> findPageMapByFetchedHql(String hql, String countHql,
	            int pageNo, int pageSize, Object[] values) {
	        PageResults<Map> retValue = new PageResults<Map>();
	        if (countHql == null){
	 	         int indx=hql.toUpperCase().indexOf("FROM");
	 	         countHql="select count(*) "+hql.substring(indx);
	        } 
	    	Session session = this.getSession();
	    	Query query = session.createSQLQuery(countHql);
	        if(values != null){
	            for(int i = 0; i < values.length; i++) {
	                query.setParameter(i, values[i]);
	            }
	        }
	        Object obj = query.uniqueResult();
	        int count = obj!=null?CommonUtil.getIntValue(obj.toString()):0;
	        retValue.setTotalCount(count);
	        
	        query=session.createQuery(hql);
	        if(values != null){
	            for(int i = 0; i < values.length; i++) {
	                query.setParameter(i, values[i]);
	            }
	        }
	        int currentPage = pageNo > 1 ? pageNo : 1;
	        query.setMaxResults(pageSize);
	        query.setFirstResult((currentPage - 1) * pageSize);
	        //需要将hibernate的数据库方言设置明确，不能笼统为SQL Server，需要具体到2005或2008等具体版本，否则执行会出错误
	        List<Map> itemList = query.list();
	        if (itemList == null)
	        {
	            itemList = new ArrayList<Map>();
	        }
	        retValue.setResults(itemList);
	         
	        return retValue;
	    }
	    /**
	     * <HQL分页查询>
	     * @param hql HQL语句
	     * @param countHql 查询记录条数的HQL语句
	     * @param pageNo 下一页
	     * @param pageSize 一页总条数
	     * @param values 不定Object数组参数
	     * @return PageResults的封装类，里面包含了页码的信息以及查询的数据List集合
	     */
	    @SuppressWarnings("unchecked")
		public PageResults<T> findPageByFetchedHql(String hql, String countHql,
	            int pageNo, int pageSize, Object[] values) {
	        PageResults<T> retValue = new PageResults<T>();
	        if (countHql == null){
	 	         int indx=hql.toUpperCase().indexOf("FROM");
	 	         countHql="select count(*) "+hql.substring(indx);
	        } 
	    	Session session = this.getSession();
	    	Query query = session.createQuery(countHql);
	        if(values != null){
	            for(int i = 0; i < values.length; i++) {
	                query.setParameter(i, values[i]);
	            }
	        }
	        Object obj = query.uniqueResult();
	        int count = obj!=null?CommonUtil.getIntValue(obj.toString()):0;
	        retValue.setTotalCount(count);
	        
	        query=session.createQuery(hql);
	        if(values != null){
	            for(int i = 0; i < values.length; i++) {
	                query.setParameter(i, values[i]);
	            }
	        }
	        int currentPage = pageNo > 1 ? pageNo : 1;
	        query.setMaxResults(pageSize);
	        query.setFirstResult((currentPage - 1) * pageSize);
	        //需要将hibernate的数据库方言设置明确，不能笼统为SQL Server，需要具体到2005或2008等具体版本，否则执行会出错误
	        List<T> itemList = query.list();
	        if (itemList == null)
	        {
	            itemList = new ArrayList<T>();
	        }
	        retValue.setResults(itemList);
	         
	        return retValue;
	    }
	    
	    
	    /**
	     * 
	     * 设置每行批处理参数
	     * 
	     * @param ps
	     * @param pos ?占位符索引，从0开始
	     * @param data
	     * @throws SQLException
	     */
	    @SuppressWarnings({ "rawtypes", "unused" })
	    
		private void setParameter(PreparedStatement ps, int pos, Object data)
	        throws SQLException
	    {
	        if (data == null)
	        {
	            ps.setNull(pos + 1, Types.VARCHAR);
	            return;
	        }
	        Class dataCls = data.getClass();
	        if (String.class.equals(dataCls))
	        {
	            ps.setString(pos + 1, (String)data);
	        }
	        else if (boolean.class.equals(dataCls))
	        {
	            ps.setBoolean(pos + 1, ((Boolean)data));
	        }
	        else if (int.class.equals(dataCls))
	        {
	            ps.setInt(pos + 1, (Integer)data);
	        }
	        else if (double.class.equals(dataCls))
	        {
	            ps.setDouble(pos + 1, (Double)data);
	        }
	        else if (Date.class.equals(dataCls))
	        {
	            Date val = (Date)data;
	            ps.setTimestamp(pos + 1, new Timestamp(val.getTime()));
	        }
	        else if (BigDecimal.class.equals(dataCls))
	        {
	            ps.setBigDecimal(pos + 1, (BigDecimal)data);
	        }
	        else
	        {
	            // 未知类型
	            ps.setObject(pos + 1, data);
	        }
	         
	    }	    
	    
	    /** 简单的SQL 原生态语句 查询列表
	  		 * @param sql
	  		 * @return
	  		 */
	  		public List  createSQLQuerybyObjectArray(String sql){
	  			return this.getSession().createSQLQuery(sql).list();
	  		}
	  		
	  		/** 简单的SQL原生态语句执行
	  		 * @param sql
	  		 * @return
	  		 */
	  		public int exectueSQL(String sql){
	  			return this.getSession().createSQLQuery(sql).executeUpdate();
	  		}
	  		
	  		/** 简单的原生态SQL执行 返回集合封装的MAP 字段--值
	  		 * @param sql
	  		 * @return
	  		 */
	  		public List<Map> createSQLQuerybyMap(String sql){
	  			return this.getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	  		}
	  		
	  		/**
	  		 * 根据属性名和属性值查询对象.
	  		 * 
	  		 * @return 符合条件的对象列表
	  		 */
	  		@SuppressWarnings({ "unchecked", "hiding" })
	  		public <T> List<T> findBy(String propertyName, Object value) {
	  			return createCriteria(getEntityClass(), Restrictions.eq(propertyName, value))
	  					.list();
	  		}
	  		
	  		/**
	  		 * 根据属性名和属性值查询对象.
	  		 * 
	  		 * @return 符合条件的对象列表
	  		 */
	  		@SuppressWarnings({ "unchecked", "hiding" })
	  		public  T findByObject(String propertyName, Object value) {
	  			return (T) createCriteria(getEntityClass(), Restrictions.eq(propertyName, value))
	  					.uniqueResult(); 
	  		}

	  		
	  		/**
	  		 * 根据属性名和属性值以Like AnyWhere方式查询对象.
	  		 */
	  		@SuppressWarnings("unchecked")
	  		public List<T> findByLike(String propertyName, String value) {
	  			return createCriteria(getEntityClass(),
	  					Restrictions.like(propertyName, value, MatchMode.ANYWHERE))
	  					.list();
	  		}
	  		

	  		/**
	  		 * 根据属性名和属性值查询唯一对象.
	  		 * 
	  		 * @return 符合条件的唯一对象 or null if not found.
	  		 */
	  		@SuppressWarnings({ "unchecked", "hiding" })
	  		public <T> T findUniqueBy(String propertyName, Object value) {
	  			return (T) createCriteria(getEntityClass(),
	  					Restrictions.eq(propertyName, value)).uniqueResult();
	  		}
	  		
	  		/**
	  		 * 创建Criteria对象.
	  		 * 
	  		 * @param criterions
	  		 *            可变的Restrictions条件列表,见{@link #createQuery(String,Object...)}
	  		 */
	  		@SuppressWarnings("hiding")
	  		public <T> Criteria createCriteria(Class<T> entityClass,
	  				Criterion... criterions) {
	  			Criteria criteria = getSession().createCriteria(entityClass);
	  			for (Criterion c : criterions) {
	  				criteria.add(c);
	  			}
	  			return criteria;
	  		}
	    
}
