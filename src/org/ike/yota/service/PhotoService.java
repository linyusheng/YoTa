package org.ike.yota.service;

import org.ike.yota.entity.Photo;
import org.ike.yota.entity.PhotoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 照片业务处理类
 * 
 * @author 林玉生
 * 
 * @since 2014-11-03
 * 
 * @version v1.0
 *
 */
@Service
public class PhotoService {
	
	@Autowired
	private PhotoDAO photoDAO;
	
	/**
	 * 根据主键查找对象
	 * 
	 * @param photoId
	 * 
	 * @return
	 */
	public Photo get(Integer photoId) {
		return photoDAO.findById(photoId);
	}
	/**
	 * 保存或修改对象
	 * 
	 * @param photo
	 */
	public void save(Photo photo) {
		photoDAO.merge(photo);
	}
	/**
	 * 根据主键删除对象
	 * 
	 * @param photo
	 */
	public void delete(Integer photoId){
		photoDAO.delete(photoDAO.findById(photoId));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
