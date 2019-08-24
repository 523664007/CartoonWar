package com.y.constant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



/**
* @ClassName: Constant
* @Description: ������
* @author gjy
* @date 2019��8��17�� ����1:53:49
*
*/
public class Constant {

	//��ζ�ȡ�����ļ�
	public static Properties prop = new Properties();
	static Integer Game_Width=null;
	static Integer Game_Hight=null;
	static
	{
		InputStream inStream = Constant.class.getResourceAsStream("/gameConfiguration.properties");
		try {
			prop.load(inStream);
			Game_Width = Integer.parseInt(prop.getProperty("Game_Width"));
			Game_Hight = Integer.parseInt(prop.getProperty("Game_Hight"));
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	 // ������
	public static final int GAME_WIDTH = Game_Width;
	
	 // ����߶�
	public static final int GAME_HIGHT = Game_Hight;
}
