package org.ike.yota.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.ike.yota.entity.Activities;
import org.ike.yota.entity.ActivitiesDAO;
import org.ike.yota.entity.Attend;
import org.ike.yota.model.EActivities;
import org.ike.yota.model.EActivitiesType;
import org.ike.yota.util.PageBean;
import org.ike.yota.util.PageService;
import org.ike.yota.util.Tool;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 活动业务处理类
 * 
 * @author 林玉生
 * 
 * @since 2014-10-28
 * 
 * @version v1.0
 *
 */
@Service
public class ActivitiesService {
	
	@Autowired
	private ActivitiesDAO activitiesDAO;
	
	/**
	 * 根据主键id加载对象
	 * 
	 * @param id
	 * 
	 * @return
	 */
	public Activities get(Integer id) {
		return activitiesDAO.findById(id);
	}
	/**
	 * 保存对象,返回活动对象主键
	 * 
	 * @param activities
	 * 
	 * @return
	 */
	public Serializable save(Activities activities) {
		return activitiesDAO.save(activities);
	}
	/**
	 * 根据主键id删除对象
	 * 
	 * @param id
	 */
	public void delete(Integer id) {
		activitiesDAO.delete(activitiesDAO.findById(id));
	}
	/**
	 * 更新对象
	 * 
	 * @param activities
	 */
	public void update(Activities activities) {
		activitiesDAO.merge(activities);
	}
	/**
	 * 复制对象属性值
	 * 
	 * @param activities
	 * 
	 * @return
	 */
	public EActivities copyObject(Activities activities) {
		EActivities eActivities = new EActivities();
		BeanUtils.copyProperties(activities, eActivities);
		eActivities.setActivitiesDateAndTime(Tool.timestampToString(activities.getActivitiesDate()));//
		eActivities.setStartDateAndTime(Tool.timestampToString(activities.getStartTime()));
		eActivities.setEndDateAndTime(Tool.timestampToString(activities.getEndTime()));
		//获得活动的男女人数
		int[] sex = getSexNumber(activities);
		eActivities.setMaleNumber(sex[0]);
		eActivities.setFemaleNumber(sex[1]);
		eActivities.setActivitiesNumber(sex[0]+sex[1]);
		//初始化活动创建者信息
		eActivities.setUserId(activities.getUser().getUserId());
		eActivities.setCreator(activities.getUser().getUserNickname());
		eActivities.setHead(activities.getUser().getUserHead());
		return eActivities;
	}
	/**
	 * 复制list集合
	 * 
	 * @param list
	 * 
	 * @return
	 */
	public List<EActivities> copyList(List<Activities> list) {
		List<EActivities> eList = new ArrayList<EActivities>();
		for (Activities activities : list) {
			eList.add(copyObject(activities));
		}
		return eList;
	}
	/**
	 * 查找所有活动
	 * 
	 * @return
	 */
	public List<Activities> findAll() {
		String selectHql = "from Activities order by activitiesId desc";
		List<Activities> list = PageService.searchByPage(selectHql, null, null, null, activitiesDAO);
		return list;
	}
	/**
	 * 加载更多活动
	 * @param activitiesId
	 * @param city
	 * @param typeList
	 * @param pageBean
	 * @return
	 */
	public List<Activities> findMore(Integer activitiesId,String city,List<EActivitiesType> typeList,PageBean pageBean) {
		String selectHql = "";
		//如果为第一次浏览活动
		if (activitiesId == 0) {
			if (typeList.size() == 0) {
				selectHql = "from Activities where activitiesLevel = 1 and activitiesCity = '" + city + "' order by activitiesId desc";
			}else {
				selectHql = "from Activities where activitiesLevel = 1 and activitiesCity = '" + city + "' and activitiesType.typeId in(" + activitiesTypeSql(typeList) + ") order by activitiesId desc";
			}
		}else {
			if (typeList.size() == 0) {
				selectHql = "from Activities where activitiesLevel = 1 and activitiesCity = '" + city + "' and activitiesId < '" + activitiesId + "' order by activitiesId desc";
			}else {
				selectHql = "from Activities where activitiesLevel = 1 and activitiesCity = '" + city + "' and activitiesId < '" + activitiesId + "' and activitiesType.typeId in(" + activitiesTypeSql(typeList) + ") order by activitiesId desc";
			}
		}
		//System.out.println("浏览活动："+selectHql);
		String countHql="select count(*) "+selectHql;
		List<Activities> list = PageService.searchByPage(selectHql, countHql, null, pageBean, activitiesDAO);
		//插入推广的活动
		if (activitiesId == 0) {
			list.addAll(0, getExtensionActivities());
		}
		return list;
	}
	/**
	 * 刷新活动
	 * @param activitiesId
	 * @param city
	 * @param typeList
	 * @return
	 */
	public List<Activities> findNewest(Integer activitiesId,String city,List<EActivitiesType> typeList) {
		String selectHql = "";
		if (typeList.size() == 0) {
			selectHql = "from Activities where activitiesLevel = 1 and activitiesCity = '" + city + "' and activitiesId > '" + activitiesId + "' order by activitiesId asc";
		}else {
			selectHql = "from Activities where activitiesLevel = 1 and activitiesCity = '" + city + "' and activitiesId > '" + activitiesId + "' and activitiesType.typeId in(" + activitiesTypeSql(typeList) + ") order by activitiesId asc";
		}
		//System.out.println("刷新活动："+selectHql);
		List<Activities> list = PageService.searchByPage(selectHql, null, null, null, activitiesDAO);
		return list;
	}
	/**
	 * 拼装活动类型条件sql
	 * @param typeList
	 * @return
	 */
	public String activitiesTypeSql(List<EActivitiesType> typeList) {
		String number = "";
		for (int i = 0; i < typeList.size(); i++) {
			if (0 == i) {
				number += typeList.get(i).getTypeId();
				continue;
			}
			number += "," + typeList.get(i).getTypeId();
		}
		return number;
	}
	/**
	 * 获得活动总人数
	 * @param activities
	 * @return
	 */
	public Integer getTotalNumber(Activities activities) {
		return activities.getAttends().size();
	}
	/**
	 * 获得活动男女人数
	 * @param activities
	 * @return
	 */
	public int[] getSexNumber(Activities activities) {
		int[] sex = new int[2];
		Set<Attend> attends = activities.getAttends();
		for (Attend attend : attends) {
			if (attend.getUser().getUserSex().equals("男")) {
				sex[0]++;
			}else {
				sex[1]++;
			}
		}
		return sex;
	}
	/**
	 * 刷新热门的活动
	 */
	public void refreshHotActivities(PageBean pageBean) {
		//刷新所有活动的参与人数,将热门活动标志置0
		List<Activities> allList = findAll();
		for (Activities activities : allList) {
			activities.setActivitiesNumber(activities.getAttends().size());
			activities.setIsHot((short)0);
			update(activities);
		}
		//查找参与人数最多的前N个活动（N在pageBean中设置）,将其热门活动标志置1
		String selectHql = "from Activities order by activitiesNumber desc";
		String countHql = "select count(*) "+selectHql;
		List<Activities> list = PageService.searchByPage(selectHql, countHql, null, pageBean, activitiesDAO);
		for (Activities activities : list) {
			activities.setIsHot((short)1);
			update(activities);
		}
	}
	/**
	 * 刷新推广的活动
	 */
	public void refreshExtensionActivities(int[] ids) {
		//初始化所有活动的级别，置1(普通级别)
		List<Activities> allList = findAll();
		for (Activities activities : allList) {
			activities.setActivitiesLevel(1);
			update(activities);
		}
		//将需要推广的活动级别提升为2
		for (int i = 0; i < ids.length; i++) {
			Activities activities = get(ids[i]);
			activities.setActivitiesLevel(2);
			update(activities);
		}
	}
	/**
	 * 获得推广的活动
	 * @return
	 */
	public List<Activities> getExtensionActivities() {
		String selectHql = "from Activities where activitiesLevel = 2 order by activitiesId desc";
		List<Activities> list = PageService.searchByPage(selectHql, null, null, null, activitiesDAO);
		return list;
	}

}
