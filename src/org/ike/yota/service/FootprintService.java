package org.ike.yota.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.ike.yota.entity.Footprint;
import org.ike.yota.entity.FootprintDAO;
import org.ike.yota.entity.Photo;
import org.ike.yota.model.EFootprint;
import org.ike.yota.util.PageBean;
import org.ike.yota.util.PageService;
import org.ike.yota.util.Tool;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 足迹业务处理类
 * 
 * @author 林玉生
 * 
 * @since 2014-11-03
 * 
 * @version v1.0
 *
 */
@Service
public class FootprintService {
	
	@Autowired
	private FootprintDAO footprintDAO;
	
	/**
	 * 根据主键查找足迹
	 * 
	 * @param footprintId
	 * 
	 * @return
	 */
	public Footprint get(Integer footprintId) {
		return footprintDAO.findById(footprintId);
	}
	/**
	 * 保存足迹
	 * 
	 * @param footprint
	 */
	public Integer save(Footprint footprint) {
		return (Integer)footprintDAO.save(footprint);
	}
	/**
	 * 删除足迹
	 * 
	 * @param footprint
	 */
	public void delete(Integer footprintId) {
		footprintDAO.delete(footprintDAO.findById(footprintId));
	}
	/**
	 * 复制list集合
	 * 
	 * @param list
	 * 
	 * @return
	 */
	public List<EFootprint> copyList(List<Footprint> list) {
		List<EFootprint> eList = new ArrayList<EFootprint>();
		for (Footprint footprint : list) {
			EFootprint eFootprint = new EFootprint();
			String[] ignore = {"footprintTime"};
			BeanUtils.copyProperties(footprint, eFootprint,ignore);
			eFootprint.setUserId(footprint.getUser().getUserId());
			eFootprint.setUserNickname(footprint.getUser().getUserNickname());
			eFootprint.setUserHead(footprint.getUser().getUserHead());
			eFootprint.setActivitiesId(footprint.getActivities().getActivitiesId());
			eFootprint.setFootprintTime(Tool.timestampToString(footprint.getFootprintTime()));
			//复制照片url集合到EFootPrint对象的photoStream属性
			List<String> photoStream = new ArrayList<String>();
			Set<Photo> photos = footprint.getPhotos();
			for (Photo photo : photos) {
				photoStream.add(photo.getPhotoStream());
			}
			eFootprint.setPhotoStream(photoStream);
			eList.add(eFootprint);
		}
		return eList;
	}
	/**
	 * 加载更多足迹
	 * 
	 * @param activitiesId
	 * 
	 * @return
	 */
	public List<Footprint> findMore(Integer activitiesId,Integer footprintId,PageBean pageBean) {
		String selectHql = "";
		String countHql = "";
		//判断是否为第一次浏览足迹
		if (footprintId == 0) {
			selectHql = "from Footprint where activities.activitiesId = '" + activitiesId + "' order by footprintId desc";
		}else {
			selectHql = "from Footprint where activities.activitiesId = '" + activitiesId + "' and footprintId < '" + footprintId + "' order by footprintId desc";
		}
		//System.out.println("浏览足迹："+selectHql);
		countHql="select count(*) "+selectHql;
		List<Footprint> list = PageService.searchByPage(selectHql, countHql, null, pageBean, footprintDAO);
		return list;
	}
	/**
	 * 刷新足迹
	 * @param activitiesId
	 * @param footprintId
	 * @return
	 */
	public List<Footprint> findNewest(Integer activitiesId,Integer footprintId) {
		String selectHql = "";
		selectHql = "from Footprint where activities.activitiesId = '" + activitiesId + "' and footprintId > '" + footprintId + "' order by footprintId desc";
		//System.out.println("刷新足迹："+selectHql);
		List<Footprint> list = PageService.searchByPage(selectHql, null, null, null, footprintDAO);
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
