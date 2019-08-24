package com.y.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

import javax.imageio.ImageIO;

/**
* @ClassName: GetImageUtil
* @Description: 获取图片类
* @author gjy
* @date 2019年8月17日 下午2:14:49
*
*/
public class GetImageUtil {

	public GetImageUtil() {
		// TODO Auto-generated constructor stub
	}
        // 获取图片的方法
	public static Image getImg(String imgName) {
		// 反射  获取图片路径
		URL resource = GetImageUtil.class.getClassLoader().getResource("com/y/img/"+imgName);
		BufferedImage bufferedImage = null;
		try {
		// 读取图片	
			bufferedImage = ImageIO.read(resource);
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return bufferedImage;
	}
}
