package org.ike.yota.controller;

import org.ike.yota.service.ActivitiesService;
import org.ike.yota.service.AttendService;
import org.ike.yota.service.FootprintService;
import org.ike.yota.service.PhotoService;
import org.ike.yota.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理员访问控制类(类资源访问：/admin/*)
 * 
 * @author 林玉生
 * 
 * @since 2014-11-29
 * 
 * @version v1.0
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	
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
	

	

}
