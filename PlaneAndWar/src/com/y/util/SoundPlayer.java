package com.y.util;

import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
* @ClassName: SoundPlayer
* @Description: ���ű�������
* @author gjy
* @date 2019��8��21�� ����11:42:16
*
*/
public class SoundPlayer extends Thread {
	
	private String mp3Name;
	
	public SoundPlayer() {
		
	}
public SoundPlayer(String mp3Name) {
		this.mp3Name = mp3Name;
	}
@Override
	public void run() {
	for(;;) {
	InputStream resouceAsStream = SoundPlayer.class.getClassLoader().getResourceAsStream(mp3Name);
	try {
		AdvancedPlayer advancedPlayer = new AdvancedPlayer(resouceAsStream);
		advancedPlayer.play();
	} catch (JavaLayerException e) {
		e.printStackTrace();
	}
	}
}
}
