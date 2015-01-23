package com.silverline.gameboy.bubble;

//import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;

import android.util.Log;

import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class AndroidGraphicsmain extends SurfaceView implements  SurfaceHolder.Callback {
	public static final int MAIN_MENU=1;
	public static final int GAME_SCREEN=2;
	public static final int CREDITS=3;
	public static final int RESUME=4;
gameLoop gs;
static	int height , width;


 

//Bitmap   bblur= BitmapFactory.decodeResource(getResources(), R.drawable.bathroomblur);


	 static int state=1;
	boolean play=true;
	
	
Context context =null;

//Paint paint;	 
static AndroidGraphics ag;
static	 AndroidGraphicsstart ags;
	 
	public AndroidGraphicsmain(Context context) {
		
		super(context);
		this.context=context;
		// TODO Auto-generated constructor stub
		 Log.d("GameboyActivity"," loading main");
		getHolder().addCallback(this);
		gs=new gameLoop( getHolder(),this);
		setFocusable(true);
		 Log.d("GameboyActivity"," going to start");
		 Log.d("GameboyActivity"," initialling the 2 childs");
	    ags = new AndroidGraphicsstart(this.context);
	    ag = new AndroidGraphics(this.context);	
       
	
	}


public void surfaceChanged(SurfaceHolder holder, int format, int width,
		int height) {

}
static SurfaceHolder holder;
public void surfaceCreated(SurfaceHolder holder) {
	// TODO Auto-generated method stub
  this.holder=holder;
	width=this.getWidth();
	height=this.getHeight();
	ags.surfaceCreated(holder);
	ag.surfaceCreated(holder);
	

	
	gs.start();
	gs.setRunnning(true);
	
}

public void surfaceDestroyed(SurfaceHolder holder) {
	
	// TODO Auto-generated method stub
	
	boolean retry=true;
	 gs.running=false;
	while (retry) {
		   try {
		    gs.join();
		    retry = false;
		    Log.d("GameboyActivity","finisihing lastdude 1");
		    	   } catch (InterruptedException e) {
		// try again shutting down the thread
		}
		  
	}
	ags.surfaceDestroyed(holder);
	ag.surfaceDestroyed(holder);
	//destroyDrawingCache();
}


  public void update(){
	 
	  
	  switch (state) {
	case MAIN_MENU:
	
		ags.update();
		break;
	case RESUME:
		
		ags.update();
		break;
	case 2:
		ag.update();
	
		break;
	}
  }
  
  
  
  @SuppressLint("WrongCall")
void render(Canvas canvas){
	  

	  switch (state) {
	  
	  case RESUME:
	  ags.render(canvas);
	  break;
		case MAIN_MENU:
		
			ags.render(canvas);
			break;
		case GAME_SCREEN:
			ag.render(canvas);
		
			break;
		case CREDITS:
			ags.render(canvas);
			break;
		}
	
	 
 }
 
  



public void onDraw(Canvas canvas){	
}

protected void delay() {
	try {
		Thread.sleep(10);
		  Log.d("GameboyActivity","sleeping a");
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static int getScreenWidth(){
	
return 	width;    // returns width of screen/canvas
}

public static int getScreenHeight(){
	
return 	height; // returns height of screen/canvas
}

public  static void reinitializegame() {
	ags.a =255;
	ag.surfaceCreated(holder);
 ag.counter=0;
 ag.touch_count=0;
 
}

public boolean onTouchEvent(MotionEvent event) {
	// TODO Auto-generated method stub
	if(state==MAIN_MENU || state==RESUME)
	{ags.onTouchEvent(event);}
	
	else if(state==GAME_SCREEN){
	//	at= new AndroidTouch(ag);
		ag.onTouchEvent(event);
	}
	
	
	return super.onTouchEvent(event);
	
}
public Boolean onBackPressed() {
	// TODO Auto-generated method stub
	
	if(state==MAIN_MENU || state==RESUME){ ags.ad.pauseMusic(); return true;}
	
	else if(state==GAME_SCREEN){
		Log.d("GameboyActivity","game screen to main menu");
		if(ag.currenttime<1){
			state=MAIN_MENU; 
			if(ag.counter > ag.best){
				SharedPreferences.Editor editor =
						ag.settings.edit(); 
				   editor.putInt("counter", ag.counter);
					Log.d("GameboyActivity",Integer.toString(ag.counter));
						editor.commit();
						ag.best=ag.counter;		
				}
		//	reinitializegame();
		}else{
	state=RESUME;
	if(ag.counter > ag.best){
		SharedPreferences.Editor editor =
				ag.settings.edit(); 
		   editor.putInt("counter", ag.counter);
			Log.d("GameboyActivity",Integer.toString(ag.counter));
				editor.commit();
				ag.best=ag.counter;		
		}
	 	return false;}
	}else if(state==CREDITS){
		
	state=ags.prev_state;
	 	return false;
	}
	return false;
}
}