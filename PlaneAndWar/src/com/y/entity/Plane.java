package com.y.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;

import com.y.action.ActionAble;
import com.y.client.GameClient;
import com.y.constant.Constant;
import com.y.util.GetImageUtil;
import com.y.util.SinglePlay;

/**
* @ClassName: Plane
* @Description:飞机类
* @author gjy
* @date 2019��8��19�� ����8:40:31
*
*/
public class Plane  extends GameObj implements ActionAble{

	public Plane() {
	}
	// 添加客户端
	public GameClient gc;
	// 区分敌机
	public boolean isGood;
	// 子弹等级
	public int BulletLevel = 1;
	// 飞机血量
	public int blood;
	// 创建音乐对象 
	SinglePlay play = new SinglePlay();
	public Plane(int x,int y,String imgName,GameClient gc,boolean isGood) {		
		this.x = x;
		this.y = y;
		this.img = GetImageUtil.getImg(imgName);
		this.speed = 20;
		this.gc = gc;
		this.isGood = isGood;
		this.blood = 100;
	}
	// 定义速度
	private int speed;
	// 添加方向
	private boolean left,up,right,down;
	// 移动方法
	@Override
	public void move() {
		if(left) {
			x-=speed;
		}
		if(right) {
			x+=speed;
		}
		if(up) {
			y-=speed;
		}
		if(down) {
			y+=speed;
		}
		outOfBound();
	}

	@Override
	// ��һ���ɻ�
	public void draw(Graphics g) {

		g.drawImage(img, x, y, null);
		move();
		g.drawString("��ǰѪ��: "+blood, 10, 190);
	}
	// ����ɻ��߽�����
	public void outOfBound(){
		if(y<=30) {
			y=20;
		}
		if(x<=5) {
			x=0;
		}
		if(x>=Constant.GAME_WIDTH-img.getWidth(null)) {
			x = Constant.GAME_WIDTH-img.getWidth(null); 
		}
		if(y>=Constant.GAME_HIGHT-img.getHeight(null)) {
			y = Constant.GAME_HIGHT-img.getHeight(null);
		}
	}
	// ���̰���
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				left = true;
				break;
			case KeyEvent.VK_UP:
				up = true;
				break;
			case KeyEvent.VK_RIGHT:
				right = true;
				break;
			case KeyEvent.VK_DOWN:
				down = true;
				break;
		
			default :
				break;
		}
	}	
	// �����ɿ�
		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode()) {

			case KeyEvent.VK_LEFT:
				left = false;
				break;
			case KeyEvent.VK_UP:
				up = false;
				break;
			case KeyEvent.VK_RIGHT:
				right = false;
				break;
			case KeyEvent.VK_DOWN:
				down = false;
				break;
			case KeyEvent.VK_X:
				fire();
				break;
			default :
				break;
		}
		
	}
   // �����ӵ�
		public void fire() {
			play.play("com/y/sound/enemy3_down.mp3");
			Bullet b = new Bullet(x+this.img.getWidth(null)/2-16,y-this.img.getHeight(null)/2+50,"bullet/0"+BulletLevel+".png",gc,true);
			if(gc.planes.size()!=0) {
			gc.bullets.add(b);
			}
		}
   // ��ȡ�ɻ�����	
		public Rectangle getRec() {
			return new Rectangle(x,y,this.img.getWidth(null),this.img.getHeight(null));
		}
   // ����ҷ���ɫ��ײ������
		public void containItem(Prop prop) {
			if(this.getRec().intersects(prop.getRect())) {
   // �Ƴ�����			
				gc.props.remove(prop);
   // �����ӵ��ȼ�
				if(BulletLevel>6) {
					BulletLevel = 7;
					return;
				}
				this.BulletLevel +=1;
			}
		}
   // ����ҷ���ײ�������
		public void containItems(List<Prop> props) {
			if(props==null) {
				return;
			}else {
				for(int i=0;i<props.size();i++) {
					containItem(props.get(i));
				}
			}
		}
		
}
