 package com.silverline.gameboy.bubble;



import com.silverline.gameboy.bubble.R;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;


public class AndroidAudio extends MediaPlayer{
	MediaPlayer mp= new MediaPlayer();;
	Context con;
	 static SoundPool sp;
	 static float rig_vol, left_vol;
	int burst, bubbles;
	public static boolean PLAYMUSIC ;
	AndroidAudio( Context con){
		sp = new SoundPool(20,AudioManager.STREAM_MUSIC, 0); // makes a instant of soundpool 1 param max no of concurrent sound, what kind of sound, currently unused
		 
		burst = sp.load(con,R.raw.burst,1); 
		 bubbles = sp.load(con,R.raw.bubbles,2); 
		 rig_vol=1; left_vol=1;
	this.con=con;
	}
//@SuppressWarnings("static-access")
public  void playMusic(int resid) {



	mp=MediaPlayer.create(con,resid);
	mp.setLooping(true);

	//mp.setVolume(0.8,0.8);
	Log.d("GameboyActivity","music going to start");

	if(PLAYMUSIC){mp.start();}else{mp.pause();}
}

public void stopMusic() {
	//if(conx.equals(con))
	{ PLAYMUSIC=false;
		mp.release();Log.d("GameboyActivity","music stoped");
		sp.release();
		
	//}else{
		}
	}
	
	public void pauseMusic() {
		//if(conx.equals(con))
		{mp.pause();
		PLAYMUSIC=false;
		Log.d("GameboyActivity","music paused");
		
			}
	
}
	public static void playSound(int sample){
		if(PLAYMUSIC){
			switch (sample){
			case 1:
		sp.play(1,rig_vol,left_vol,0,0,1.5f);// plays the sound 1 param is the handle, left vol, right vol, priorty, looped or not,rate <1 slow - >1 faster
	    break;
			case 2:
		sp.play(2,rig_vol,left_vol,0,0,1.5f);		
		break;
			}
		}
	}
protected void resumeMusic(){
	
	PLAYMUSIC=true;
	mp.start();	
	}

}
