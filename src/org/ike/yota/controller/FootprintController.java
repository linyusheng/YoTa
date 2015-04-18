package org.ike.yota.controller;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.ike.yota.entity.Activities;
import org.ike.yota.entity.Footprint;
import org.ike.yota.entity.Photo;
import org.ike.yota.entity.User;
import org.ike.yota.model.EFootprint;
import org.ike.yota.service.FootprintService;
import org.ike.yota.service.PhotoService;
import org.ike.yota.util.PageBean;
import org.ike.yota.util.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 足迹访问控制类(类资源访问：/footprint/*)
 * 
 * @author 林玉生
 * 
 * @since 2014-11-04
 * 
 * @version v1.0
 *
 */
@Controller
@RequestMapping("/footprint")
public class FootprintController {
	
	@Autowired
	private FootprintService footprintService;
	@Autowired
	private PhotoService photoService;
	
	/**
	 * 添加足迹
	 * 
	 * @param jsonString
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/add")
	public String add(String jsonString) {
		System.out.println("请求/footprint/add：" + jsonString);
		EFootprint eFootprint = JSON.parseObject(jsonString, EFootprint.class);
		Footprint footprint = new Footprint();
		User user = new User();
		Activities activities = new Activities();
		user.setUserId(eFootprint.getUserId());
		activities.setActivitiesId(eFootprint.getActivitiesId());
		footprint.setUser(user);
		footprint.setActivities(activities);
		footprint.setFootprintMood(eFootprint.getFootprintMood());
		footprint.setFootprintTime(Tool.getCurrentTime());
		Integer footprintId = footprintService.save(footprint);
		footprint.setFootprintId(footprintId);
		//保存足迹照片
		Photo photo = new Photo();
		photo.setFootprint(footprint);
		List<String> photoStreams = eFootprint.getPhotoStream();
		for (String string : photoStreams) {
			photo.setPhotoStream(savePhoto(string));
			photoService.save(photo);
		}
		return "发表成功！";
	}
	/**
	 * 保存照片，将照片字节数组输出到服务器端存储，返回值为保存路径 （/images/photo/当天日期/文件名）
	 * 
	 * @param photoStream
	 * 
	 * @return
	 */
	public String savePhoto(String photoStream) {
		String currentDate = Tool.getCurrentDate();
		String webRoot = System.getProperty("web.root");
		String saveDir = File.separatorChar + "images" + File.separatorChar + "photo" + File.separatorChar + currentDate + File.separatorChar;
		File file = new File(webRoot + saveDir);
		//如果文件夹不存在则创建
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
		String fileName = UUID.randomUUID().toString() + ".jpg";
		Tool.stringToImage(photoStream, webRoot + saveDir + fileName);
		return "/images/photo/" + currentDate + "/" + fileName;
	}
	/**
	 * 查找本活动的足迹(分页查找)
	 * 
	 * @param jsonString
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listMore")
	public List<EFootprint> listMore(String jsonString) {
		System.out.println("请求/footprint/listMore：" + jsonString);
		JSONObject object = JSON.parseObject(jsonString);
		Integer activitiesId = (Integer)object.get("eActivitiesId");
		Integer footprintId = (Integer)object.get("eFootprintId");
		PageBean pageBean = new PageBean();
		List<Footprint> list = footprintService.findMore(activitiesId,footprintId,pageBean);
		List<EFootprint> eList = footprintService.copyList(list);
		return eList;
	}
	/**
	 * 加载最新的足迹
	 * 
	 * @param jsonString
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listNewest")
	public List<EFootprint> listNewest(String jsonString) {
		System.out.println("请求/footprint/listNewest：" + jsonString);
		JSONObject object = JSON.parseObject(jsonString);
		Integer activitiesId = (Integer)object.get("eActivitiesId");
		Integer footprintId = (Integer)object.get("eFootprintId");
		List<Footprint> list = footprintService.findNewest(activitiesId, footprintId);
		List<EFootprint> eList = footprintService.copyList(list);
		return eList;
	}
	/**
	 * 根据足迹ID删除足迹
	 * 
	 * @param jsonString
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(String jsonString) {
		System.out.println("请求/footprint/delete：" + jsonString);
		JSONObject object = JSON.parseObject(jsonString);
		Integer footprintId = (Integer)object.get("footprintId");
		Footprint footprint = footprintService.get(footprintId);
		Set<Photo> photos = footprint.getPhotos();
		for (Photo photo : photos) {
			String path = System.getProperty("web.root") + photo.getPhotoStream();
			Tool.deleteFile(path);
			photoService.delete(photo.getPhotoId());
		}
		footprintService.delete(footprintId);
		return "删除成功！";
	}


}
