package com.y.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

import com.y.action.ActionAble;
import com.y.client.GameClient;
import com.y.constant.Constant;
import com.y.util.GetImageUtil;
import com.y.util.SinglePlay;

/**
* @ClassName: Bullet
* @Description:子弹类
* @author gjy
* @date 2019��8��19�� ����10:51:28
*
*/
public class Bullet extends GameObj implements ActionAble{
	
	// 创建音乐对象
	SinglePlay singlePlay = new SinglePlay();
	
	// 定义速度
	private Integer speed;
	// 添加客户端
	public GameClient gc;
	// 区分子弹
	private boolean isGood;
	public Bullet() {
		
	}
	
	public Bullet(int x,int y, String imgName,GameClient gc,boolean isGood) {
		this.x = x;
		this.y = y;
		this.img = GetImageUtil.getImg(imgName);
		this.speed = 25;
		this.gc = gc;
		this.isGood = isGood; 
	}
	@Override
	public void move() {
		if(isGood) {
			y -= speed;
		}else {
			y+=speed;
		}
		outOfBounds();		
	}
	@Override
	public void draw(Graphics g) {
		g.drawImage(img,x,y,null);
		move();		
	}
	// 子弹越界
	public void outOfBounds() {
		if(y<0||y>Constant.GAME_HIGHT) {
			gc.bullets.remove(this);			
		}
	}
	// 添加随机数
	Random random = new Random();
	// 攻击敌机 
	public boolean hitMonster (Plane plane) {
		Boom boom = new Boom(plane.x,plane.y,gc);
		if(this.getRec().intersects(plane.getRec())&&this.isGood != plane.isGood)
		{
			if(plane.isGood) {
	// 添加音乐			
				singlePlay.play("com/y/sound/enemy5_down.mp3");
    // 移除子弹			
				gc.bullets.remove(this);
    // 受伤减血
				plane.blood -=10;
				if(plane.blood == 0) {
				gc.planes.remove(plane);
				singlePlay.play("com/y/sound/game_over.mp3");
				}	
			}else {
	// 添加音乐
				singlePlay.play("com/y/sound/bullet.mp3");	
	// 添加Boss掉血			
				if(plane instanceof Boss) {
					plane.blood -= 100;
	// 销毁子弹
					gc.bullets.remove(this);
					if(plane.blood<=0) {
					gc.bosss.remove(plane);
					}
				}else {
		
	// 销毁敌机
				gc.enemys.remove(plane);
	// 销毁子弹
				gc.bullets.remove(this);
	// 随机生成道具
				if(random.nextInt(500)>300) {
					if(plane instanceof EnemyPlane) {
					Prop prop = new Prop(plane.x+EnemyPlane.imgs1[0].getWidth(null)/2,plane.y+EnemyPlane.imgs1[0].getHeight(null)/2,"Prop/p.png");
					gc.props.add(prop);
					}
					
				}
			}
				}
				gc.booms.add(boom);
				return true;
		}
		return false;
	}
	// 打多个敌人
	public boolean hitMonsters(List<Plane> planes) {
		if(planes ==null) {
			return false;
		}
		for(int i = 0;i<planes.size();i++) {
			if(hitMonster(planes.get(i))) {
				return true;
			}
		}
		return false;
	}
	// 获取子弹矩形
	public Rectangle getRec() {
		return new Rectangle(x,y,this.img.getWidth(null),this.img.getHeight(null));
	}

}
