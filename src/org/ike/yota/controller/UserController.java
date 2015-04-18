
package org.ike.yota.controller;

import java.util.List;

import org.ike.yota.entity.User;
import org.ike.yota.model.EUser;
import org.ike.yota.service.ActivitiesService;
import org.ike.yota.service.AttendService;
import org.ike.yota.service.UserService;
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
 * 用户访问控制类(类资源访问：/user/*)
 * 
 * @author 林玉生
 * 
 * @since 2014-10-31
 * 
 * @version v1.0
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ActivitiesService activitiesService;
	
	@Autowired
	private AttendService attendService;
	
	/**
	 * 用户注册
	 * 
	 * @param jsonString
	 */
	@ResponseBody
	@RequestMapping("/add")
	public EUser add(String jsonString) {
		System.out.println("请求/user/add：" + jsonString);
		EUser eUser = JSON.parseObject(jsonString, EUser.class);
		User user = new User();
		BeanUtils.copyProperties(eUser, user);
		user.setUserSignature("他很懒，什么也没留下~");
		user.setRegisterTime(Tool.getCurrentTime());
		String head = eUser.getUserHead();
		if (head != null) {
			user.setUserHead(Tool.downImages(eUser.getUserHead()));
		}
		Integer userId = userService.save(user);
		if (userId != null) {
			user = userService.get(userId);
		}
		BeanUtils.copyProperties(user, eUser);
		return eUser;
	}
	/**
	 * 根据userId或者openId查找用户
	 * 
	 * @param jsonString
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUser")
	public EUser getUser(String jsonString) {
		System.out.println("请求/user/getUser：" + jsonString);
		JSONObject object = JSON.parseObject(jsonString);
		Object id = object.get("id");
		User user = userService.getUser(id);
		EUser eUser = new EUser();
		BeanUtils.copyProperties(user, eUser);
		return eUser;
	}
	/**
	 * 根据userId或者openId数组查找多个用户信息
	 * 
	 * @param jsonString
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUsers")
	public List<EUser> getUsers(String jsonString) {
		System.out.println("请求/user/getUsers：" + jsonString);
		JSONObject object = JSON.parseObject(jsonString);
		List<Object> ids = JSONArray.parseArray(object.getString("ids"),Object.class);
		List<User> list = userService.getUsers(ids);
		List<EUser> eList = userService.copyList(list);
		return eList;
	}
	/**
	 * 查找所有用户
	 * 
	 * @param jsonString
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listAll")
	public List<EUser> listAll(String jsonString) {
		System.out.println("请求/user/listAll：" + jsonString);
		List<User> list = userService.findAll();
		List<EUser> eList =userService.copyList(list);
		return eList;
	}
	/**
	 * 根据userId、昵称模糊查找用户
	 * 
	 * @param jsonString
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/searchUser")
	public List<EUser> searchUser(String jsonString) {
		System.out.println("请求/user/searchUser：" + jsonString);
		JSONObject object = JSON.parseObject(jsonString);
		String q = (String)object.get("q");
		List<User> list = userService.searchUser(q);
		List<EUser> eList =userService.copyList(list);
		return eList;
	}
	
	

}
