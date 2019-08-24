package com.y.entity;

import java.awt.Graphics;

import com.y.action.ActionAble;
import com.y.util.GetImageUtil;

/**
* @ClassName: BackGround
* @Description: 背景类
* @author gjy
* @date 2019年8月19日 下午2:24:22
*
*/
public class BackGround extends GameObj implements ActionAble{

	private Integer speed;

	public BackGround() {
		// TODO Auto-generated constructor stub
	}

	public BackGround(int x, int y, String imgName) {
		
		this.x = x;
		this.y = y;
		this.img = GetImageUtil.getImg(imgName);
		this.speed = 5;
	}
	@Override
	public void move() {
		y+= speed;
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
		move();
	}
	

}
