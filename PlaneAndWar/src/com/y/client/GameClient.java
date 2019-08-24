package com.y.client;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import com.y.constant.Constant;
import com.y.entity.BackGround;
import com.y.entity.Boom;
import com.y.entity.Boss;
import com.y.entity.Bullet;
import com.y.entity.EnemyPlane;
import com.y.entity.Plane;
import com.y.entity.Prop;
import com.y.util.GetImageUtil;
import com.y.util.SoundPlayer;

/**
* @ClassName: GameClinent
* @Description: 客户端
* @author gjy
* @date 2019��8��17�� ����1:47:59
*
*/
public class GameClient extends Frame {
    
	// 子弹集合
	 public	List<Bullet> bullets = new ArrayList<>();
	// 背景图片
	 BackGround backImg = new BackGround(0,-5000,"plane/6.jpg");
	// 爆炸集合 
	 public	List<Boom> booms = new ArrayList<>();
	// 敌机集合
	 public List<Plane> enemys = new ArrayList<>();
	// 飞机集合
	 public List<Plane> planes = new ArrayList<>();
	// 道具集合
	 public List<Prop> props = new ArrayList<>();
	// BOSS集合
	 public List<Plane> bosss = new ArrayList<>();
 	// 解决屏幕闪烁
	public void update(Graphics g) {
		Image backImg = createImage(Constant.GAME_WIDTH,Constant.GAME_HIGHT);
		Graphics backg = backImg.getGraphics();
		Color color = backg.getColor();
		backg.setColor(Color.WHITE);
		backg.fillRect(0,0,Constant.GAME_WIDTH,Constant.GAME_HIGHT);
		backg.setColor(color);
		paint(backg);
		g.drawImage(backImg,0,0,null);
		}
	// 飞机实例化
	  Plane plane = null;
	// 生成窗口
	public void launchFrame() {
	// 添加音乐	
		SoundPlayer soundPlayer = new SoundPlayer("com/y/sound/game_music.mp3");
		soundPlayer.start();
    // 添加宽高
	    this.setSize(Constant.GAME_WIDTH,Constant.GAME_HIGHT);
    // 添加标题
		this.setTitle("飞机大战");
	// 	
		this.setVisible(true);
	// 添加图标	
		Image img = GetImageUtil.getImg("icon.png");
		this.setIconImage(img);
	// 显示窗口
		this.setResizable(false);
	// 窗口居中
		this.setLocationRelativeTo(null);		
	// 关闭窗口
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
	// 鼠标监听	
//		this.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				System.out.println("���������");
//			}
//		});
    //  添加飞机
		plane = new Plane(350,950,"plane/hero0.png",this,true);
		planes.add(plane);	
	// 键盘监听
		this.addKeyListener(new KeyAdapter() {
	// 键盘按下		
			public void keyPressed(KeyEvent e) {
				plane.keyPressed(e);
			}
	// 键盘松开		
			public void keyReleased(KeyEvent e) {
				plane.keyReleased(e);
			}
		});
	// 创建线程	
		new paintThread().start();
		
	// 创建敌机
	// 添加敌机
		 EnemyPlane enemy1 = new EnemyPlane(50, 50, 1, this, false);
		 EnemyPlane enemy2 = new EnemyPlane(250, 50, 1, this, false);
		 EnemyPlane enemy3 = new EnemyPlane(450, 50, 1, this, false);
		 EnemyPlane enemy4 = new EnemyPlane(600, 50, 1, this, false);
		 EnemyPlane enemy5 = new EnemyPlane(250, 350, 1, this, false);
		 EnemyPlane enemy6 = new EnemyPlane(450, 350, 1, this, false);
		 EnemyPlane enemy7 = new EnemyPlane(600, 350, 1, this, false);
		 enemys.add(enemy1);
		 enemys.add(enemy2);
		 enemys.add(enemy3);
		 enemys.add(enemy4);
		 enemys.add(enemy5);
		 enemys.add(enemy6);
		 enemys.add(enemy7);
	// 添加boss
		 bosss.add(new Boss(300,-300, this,false));
	}

	// 重写paint
	@Override
	public void paint(Graphics g) {
		backImg.draw(g);
		g.drawLine(0, 300, 800, 300);
		
	// 循环画机
	for(int i = 0;i<planes.size();i++) {
				Plane plane2 = planes.get(i);			
					plane2.draw(g);
					plane2.containItems(props);
					}
	// 循环子弹
		for(int i = 0;i<bullets.size();i++) {
			
			Bullet bullet = bullets.get(i);
			bullet.draw(g);
			bullet.hitMonsters(enemys);
			bullet.hitMonsters(planes);
			bullet.hitMonsters(bosss);
		}
		g.drawString("当前子弹数量:"+bullets.size(),10,50);
		g.drawString("当前敌人数量"+enemys.size(), 10, 70);
		g.drawString("当前Boss数量"+bosss.size(), 10, 90);
		g.drawString("当前飞机数量"+planes.size(), 10, 110);
		g.drawString("当前道具数量"+props.size(), 10, 150);
	
		
	// 循环敌机
	    for(int i = 0;i<enemys.size();i++) {
	    	enemys.get(i).draw(g);
	    }
	// 循环道具
	    for(int i = 0;i<props.size();i++) {
	    	props.get(i).draw(g);
	    }
	 // 循环boss
	    for(int i = 0;i<bosss.size();i++) {
	    	bosss.get(i).draw(g);
	    }
	    
	// 循环爆炸
	    for(int i = 0; i<booms.size();i++) {
	    	if(booms.get(i).isLive()==true) {
	    	     booms.get(i).draw(g);
	    	}
	    }
	    
	}
	
	
	
	// 线程
	class paintThread extends Thread {

		@Override
		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
		}
		
	}
	
}
 