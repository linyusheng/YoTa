package org.ike.yota.controller;

import java.util.List;

import org.ike.yota.model.EActivitiesType;
import org.ike.yota.service.ActivitiesTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 活动类型访问控制类(类资源访问：/activitiesType/*)
 * 
 * @author 林玉生
 * 
 * @since 2014-10-31
 * 
 * @version v1.0
 *
 */
@Controller
@RequestMapping("/activitiesType")
public class ActivitiesTypeController {
	
	@Autowired
	private ActivitiesTypeService activitiesTypeService;
	
	/**
	 * 查找出所有活动类型名
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listAll")
	public List<EActivitiesType> listAll() {
		System.out.println("请求/activitiesType/listAll");
		return activitiesTypeService.findAll();
	}

}
