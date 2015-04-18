package org.ike.yota.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Footprint entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.ike.yota.entity.Footprint
 * @author MyEclipse Persistence Tools
 */
public class FootprintDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(FootprintDAO.class);
	// property constants
	public static final String FOOTPRINT_MOOD = "footprintMood";

	protected void initDao() {
		// do nothing
	}

	public Serializable save(Footprint transientInstance) {
	Serializable serializable = null;
	log.debug("saving Footprint instance");
	try {
		serializable = getHibernateTemplate().save(transientInstance);
		log.debug("save successful");
	} catch (RuntimeException re) {
		log.error("save failed", re);
		throw re;
	}
	return serializable;
}

	public void delete(Footprint persistentInstance) {
		log.debug("deleting Footprint instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Footprint findById(java.lang.Integer id) {
		log.debug("getting Footprint instance with id: " + id);
		try {
			Footprint instance = (Footprint) getHibernateTemplate().get(
					"org.ike.yota.entity.Footprint", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Footprint instance) {
		log.debug("finding Footprint instance by example");
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
		log.debug("finding Footprint instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Footprint as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFootprintMood(Object footprintMood) {
		return findByProperty(FOOTPRINT_MOOD, footprintMood);
	}

	public List findAll() {
		log.debug("finding all Footprint instances");
		try {
			String queryString = "from Footprint";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Footprint merge(Footprint detachedInstance) {
		log.debug("merging Footprint instance");
		try {
			Footprint result = (Footprint) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Footprint instance) {
		log.debug("attaching dirty Footprint instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Footprint instance) {
		log.debug("attaching clean Footprint instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static FootprintDAO getFromApplicationContext(ApplicationContext ctx) {
		return (FootprintDAO) ctx.getBean("FootprintDAO");
	}
}