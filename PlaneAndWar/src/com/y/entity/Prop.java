package com.y.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.y.action.ActionAble;
import com.y.constant.Constant;
import com.y.util.GetImageUtil;

/**
* @ClassName: Prop
* @Description:道具类
* @author gjy
* @date 2019��8��20�� ����6:25:15
*
*/
public class Prop extends GameObj implements ActionAble{
	private int speed;
	public Prop() {	
	}
	
	public Prop(int x,int y,String imgName) {
		this.x = x;
		this.y = y;
		this.img = GetImageUtil.getImg(imgName);
		this.speed = 5;
	}
	
	private double theta = Math.PI/4;
	@Override
	public void move() {
		x += speed*Math.cos(theta);
		y += speed*Math.cos(theta);
		if(x<50||x>Constant.GAME_WIDTH-img.getWidth(null)) {
			theta = Math.PI-theta;	
		}
		if(y<50||y>Constant.GAME_HIGHT-img.getHeight(null)) {
			theta = -theta;
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
		move();	
	}
	// 获得道具矩形
	public Rectangle getRect() {
		return new Rectangle(x, y, this.img.getWidth(null), this.img.getHeight(null));
	}

}
