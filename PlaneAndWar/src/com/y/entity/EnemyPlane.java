package com.y.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import com.y.action.ActionAble;
import com.y.client.GameClient;
import com.y.util.GetImageUtil;

public class EnemyPlane extends Plane implements ActionAble{

	private Integer enemyType;
	private Integer speed;
	private GameClient gc;
	public static Image[] imgs1 = {
			GetImageUtil.getImg("enemy/a4-2-bai.png"),
			GetImageUtil.getImg("enemy/a4-2.png"),
			GetImageUtil.getImg("enemy/a4-3-bai.png"),
			GetImageUtil.getImg("enemy/a4-3.png"),
			GetImageUtil.getImg("enemy/a4-4-bai.png")
	};
	
	public EnemyPlane() {
	}
	public EnemyPlane(int x, int y,int enemyType,GameClient gc,boolean isGood) {
		this.x = x;
		this.y = y; 
		this.speed = 1;
		this.enemyType = enemyType;
		this.gc = gc;
		this.isGood = isGood;
	}
	@Override
	public void move() {
		y += speed;	
	}
	int count = 0;
	@Override
	public void draw(Graphics g) {
		if(count>4) {
			count = 0;
		}
		g.drawImage(imgs1[count++],x,y,null);
			move();			
			if(random.nextInt(500)>490) {
			fire();
			}
		} 
	//敌机开火	
	public void fire() {
		Bullet bullet = new Bullet(x+70,y+100,"bullet/m1.png",gc,false);
		gc.bullets.add(bullet);
	}
	// 获取矩形
	public Rectangle getRec() {
		return new Rectangle(x+imgs1[0].getWidth(null)/2,y,this.imgs1[0].getWidth(null),this.imgs1[0].getHeight(null));
	}
	// 添加随机数
	Random random = new Random();
	}
	
