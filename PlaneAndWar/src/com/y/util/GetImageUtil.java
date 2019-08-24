package com.y.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

import javax.imageio.ImageIO;

/**
* @ClassName: GetImageUtil
* @Description: ��ȡͼƬ��
* @author gjy
* @date 2019��8��17�� ����2:14:49
*
*/
public class GetImageUtil {

	public GetImageUtil() {
		// TODO Auto-generated constructor stub
	}
        // ��ȡͼƬ�ķ���
	public static Image getImg(String imgName) {
		// ����  ��ȡͼƬ·��
		URL resource = GetImageUtil.class.getClassLoader().getResource("com/y/img/"+imgName);
		BufferedImage bufferedImage = null;
		try {
		// ��ȡͼƬ	
			bufferedImage = ImageIO.read(resource);
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return bufferedImage;
	}
}
