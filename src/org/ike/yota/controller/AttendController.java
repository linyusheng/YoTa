package org.ike.yota.controller;

import java.util.ArrayList;
import java.util.List;

import org.ike.yota.entity.Activities;
import org.ike.yota.entity.Attend;
import org.ike.yota.entity.User;
import org.ike.yota.model.EUser;
import org.ike.yota.service.ActivitiesService;
import org.ike.yota.service.AttendService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 参与活动访问控制类(类资源访问：/attend/*)
 * 
 * @author 林玉生
 * 
 * @since 2014-11-02
 * 
 * @version v1.0
 *
 */
@Controller
@RequestMapping("/attend")
public class AttendController {
	
	@Autowired
	private AttendService attendService;
	
	@Autowired
	private ActivitiesService activitiesService;
	
	/**
	 * 用户参与或退出活动
	 * 
	 * @param userId
	 * 
	 * @param activitiesId
	 */
	@ResponseBody
	@RequestMapping("/addOrdelete")
	public String addOrdelete(String jsonString) {
		System.out.println("请求/attend/addOrdelete：" + jsonString);
		JSONObject object = JSON.parseObject(jsonString);
		Integer userId = (Integer)object.get("eUserId");
		Integer activitiesId = (Integer)object.get("eActivitiesId");
		Activities activities = null;
		String result = "";
		//查找是否有参与记录
		Attend attend = attendService.isAttend(userId, activitiesId);
		if (attend == null) {
			User user = new User();
			activities = new Activities();
			user.setUserId(userId);
			activities.setActivitiesId(activitiesId);
			attend = new Attend(user,activities);
			attendService.save(attend);
			result = "exit";
		}else {
			attendService.delete(attend.getAttendId());
			result = "attend";
		}
		return result;
	}
	/**
	 * 根据活动Id列出所有参与者
	 * 
	 * @param jsonString
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listAttender")
	public List<EUser> listAttender(String jsonString) {
		System.out.println("请求/attend/listAttender：" + jsonString);
		JSONObject object = JSON.parseObject(jsonString);
		Integer activitiesId = (Integer)object.get("activitiesId");
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
	 * 判断某用户是否已参加某活动
	 * 
	 * @param userId
	 * @param activitiesId
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/isAttend")
	public String isAttend(String jsonString) {
		System.out.println("请求/attend/isAttend：" + jsonString);
		JSONObject object = JSON.parseObject(jsonString);
		Integer userId = (Integer)object.get("eUserId");
		Integer activitiesId = (Integer)object.get("eActivitiesId");
		Attend attend = attendService.isAttend(userId, activitiesId);
		if (attend == null) {
			return "0";
		}
		return "1";
	}
	
	
	
	
	
	
	
	
	
	
	
}
