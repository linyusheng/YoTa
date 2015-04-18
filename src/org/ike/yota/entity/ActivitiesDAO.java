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
 * Activities entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.ike.yota.entity.Activities
 * @author MyEclipse Persistence Tools
 */
public class ActivitiesDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(ActivitiesDAO.class);
	// property constants
	public static final String ACTIVITIES_NAME = "activitiesName";
	public static final String ACTIVITIES_CITY = "activitiesCity";
	public static final String ACTIVITIES_PLACE = "activitiesPlace";
	public static final String ACTIVITIES_POSTER = "activitiesPoster";
	public static final String ACTIVITIES_DESCRIPTION = "activitiesDescription";
	public static final String ACTIVITIES_NUMBER = "activitiesNumber";
	public static final String ACTIVITIES_LEVEL = "activitiesLevel";
	public static final String ACTIVITIES_STATE = "activitiesState";
	public static final String ASPECT_RATIO = "aspectRatio";
	public static final String IS_HOT = "isHot";

	protected void initDao() {
		// do nothing
	}

	public Serializable save(Activities transientInstance) {
		Serializable serializable;
		log.debug("saving Activities instance");
		try {
			serializable = getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		return serializable;
	}

	public void delete(Activities persistentInstance) {
		log.debug("deleting Activities instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Activities findById(java.lang.Integer id) {
		log.debug("getting Activities instance with id: " + id);
		try {
			Activities instance = (Activities) getHibernateTemplate().get(
					"org.ike.yota.entity.Activities", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Activities instance) {
		log.debug("finding Activities instance by example");
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
		log.debug("finding Activities instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Activities as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByActivitiesName(Object activitiesName) {
		return findByProperty(ACTIVITIES_NAME, activitiesName);
	}

	public List findByActivitiesCity(Object activitiesCity) {
		return findByProperty(ACTIVITIES_CITY, activitiesCity);
	}

	public List findByActivitiesPlace(Object activitiesPlace) {
		return findByProperty(ACTIVITIES_PLACE, activitiesPlace);
	}

	public List findByActivitiesPoster(Object activitiesPoster) {
		return findByProperty(ACTIVITIES_POSTER, activitiesPoster);
	}

	public List findByActivitiesDescription(Object activitiesDescription) {
		return findByProperty(ACTIVITIES_DESCRIPTION, activitiesDescription);
	}

	public List findByActivitiesNumber(Object activitiesNumber) {
		return findByProperty(ACTIVITIES_NUMBER, activitiesNumber);
	}

	public List findByActivitiesLevel(Object activitiesLevel) {
		return findByProperty(ACTIVITIES_LEVEL, activitiesLevel);
	}

	public List findByActivitiesState(Object activitiesState) {
		return findByProperty(ACTIVITIES_STATE, activitiesState);
	}

	public List findByAspectRatio(Object aspectRatio) {
		return findByProperty(ASPECT_RATIO, aspectRatio);
	}

	public List findByIsHot(Object isHot) {
		return findByProperty(IS_HOT, isHot);
	}

	public List findAll() {
		log.debug("finding all Activities instances");
		try {
			String queryString = "from Activities";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Activities merge(Activities detachedInstance) {
		log.debug("merging Activities instance");
		try {
			Activities result = (Activities) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Activities instance) {
		log.debug("attaching dirty Activities instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Activities instance) {
		log.debug("attaching clean Activities instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ActivitiesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ActivitiesDAO) ctx.getBean("ActivitiesDAO");
	}
}