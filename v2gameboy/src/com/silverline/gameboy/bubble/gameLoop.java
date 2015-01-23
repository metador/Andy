package com.silverline.gameboy.bubble;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class gameLoop extends Thread{


	SurfaceHolder surfaceHolder; 
	
	AndroidGraphicsmain andgrapstart;
  
	volatile boolean running ;
	Canvas canvas;
	//desired fps
	private final static int MAX_FPS = 50;
	//maximum number of frames to be skipped
	private final static int    MAX_FRAME_SKIPS = 5;
	//the frame period
	private final static int    FRAME_PERIOD = 1000 / MAX_FPS;
	    long beginTime;     // the time when the cycle begun
	    long timeDiff;      // the time it took for the cycle to execute
	    int sleepTime =0;      // ms to sleep (<0 if we're behind)
	    int framesSkipped;  // number of frames being skipped
	    


public gameLoop(SurfaceHolder surfaceHolder, AndroidGraphicsmain andgrap) {
	// TODO Auto-generated constructor stub
this.surfaceHolder=surfaceHolder;
this.andgrapstart=andgrap;
}

void setRunnning(boolean running)
{this.running=running;}

	public void run() {
		while(running){
		try{
	//Log.d("GameboyActivity"," started running");
	
		if(surfaceHolder.getSurface().isValid())
	{
		canvas=null;
 canvas=surfaceHolder.lockCanvas();
     synchronized (canvas) {
	beginTime=System.currentTimeMillis();// current time
	framesSkipped=0; // sets the no of frames skipped to zero
		
		andgrapstart.update();
		andgrapstart.render(canvas);

	
	timeDiff=System.currentTimeMillis()-beginTime; // time taken to execute above 2 lines
	Log.d("GameboyActivity","running gamellop");
	
	sleepTime=(int)(FRAME_PERIOD-timeDiff);
     } 
    }// is surfaceholder valid ka if
	if(sleepTime>0){
try {
//	Log.d("GameboyActivity","waiting yi=".concat(Float.toString(andgrap.y1)));
	Thread.sleep(sleepTime); // sleeps time that frame preiod is over;
 
	}
	 catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();

    }   
   } // sleep block ka if()
	
   if(sleepTime<0 && framesSkipped<MAX_FRAME_SKIPS){ // if the execution of render+ update take more than fps
	   Log.d("GameboyActivity","running gamellop skipped frame");
	   
	 andgrapstart.update(); // skips render so as to save time
	framesSkipped++ ;// more than 5 frameskipps can make the game lagging
   }

	}finally{
	if (canvas != null)
	 { surfaceHolder.unlockCanvasAndPost(canvas);}
	}
	 }


	}
	}
	

	

