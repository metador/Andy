package com.silverline.gameboy.bubble;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.SharedPreferences;

import android.media.AudioManager;
import android.os.Bundle;
//import android.silverline.gameboy.bubble.AndroidAudio;
import android.util.Log;

import android.view.Window;
import android.view.WindowManager;


public class GameboyActivity extends Activity {
    /** Called when the activity is first created. */
	
	static AndroidGraphicsmain agm;
	 SharedPreferences settings;
	 static boolean startpage;
    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
       
       
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_PROGRESS);// removes title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN); //sets full screen
     //   setContentView(R.layout.activity_main);
        
        Log.d("GameboyActivity"," going to start");
      
    
       settings = this.getSharedPreferences("custom", 0); 
       Log.d("GameboyActivity"," going to start amain");
        agm = new AndroidGraphicsmain(this);
        startpage=true;
        setContentView(agm);
       
      //  ag = new AndroidGraphics(this);
     //   at= new AndroidTouch(ag);
       this.setDefaultKeyMode(DEFAULT_KEYS_DISABLE);
       agm.setKeepScreenOn(true);
       agm.state=1;
       //ag.setKeepScreenOn(true);
       this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
    
    
  

        
    }
    @Override
    protected void onPause() {
    	
	super.onPause();
    	Log.d("GameboyActivity","pause ACTIVITY 1");
    	//ad.pauseMusic(this);
    	if(agm.ags.ad.PLAYMUSIC==true)
    	agm.ags.ad.pauseMusic();
    	
    	AndroidGraphicsmain.state=AndroidGraphicsmain.RESUME;
    agm.gs.setRunnning(false);
    Log.d("GameboyActivity","pause ACTIVITY 1");
   }
    
    	@Override
        protected void onStop() {
        	
        	super.onStop();
        	Log.d("GameboyActivity","finisihing 1");
        	agm.surfaceDestroyed(agm.getHolder());
         	agm.ags.ad.stopMusic();
         	this.finish();
         System.gc();
       	Runtime.getRuntime().gc();
           
    }
    	@Override
    	protected void onRestart() {
    		// TODO Auto-generated method stub
    		super.onRestart();
    		AndroidGraphicsmain.reinitializegame();
    		agm.ags.ad.resumeMusic();
    		
    	}
    	
		@Override
		public void onBackPressed() {
			// TODO Auto-generated method stub
			//super.onBackPressed();
			
			if(agm.onBackPressed())
			{ //agm.ags.ad.pauseMusic();
				Log.d("GameboyActivity", "Back button pressed ending.");
				new AlertDialog.Builder(this)
			    .setTitle("Exit")
			    .setMessage("Are you sure you want to exit the game?")
			    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			        	
			        	dialog.cancel();    
			        	dialog.dismiss();
			        	 onPause(); 
			        	 onStop();
			        	 // continue with delete
			        }
			     })
			    .setNegativeButton("No", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // do nothing
			        	dialog.cancel(); agm.ags.ad.resumeMusic();
			        }}).show();
			
			}
		}
		
@Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	agm.gs.setRunnning(true);
}
		
}
