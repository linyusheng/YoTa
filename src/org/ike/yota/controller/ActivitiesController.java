package org.ike.yota.controller;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.ike.yota.entity.Activities;
import org.ike.yota.entity.ActivitiesType;
import org.ike.yota.entity.Attend;
import org.ike.yota.entity.Footprint;
import org.ike.yota.entity.Photo;
import org.ike.yota.entity.User;
import org.ike.yota.model.EActivities;
import org.ike.yota.model.EActivitiesType;
import org.ike.yota.model.EUser;
import org.ike.yota.service.ActivitiesService;
import org.ike.yota.service.AttendService;
import org.ike.yota.service.FootprintService;
import org.ike.yota.service.PhotoService;
import org.ike.yota.service.UserService;
import org.ike.yota.util.PageBean;
import org.ike.yota.util.Tool;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 活动访问控制类(类资源访问：/activities/*)
 * 
 * @author 林玉生
 * 
 * @since 2014-10-28
 * 
 * @version v1.0
 *
 */
@Controller
@RequestMapping("/activities")
public class ActivitiesController {
	
	@Autowired
	private ActivitiesService activitiesService;
	@Autowired
	private AttendService attendService;
	@Autowired
	private UserService userService;
	@Autowired
	private FootprintService footprintService;
	@Autowired
	private PhotoService photoService;
	
	/**
	 * 发布活动
	 * 
	 * @param json
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/add")
	public String add(String jsonString) {
		System.out.println("请求/activities/add：" + jsonString);
		EActivities eActivities = JSON.parseObject(jsonString,EActivities.class);
		Activities activities = copyEntity(eActivities);
		//保存活动对象
		Integer activitiesId = (Integer)activitiesService.save(activities);
		addAttend(eActivities.getUserId(), activitiesId);
		return "发布成功！";
	}
	/**
	 * 将实体EActivities还原回实体Activities
	 * 
	 * @param eActivities
	 * 
	 * @return
	 */
	public Activities copyEntity(EActivities eActivities) {
		Activities activities = new Activities();
		ActivitiesType activitiesType = new ActivitiesType();
		User user = new User();
		//忽略复制的属性
		String[] ignores = {"activitiesPoster"};
		BeanUtils.copyProperties(eActivities, activities,ignores);
		//设置系统默认属性
		user.setUserId(eActivities.getUserId());
		activitiesType.setTypeId(eActivities.getActivitiesTypeId());
		activities.setUser(user);
		activities.setActivitiesType(activitiesType);
		activities.setActivitiesDate(Tool.getCurrentTime());
		activities.setStartTime(Timestamp.valueOf(eActivities.getStartDateAndTime()));
		activities.setEndTime(Timestamp.valueOf(eActivities.getEndDateAndTime()));
		activities.setActivitiesLevel(1);
		activities.setActivitiesNumber(1);
		activities.setActivitiesState(1);
		//将海报字节数组输出到服务器指定目录下
		String poster = eActivities.getActivitiesPoster();
		//判断海报字节数组是否为空
		if (poster != null) {
			Map<String, Object> map = savePoster(poster);
			activities.setActivitiesPoster((String)map.get("savePath"));
			activities.setAspectRatio((Float)map.get("aspectRatio"));
		}
		return activities;
	}
	/**
	 * 保存海报，将图片字节数组输出到服务器端存储
	 * 返回值	1、图片宽高比	2、保存路径 （/images/poster/当天日期/文件名）
	 * 
	 * @param poster
	 * 
	 * @return
	 */
	public Map<String, Object> savePoster(String poster) {
		String currentDate = Tool.getCurrentDate();
		String webRoot = System.getProperty("web.root");
		String saveDir = File.separatorChar + "images" + File.separatorChar + "poster" + File.separatorChar + currentDate + File.separatorChar;
		File file = new File(webRoot + saveDir);
		//如果文件夹不存在则创建
		if (!file.exists() && !file.isDirectory()) {
			file.mkdir();
		}
		String fileName = UUID.randomUUID().toString() + ".jpg";
		float aspectRatio = Tool.stringToImage(poster, webRoot + saveDir + fileName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("aspectRatio", aspectRatio);
		map.put("savePath", "/images/poster/" + currentDate + "/" + fileName);
		return map;
	}
	/**
	 * 当用户发布活动时，添加一条用户（创建者）参与活动记录
	 * 
	 * @param userId
	 * 
	 * @param activities
	 */
	public void addAttend(Integer userId,Integer activitiesId) {
		User user = new User();
		Activities activities = new Activities();
		user.setUserId(userId);
		activities.setActivitiesId(activitiesId);
		Attend attend = new Attend(user,activities);
		attendService.save(attend);
	}
	/**
	 * 加载更多活动
	 * 
	 * @param jsonString
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listMore")
	public List<EActivities> listMore(String jsonString) {
		System.out.println("请求/activities/listMore：" + jsonString);
		JSONObject object = JSON.parseObject(jsonString);
		Integer activitiesId = (Integer)object.get("eActivitiesId");
		String city = (String)object.get("city");
		List<EActivitiesType> typeList = JSONArray.parseArray(object.getString("typeList"),EActivitiesType.class);
		PageBean pageBean = new PageBean();
		List<Activities> list = activitiesService.findMore(activitiesId,city,typeList,pageBean);
		List<EActivities> eList = activitiesService.copyList(list);
		return eList;
	}
	/**
	 * 加载最新的活动
	 * 
	 * @param jsonString
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listNewest")
	public List<EActivities> listNewest(String jsonString) {
		System.out.println("请求/activities/listNewest：" + jsonString);
		JSONObject object = JSON.parseObject(jsonString);
		Integer activitiesId = (Integer)object.get("eActivitiesId");
		String city = (String)object.get("city");
		List<EActivitiesType> typeList = JSONArray.parseArray(object.getString("typeList"),EActivitiesType.class);
		List<Activities> list = activitiesService.findNewest(activitiesId,city,typeList);
		List<EActivities> eList = activitiesService.copyList(list);
		return eList;
	}
	/**
	 * 根据用户Id查找自己参与的活动（分页回传）
	 * 
	 * @param jsonString
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listMyActivities")
	public List<EActivities> listMyActivities(String jsonString){
		System.out.println("请求/activities/listMyActivities：" + jsonString);
		JSONObject object = JSON.parseObject(jsonString);
		Integer userId = (Integer)object.get("userId");
		Integer activitiesId = (Integer)object.get("eActivitiesId");
		List<Activities> list = new ArrayList<Activities>();
		PageBean pageBean = new PageBean();
		List<Attend> attends = attendService.findByUserId(userId,activitiesId,pageBean);
		for (Attend attend : attends) {
			list.add(attend.getActivities());
		}
		List<EActivities> eList = activitiesService.copyList(list);
		return eList;
	}
	/**
	 * 根据userId或者openId查找自己参与的活动(一次性回传)
	 * 
	 * @param jsonString
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findMyActivities")
	public List<EActivities> findMyActivities(String jsonString){
		System.out.println("请求/activities/findMyActivities：" + jsonString);
		JSONObject object = JSON.parseObject(jsonString);
		Object id = object.get("id");
		List<Activities> list = new ArrayList<Activities>();
		List<Attend> attends = attendService.findMyActivities(id);
		for (Attend attend : attends) {
			list.add(attend.getActivities());
		}
		List<EActivities> eList = activitiesService.copyList(list);
		return eList;
	}
	/**
	 * 根据活动Id判断是否存在活动
	 * 
	 * @param jsonString
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/isExistActivities")
	public Map<String, String> isExistActivities(String jsonString) {
		System.out.println("请求/activities/isExistActivities：" + jsonString);
		JSONObject object = JSON.parseObject(jsonString);
		Integer activitiesId = (Integer)object.get("eActivitiesId");
		Activities activities = activitiesService.get(activitiesId);
		Map<String, String> map = new HashMap<String, String>();
		if (activities == null) {
			map.put("status", "该活动已经取消！");
		}
		return map;
	}
	/**
	 * 根据活动Id查找活动
	 * 
	 * @param jsonString
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/activitiesInfo")
	public Map<String, Object> activitiesInfo(String jsonString) {
		System.out.println("请求/activities/activitiesInfo：" + jsonString);
		JSONObject object = JSON.parseObject(jsonString);
		Integer userId = (Integer)object.get("eUserId");
		Integer activitiesId = (Integer)object.get("eActivitiesId");
		Activities activities = activitiesService.get(activitiesId);
		//回传两个值，1：活动实体 2、活动参与人集合
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("eActivities", activitiesService.copyObject(activities));
		map.put("userList", listAttender(activitiesId));
		map.put("isAttend", attendService.isAttend(userId, activitiesId)==null?false:true);
		return map;
	}
	/**
	 * 根据活动Id列出所有参与者(包括创建者)
	 * 
	 * @param jsonString
	 * 
	 * @return
	 */
	public List<EUser> listAttender(Integer activitiesId) {
		List<Attend> list = attendService.findByActivitiesId(activitiesId);
		List<EUser> eList = new ArrayList<EUser>();
		for (Attend attend : list) {
			EUser eUser = new EUser();
			BeanUtils.copyProperties(attend.getUser(), eUser);
			eList.add(eUser);
		}
		return eList;
	}
	/**
	 * 根据活动Id删除活动
	 * 
	 * @param jsonString
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, String> delete(String jsonString) {
		System.out.println("请求/activities/delete：" + jsonString);
		JSONObject object = JSON.parseObject(jsonString);
		Integer activitiesId = (Integer)object.get("eActivitiesId");
		//删除所有的参与记录和足迹
		Activities activities = activitiesService.get(activitiesId);
		Set<Attend> attends = activities.getAttends();
		Set<Footprint> footprints = activities.getFootprints();
		for (Footprint footprint : footprints) {
			//删除足迹的照片
			Set<Photo> photos = footprint.getPhotos();
			for (Photo photo : photos) {
				String url = System.getProperty("web.root") + photo.getPhotoStream();
				Tool.deleteFile(url);
				photoService.delete(photo.getPhotoId());
			}
			footprintService.delete(footprint.getFootprintId());
		}
		for (Attend attend : attends) {
			attendService.delete(attend.getAttendId());
		}
		//删除活动海报
		String path = System.getProperty("web.root") + activities.getActivitiesPoster();
		Tool.deleteFile(path);
		activitiesService.delete(activitiesId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", "取消成功！");
		return map;
	}
	/**
	 * 查找所有活动
	 * 
	 * @param jsonString
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listAll")
	public List<EActivities> listAll(String jsonString) {
		System.out.println("请求/activities/listAll：" + jsonString);
		List<Activities> list = activitiesService.findAll();
		List<EActivities> eList = activitiesService.copyList(list);
		return eList;
	}
	/**
	 * 刷新热门活动
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateHot")
	public String updateHot() {
		System.out.println("请求/activities/updateHot");
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(3);
		activitiesService.refreshHotActivities(pageBean);
		return "刷新热门活动成功！";
	}
	/**
	 * 刷新推广活动
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateExtension")
	public String updateExtension() {
		int[] ids = {3,12,17};
		activitiesService.refreshExtensionActivities(ids);
		return "刷新推广活动成功！";
	}

}
