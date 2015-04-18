package org.ike.yota.service;

import java.util.ArrayList;
import java.util.List;

import org.ike.yota.entity.User;
import org.ike.yota.entity.UserDAO;
import org.ike.yota.model.EUser;
import org.ike.yota.util.PageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务处理类
 * 
 * @author 林玉生
 * 
 * @since 2014-10-28
 * 
 * @version v1.0
 *
 */
@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	
	/**
	 * 根据主键查找对象
	 * 
	 * @param id
	 * 
	 * @return
	 */
	public User get(Integer id) {
		return userDAO.findById(id);
	}
	/**
	 * 保存用户对象
	 * 
	 * @param user
	 */
	public Integer save(User user) {
		return (Integer)userDAO.save(user);
	}
	/**
	 * 根据openId查找用户
	 * 
	 * @param openId
	 * 
	 * @return
	 */
	public User findByUserOpenId(String openId) {
		List<User> list = userDAO.findByUserOpenId(openId);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	/**
	 * 根据userId或者openId查找用户
	 * 
	 * @param openId
	 * 
	 * @return
	 */	
	public User getUser(Object id) {
		User user = null;
		if (id instanceof Integer) {
			user = get((Integer)id);
		}else {
			user = findByUserOpenId((String)id);
		}
		return user;
	}
	/**
	 * 复制list
	 * 
	 * @param list
	 * 
	 * @return
	 */
	public List<EUser> copyList(List<User> list) {
		List<EUser> eList = new ArrayList<EUser>();
		for (User user : list) {
			EUser eUser = new EUser();
			BeanUtils.copyProperties(user, eUser);
			eList.add(eUser);
		}
		return eList;
	}
	/**
	 * 根据userId或者openId数组查找多个用户
	 * 
	 * @param ids
	 * 
	 * @return
	 */
	public List<User> getUsers(List<Object> ids) {
		List<User> list = new ArrayList<User>();
		if (!ids.isEmpty()) {
			for (Object object : ids) {
				if (object instanceof Integer) {
					list.add(get((Integer)object));
				}else {
					list.add(findByUserOpenId((String)object));
				}
			}
		}
		return list;
	}
	/**
	 * 查找所有用户
	 * 
	 * @return
	 */
	public List<User> findAll() {
		String selectHql = "from User order by userId desc";
		List<User> list = PageService.searchByPage(selectHql, null, null, null, userDAO);
		return list;
	}
	/**
	 * 根据userId、昵称模糊查找用户
	 * 
	 * @param q
	 * 
	 * @return
	 */
	public List<User> searchUser(String q) {
		String selectHql = "from User where userId like '" + q + "%' or userNickname like '" + q + "%'";
		System.out.println(selectHql);
		List<User> list = PageService.searchByPage(selectHql, null, null, null, userDAO);
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
}
