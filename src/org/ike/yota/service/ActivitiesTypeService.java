package org.ike.yota.service;

import java.util.ArrayList;
import java.util.List;

import org.ike.yota.entity.ActivitiesType;
import org.ike.yota.entity.ActivitiesTypeDAO;
import org.ike.yota.model.EActivitiesType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 活动类型业务处理类
 * 
 * @author 林玉生
 * 
 * @since 2014-10-31
 * 
 * @version v1.0
 *
 */
@Service
public class ActivitiesTypeService {
	
	@Autowired
	private ActivitiesTypeDAO activitiesTypeDAO;
	
	/**
	 * 根据活动类型id查找活动类型对象
	 * 
	 * @param id
	 * 
	 * @return
	 */
	public ActivitiesType findById(Integer id) {
		return activitiesTypeDAO.findById(id);
	}
	
	/**
	 * 查找出所有活动类型名
	 * 
	 * @return
	 */
	public List<EActivitiesType> findAll() {
		List<ActivitiesType> list = activitiesTypeDAO.findAll();
		List<EActivitiesType> eList = new ArrayList<EActivitiesType>();
		for (ActivitiesType activitiesType : list) {
			EActivitiesType eActivitiesType = new EActivitiesType();
			BeanUtils.copyProperties(activitiesType, eActivitiesType);
			eList.add(eActivitiesType);
		}
		return eList;
	}

}
