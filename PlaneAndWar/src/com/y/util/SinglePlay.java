package com.y.util;

import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
* @ClassName: SinglePlay
* @Description: ���η���
* @author gjy
* @date 2019��8��21�� ����12:26:38
*
*/
public class SinglePlay extends Thread {

	private String mp3Name;
	public SinglePlay() {
		
	}
	public SinglePlay(String mp3Name) {
		this.mp3Name = mp3Name;
	}
	public void play(String mp3Name) {
		SinglePlay singlePlay = new SinglePlay(mp3Name);
		singlePlay.start();
	}
	public void run() {
		InputStream resouceAsStream = SoundPlayer.class.getClassLoader().getResourceAsStream(mp3Name);
		try {
			AdvancedPlayer advancedPlayer = new AdvancedPlayer(resouceAsStream);
			advancedPlayer.play();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}
}
