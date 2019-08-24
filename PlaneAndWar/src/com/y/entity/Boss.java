package com.y.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import com.y.action.ActionAble;
import com.y.client.GameClient;
import com.y.constant.Constant;
import com.y.util.GetImageUtil;

/**
* @ClassName: Boss
* @Description: Boss ��
* @author gjy
* @date 2019��8��21�� ����2:28:31
*
*/
public class Boss extends Plane implements ActionAble {
	
	
	private boolean up = true;
	// boss图片
	private static Image[] imgs = new Image[8];
	static {
		for(int i = 0;i<imgs.length;i++) {
			imgs[i] = GetImageUtil.getImg("boss/A0"+(i+1)+".png");
		}
	}
	private int speed = 2;
	public Boss() {		
	}
	public Boss(int x, int y,GameClient gc,boolean isGood) {
			this.x = x;
			this.y = y;
			this.gc = gc;
			this.isGood = isGood;
			this.blood = 2000;
		}
	int count = 0;
     @Override
    public void draw(Graphics g) {
    	 if(count>7) {
    		 count = 0;
    	 }
    	 g.drawImage(imgs[count++], x, y, null);
    	  move(); 
    	  g.drawString("当前血量:"+blood, 10, 130);
}    
   @Override
    public void move() {
	   y += speed;
	   if(y>(300-imgs[0].getHeight(null))) {
		   y=300-imgs[0].getHeight(null);
		   if(up) {
	    		x -= speed;
	    	}
		   if(!up){
			   x += speed;
		   }
		   if(x > Constant.GAME_WIDTH-imgs[0].getWidth(null)) {
			   up = true;
		   }
		   if(x<30) {
			   up = false;
		   }
	   }	   
	   if(random.nextInt(500)>480) {
		   fire();
	   }
    }  
  // 生成随机数
   Random random = new Random();
  // 获取Boss矩形	
		public Rectangle getRec() {
			return new Rectangle(x,y,this.imgs[0].getWidth(null),this.imgs[0].getHeight(null));
		} 
  // Boss开火
		public void fire() {
			play.play("com/y/sound/enemy3_down.mp3");
			Bullet b = new Bullet(x+this.imgs[0].getWidth(null)-50,y+this.imgs[0].getHeight(null),"bullet/m2.png",gc,false);
			gc.bullets.add(b);
		}
}