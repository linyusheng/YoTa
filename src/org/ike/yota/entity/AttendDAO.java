package org.ike.yota.entity;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Attend entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.ike.yota.entity.Attend
 * @author MyEclipse Persistence Tools
 */
public class AttendDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(AttendDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(Attend transientInstance) {
		log.debug("saving Attend instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Attend persistentInstance) {
		log.debug("deleting Attend instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Attend findById(java.lang.Integer id) {
		log.debug("getting Attend instance with id: " + id);
		try {
			Attend instance = (Attend) getHibernateTemplate().get(
					"org.ike.yota.entity.Attend", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Attend instance) {
		log.debug("finding Attend instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Attend instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Attend as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Attend instances");
		try {
			String queryString = "from Attend";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Attend merge(Attend detachedInstance) {
		log.debug("merging Attend instance");
		try {
			Attend result = (Attend) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Attend instance) {
		log.debug("attaching dirty Attend instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Attend instance) {
		log.debug("attaching clean Attend instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AttendDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AttendDAO) ctx.getBean("AttendDAO");
	}
}