package org.ike.yota.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;

import Decoder.BASE64Decoder;

public class Tool {

	/**
	 * 获取系统当前时间,返回值类型为Timestamp
	 * 
	 * @return
	 */
	public static Timestamp getCurrentTime(){
		Timestamp currentTime = null;
		//HH：24小时制时间显示  hh:12小时制时间显示
		Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String systemTime = f.format(date);
		currentTime = Timestamp.valueOf(systemTime);
		return currentTime;
	}
	/**
	 * 将时间戳(Timestamp)转化为字符串
	 * 
	 * @param timestamp
	 * 
	 * @return
	 */
	public static String timestampToString(Timestamp timestamp) {
		//HH：24小时制时间显示  hh:12小时制时间显示
		Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(timestamp.getTime());
		String time = f.format(date);
		return time;
	}
	/**
	 * 获得系统当前日期
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		//HH：24小时制时间显示  hh:12小时制时间显示
		Format f = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String time = f.format(date);
		return time;
	}
	/** 
	 * 删除单个文件 
	 * @param sPath  被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public static boolean deleteFile(String path) {  
	    boolean flag = false;  
	    File file = new File(path);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}
	/**
	 * 将图片字节字符串输出到指定目录下
	 * @param str 图片字节字符串
	 * @param path 输出路径
	 */
	public static float stringToImage(String str,String path) {
		System.out.println(path);
		float aspectRatio = 0;
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] imgByte = decoder.decodeBuffer(str);
			ByteArrayInputStream byInputStream = new ByteArrayInputStream(imgByte);
			BufferedImage bufImg = ImageIO.read(byInputStream);
			//获取图片宽高比
			int width = bufImg.getWidth();
			int height = bufImg.getHeight();
			aspectRatio = (float)width / (float)height ;
			File file = new File(path);
			ImageIO.write(bufImg, "jpg", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return aspectRatio;
	}
	
	/**
	 * 根据图片的外网地址下载图片到本地
	 * @param imgUrl 图片的外网地址
	 */
	public static String downImages(String imgUrl) {
		String currentDate = Tool.getCurrentDate();
		String filePath = System.getProperty("web.root")+"images" + File.separatorChar + "head" + File.separatorChar + currentDate + File.separatorChar;
		String fileName = UUID.randomUUID().toString() + ".jpg";
		try {
			//创建文件目录
			File files = new File(filePath);
			if (!files.exists()) {
				files.mkdirs();
			}
			//获取下载地址
			URL url = new URL(imgUrl);
			//链接网络地址
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			//获取链接的输出流
			InputStream is = connection.getInputStream();
			//创建文件
			File file = new File(filePath + fileName);
			//根据输入流写入文件
			FileOutputStream out = new FileOutputStream(file);
			int i = 0;
			while((i = is.read()) != -1){
				out.write(i);
			}
			out.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/images/head/" + currentDate + "/" + fileName;
	}

}
