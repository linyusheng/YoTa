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
 * A data access object (DAO) providing persistence and search support for User
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see org.ike.yota.entity.User
 * @author MyEclipse Persistence Tools
 */
public class UserDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(UserDAO.class);
	// property constants
	public static final String USER_OPEN_ID = "userOpenId";
	public static final String USER_NICKNAME = "userNickname";
	public static final String USER_HEAD = "userHead";
	public static final String USER_AGE = "userAge";
	public static final String USER_SEX = "userSex";
	public static final String USER_SIGNATURE = "userSignature";
	public static final String USER_SCHOOL = "userSchool";
	public static final String USER_OCCUPATION = "userOccupation";
	public static final String USER_HOMETOWN = "userHometown";
	public static final String USER_EDUCATION = "userEducation";
	public static final String USER_POSITION = "userPosition";
	public static final String USER_ROLE = "userRole";
	public static final String USER_IS_LOVE = "userIsLove";
	public static final String USER_REAL_NAME = "userRealName";

	protected void initDao() {
		// do nothing
	}

	public Serializable save(User transientInstance) {
		Serializable serializable = null;
		log.debug("saving User instance");
		try {
			serializable = getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		return serializable;
	}

	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public User findById(java.lang.Integer id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) getHibernateTemplate().get(
					"org.ike.yota.entity.User", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(User instance) {
		log.debug("finding User instance by example");
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
		log.debug("finding User instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from User as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUserOpenId(Object userOpenId) {
		return findByProperty(USER_OPEN_ID, userOpenId);
	}

	public List findByUserNickname(Object userNickname) {
		return findByProperty(USER_NICKNAME, userNickname);
	}

	public List findByUserHead(Object userHead) {
		return findByProperty(USER_HEAD, userHead);
	}

	public List findByUserAge(Object userAge) {
		return findByProperty(USER_AGE, userAge);
	}

	public List findByUserSex(Object userSex) {
		return findByProperty(USER_SEX, userSex);
	}

	public List findByUserSignature(Object userSignature) {
		return findByProperty(USER_SIGNATURE, userSignature);
	}

	public List findByUserSchool(Object userSchool) {
		return findByProperty(USER_SCHOOL, userSchool);
	}

	public List findByUserOccupation(Object userOccupation) {
		return findByProperty(USER_OCCUPATION, userOccupation);
	}

	public List findByUserHometown(Object userHometown) {
		return findByProperty(USER_HOMETOWN, userHometown);
	}

	public List findByUserEducation(Object userEducation) {
		return findByProperty(USER_EDUCATION, userEducation);
	}

	public List findByUserPosition(Object userPosition) {
		return findByProperty(USER_POSITION, userPosition);
	}

	public List findByUserRole(Object userRole) {
		return findByProperty(USER_ROLE, userRole);
	}

	public List findByUserIsLove(Object userIsLove) {
		return findByProperty(USER_IS_LOVE, userIsLove);
	}

	public List findByUserRealName(Object userRealName) {
		return findByProperty(USER_REAL_NAME, userRealName);
	}

	public List findAll() {
		log.debug("finding all User instances");
		try {
			String queryString = "from User";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = (User) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(User instance) {
		log.debug("attaching dirty User instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(User instance) {
		log.debug("attaching clean User instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UserDAO) ctx.getBean("UserDAO");
	}
}