package org.ike.yota.service;

import java.util.List;

import org.ike.yota.entity.Attend;
import org.ike.yota.entity.AttendDAO;
import org.ike.yota.util.PageBean;
import org.ike.yota.util.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户参与活动业务处理类
 * 
 * @author 林玉生
 * 
 * @since 2014-10-28
 * 
 * @version v1.0
 *
 */
@Service
public class AttendService {
	
	@Autowired
	private AttendDAO attendDAO;
	
	/**
	 * 根据主键Id查找参与记录
	 * 
	 * @param id
	 * 
	 * @return
	 */
	public Attend findById(Integer id) {
		return attendDAO.findById(id);
	}
	/**
	 * 保存参与对象
	 * 
	 * @param activities
	 */
	public void save(Attend attend) {
		attendDAO.merge(attend);
	}
	/**
	 * 删除参与对象
	 * 
	 * @param attend
	 */
	public void delete(Integer id){
		attendDAO.delete(attendDAO.findById(id));
	}
	/**
	 * 根据用户Id和活动Id查找参与记录
	 * 
	 * @param userId
	 * 
	 * @param activitiesId
	 * 
	 * @return
	 */
	public Attend isAttend(Integer userId,Integer activitiesId) {
		String selectHql = "from Attend where user.userId = '" + userId + "' and activities.activitiesId = '" + activitiesId + "'";
		List<Attend> list = PageService.searchByPage(selectHql, null, null, null, attendDAO);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	/**
	 * 根据用户Id查找参与记录(根据活动Id降序排序)
	 * 
	 * @param userId
	 * 
	 * @return
	 */
	public List<Attend> findByUserId(Integer userId,Integer activitiesId,PageBean pageBean) {
		String selectHql = "";
		String countHql = "";
		//判断是否为第一次浏览活动
		if (activitiesId == 0) {
			selectHql = "from Attend where user.userId = '" + userId + "' order by activities.activitiesId desc";
		}else {
			selectHql = "from Attend where user.userId = '" + userId + "' and activities.activitiesId < '" + activitiesId + "' order by activities.activitiesId desc";
		}
		//System.out.println("参与："+selectHql);
		countHql="select count(*) "+selectHql;
		List<Attend> list = PageService.searchByPage(selectHql, countHql, null, pageBean, attendDAO);
		return list;
	}
	/**
	 * 根据活动Id查找参与记录
	 * 
	 * @param activitiesId
	 * 
	 * @return
	 */
	public List<Attend> findByActivitiesId(Integer activitiesId) {
		String selectHql = "from Attend where activities.activitiesId = '" + activitiesId + "'";
		List<Attend> list = PageService.searchByPage(selectHql, null, null, null, attendDAO);
		return list;
	}
	/**
	 * 根据userId或者openId查找自己参与的活动(一次性传送)
	 * 
	 * @param id
	 * 
	 * @return
	 */
	public List<Attend> findMyActivities(Object id) {
		String selectHql = "";
		//如果为整型，则为userId，否则为openId
		if (id instanceof Integer) {
			selectHql = "from Attend where user.userId = '" + (Integer)id + "' order by activities.activitiesId desc";
		}else {
			selectHql = "from Attend where user.userOpenId = '" + (String)id + "' order by activities.activitiesId desc";
		}
		List<Attend> list = PageService.searchByPage(selectHql, null, null, null, attendDAO);
		return list;
	}

}
