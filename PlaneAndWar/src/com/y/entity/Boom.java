package com.y.entity;

import java.awt.Graphics;
import java.awt.Image;

import com.y.action.ActionAble;
import com.y.client.GameClient;
import com.y.util.GetImageUtil;


/**
* @ClassName: Boom
* @Description: 爆炸类
* @author gjy
* @date 2019��8��20�� ����10:09:42
*
*/
public class Boom extends GameObj implements ActionAble{
	
	private boolean isLive;
	private GameClient gc;
	private static Image[] boomImgs= new Image[5];
	
	
	public boolean isLive() {
		return isLive;
	}
	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}
	// 爆炸图片
	static {
		for(int i = 0;i<5;i++) {
			boomImgs[i]=GetImageUtil.getImg("Boom/copy/"+(i+1)+".png");
		}
	}
	public Boom() {	
	}
	public Boom(int x, int y,GameClient gc) {
		this.x = x;
		this.y = y;
		this.isLive = true;
		this.gc = gc;
	}
	@Override
	public void move() {		
	}	
	int count = 0;
	@Override
	public void draw(Graphics g) {
		if(isLive) {
			if(count>4) {
				gc.booms.remove(this);
				this.setLive(false);
				return;
			}
				g.drawImage(boomImgs[count++],x,y,null);		
		}
  }

}
