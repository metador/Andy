package com.silverline.gameboy.bubble;

//import java.util.Random;

import com.silverline.gameboy.bubble.R;

import android.annotation.SuppressLint;
import android.content.Context;
//import android.content.SharedPreferences;
//import android.graphics.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
//import android.provider.ContactsContract.AggregationExceptions;
import android.util.Log;

import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class AndroidGraphicsstart extends SurfaceView implements  SurfaceHolder.Callback {
	
	public gameLoop gs;

	String s ="-=| Credits |=- \n\n"+
			"Lead programmer,\n Game designer,\n Animator and Tester\n"+
			 "Melwin James\n\n" +
			 "Lead Music Production,\n Graphics and Testing  \nClifford Rodriguies" +
			 "\n\n Special Thanks to all \nFamily and Friends";
	  

			
	
    Bitmap backg =  BitmapFactory.decodeResource(getResources(),(R.drawable.bathroom));
	Bitmap   bblur= BitmapFactory.decodeResource(getResources(), R.drawable.bathroomblur);
	Bitmap  bplay= BitmapFactory.decodeResource(getResources(), R.drawable.play);
    Bitmap   binfo= BitmapFactory.decodeResource(getResources(), R.drawable.info);
    Bitmap   bon= BitmapFactory.decodeResource(getResources(), R.drawable.on);
    Bitmap   breload= BitmapFactory.decodeResource(getResources(), R.drawable.reload);
    Bitmap   btitle= BitmapFactory.decodeResource(getResources(), R.drawable.title); 
    Bitmap   info2= BitmapFactory.decodeResource(getResources(), R.drawable.bb);
	
    boolean sound = true;
	Rect rplay, rinfo, ron,rreload,rplay2,rtitle, rinfo2;
		  // please add necessary changes for multi device

	 Rect  brect ;
	 int best =0;
	 int a=255;
	 boolean fade=false;
Context context =null;
static AndroidAudio ad;
int prev_state=AndroidGraphicsmain.MAIN_MENU;
  Paint paint;
int scale=1;
	 
	public AndroidGraphicsstart(Context context) {
		super(context);
		this.context=context;
		 Log.d("GameboyActivity","android start condtructor");
		// TODO Auto-generated constructor stub
	
		setFocusable(true);	
	/*	
		Log.d("GameboyActivity",Integer.toString(counter));*/
		 paint =new Paint();
        paint.setColor(Color.WHITE);
		 paint.setFlags(Paint.FAKE_BOLD_TEXT_FLAG |Paint.LINEAR_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
		 paint.setStyle(Style.FILL);
		 paint.setTypeface(Typeface.SANS_SERIF);
        paint.setTextSize(25);
        ad= new AndroidAudio(context);
        if(sound)
        { AndroidAudio.PLAYMUSIC=true;}else{AndroidAudio.PLAYMUSIC=false;}
        ad.playMusic(R.raw.music);
        Log.d("GameboyActivity","  start inirialled");
       
	
	}


public void surfaceChanged(SurfaceHolder holder, int format, int width,
		int height) {
	// TODO Auto-generated method stub

}


public void surfaceCreated(SurfaceHolder holder) {
	// TODO Auto-generated method stub
 if(AndroidGraphicsmain.getScreenWidth()>=backg.getWidth()){
	brect = new Rect(0,0,(AndroidGraphicsmain.getScreenWidth()) ,(AndroidGraphicsmain.getScreenHeight()+40));
 }else{ 
	 brect = new Rect(0,0,(backg.getWidth()) ,(AndroidGraphicsmain.getScreenHeight()+50));
 }
	 Log.d("GameboyActivity","get hieght ka valuse"+Integer.toString(AndroidGraphicsmain.getScreenHeight()));
	rreload = new Rect(((AndroidGraphicsmain.getScreenWidth())/2-bplay.getWidth()),
			((AndroidGraphicsmain.getScreenHeight()-bplay.getHeight())/2),
			((AndroidGraphicsmain.getScreenWidth())/2) ,
			((AndroidGraphicsmain.getScreenHeight()+bplay.getHeight())/2 ));
	
	rplay2 = new Rect((int)((AndroidGraphicsmain.getScreenWidth())/2+0.5*bplay.getWidth()), // for resume screen 
			((AndroidGraphicsmain.getScreenHeight()-bplay.getHeight())/2),
			((int)(AndroidGraphicsmain.getScreenWidth()/2+1.5*bplay.getWidth())) ,
			((AndroidGraphicsmain.getScreenHeight()+bplay.getHeight())/2 ));
	
	rplay = new Rect(((AndroidGraphicsmain.getScreenWidth()-bplay.getWidth())/2),
			((AndroidGraphicsmain.getScreenHeight()-bplay.getHeight())/2),
			((AndroidGraphicsmain.getScreenWidth()+bplay.getWidth())/2) ,
			((AndroidGraphicsmain.getScreenHeight()+bplay.getHeight())/2 ));

	rtitle = new Rect((int)((.05*AndroidGraphicsmain.getScreenWidth())),
			(int)(0.1*AndroidGraphicsmain.getScreenHeight()),
			((int)(AndroidGraphicsmain.getScreenWidth()*.95)) ,
			(rplay.top-30));
	
	ron= new Rect((int)(0.1*AndroidGraphicsmain.getScreenWidth()),
			(int)(AndroidGraphicsmain.getScreenHeight()-0.1*AndroidGraphicsmain.getScreenWidth()-binfo.getHeight()),
			(int)(0.1*AndroidGraphicsmain.getScreenWidth()+binfo.getWidth()) ,
			(int)(AndroidGraphicsmain.getScreenHeight()-0.1*AndroidGraphicsmain.getScreenWidth()));
	
	rinfo= new Rect((int)(AndroidGraphicsmain.getScreenWidth()*0.9-binfo.getWidth()),
			(int)(AndroidGraphicsmain.getScreenHeight()-0.1*AndroidGraphicsmain.getScreenWidth()-binfo.getHeight()),
		    (int)(AndroidGraphicsmain.getScreenWidth()*0.9) ,
			(int)(AndroidGraphicsmain.getScreenHeight()-0.1*AndroidGraphicsmain.getScreenWidth()));
	
          rinfo2 = new Rect(brect);
          rinfo2.bottom=brect.bottom-brect.bottom/2;
          rinfo2.right=AndroidGraphicsmain.getScreenWidth();
     //   y=50+rinfo2.bottom;
scale= AndroidGraphicsmain.getScreenHeight()/720;
	paint.setTextSize(35*scale);
	
}

public void surfaceDestroyed(SurfaceHolder holder) {
	
	// TODO Auto-generated method stub
//	ad.pauseMusic();
//	this.destroyDrawingCache();
	 backg.recycle();
	 bblur.recycle(); bplay.recycle(); binfo.recycle();bon.recycle();
	breload.recycle();info2.recycle();btitle.recycle();

}





  public void update(){
	  
	  if (fade) {
		paint.setAlpha(a);
		delay();
		a -= 5;
		if(a<=5){
			a=255;
			paint.setAlpha(a);
			AndroidGraphicsmain.state=AndroidGraphicsmain.GAME_SCREEN;
		   fade=false;
	       
		}
	}
  }
  
  
  
  @SuppressLint("WrongCall")
void render(Canvas canvas){

	  if(AndroidGraphicsmain.state==AndroidGraphicsmain.CREDITS){
		  creditrender(canvas);
	  }
	  else if(AndroidGraphicsmain.state==AndroidGraphicsmain.RESUME) // resume state
	  {
		  canvas.drawBitmap(bblur,null, brect,null );
		  canvas.drawBitmap(btitle,null,rtitle,null);
		  canvas.drawBitmap(bplay,null,rplay2,null);
		  canvas.drawBitmap(breload,null,rreload,null);
		   canvas.drawBitmap(binfo, null,rinfo,null);
		   canvas.drawBitmap(bon, null,ron,null);
		  
	  }else{
	  canvas.drawBitmap(backg,null, brect,null ); // start state with backgorund
//	  canvas.drawBitmap(backg,0, 0, null);
	  if(a>=0){
	  delay();
	  canvas.drawBitmap(bblur,null, brect,paint); // start stat with blurblackgorund
	 
	   if(!fade){                                   // buttons and title on backgorund->disappears when fading starts
		   canvas.drawBitmap(btitle,null,rtitle,null);
		   canvas.drawBitmap(bplay,null,rplay,null);
		   canvas.drawBitmap(binfo, null,rinfo,null);
		   canvas.drawBitmap(bon, null,ron,null);
	   }
	  }
	  }
	 
 }
 
@Override
public void onDraw(Canvas canvas){	
}

int x=10;
int y=50;
	


void creditrender(Canvas canvas) {
	// TODO Auto-generated method stub
	canvas.drawBitmap(bblur, null, brect,null);
	Paint paint =new Paint();
     paint.setColor(Color.RED);
		 paint.setFlags(Paint.FAKE_BOLD_TEXT_FLAG |Paint.LINEAR_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
		 paint.setStyle(Style.FILL);
		 paint.setTypeface(Typeface.DEFAULT_BOLD);
     paint.setTextSize(30*scale);
     y=AndroidGraphicsmain.getScreenHeight()-(int)(12*(-paint.ascent() + paint.descent()))-30;
     rinfo2.bottom= y-20;
     /*canvas.drawBitmap(AndroidGraphicsmain.ag.bp,50,60,null);
     canvas.drawText("=Score+1",70+ AndroidGraphicsmain.ag.bp.getWidth(), 100, paint);
     canvas.drawBitmap(AndroidGraphicsmain.ag.bred,50,80+AndroidGraphicsmain.ag.bp.getHeight(),null);
     canvas.drawText("=Score+10",70+ AndroidGraphicsmain.ag.bp.getWidth(),130+ AndroidGraphicsmain.ag.bp.getHeight(), paint);
     canvas.drawBitmap(AndroidGraphicsmain.ag.bblue,50,100+2*AndroidGraphicsmain.ag.bp.getHeight(),null);
     canvas.drawText("=Score-10",70+ AndroidGraphicsmain.ag.bp.getWidth(),150+2* AndroidGraphicsmain.ag.bp.getHeight(), paint);
     canvas.drawBitmap(AndroidGraphicsmain.ag.bblack,50,120+3*AndroidGraphicsmain.ag.bp.getHeight(),null);
     canvas.drawText("=Time-10sec",70+ AndroidGraphicsmain.ag.bp.getWidth(),170+ 3*AndroidGraphicsmain.ag.bp.getHeight(), paint);
     canvas.drawBitmap(AndroidGraphicsmain.ag.byellow,50,140+4*AndroidGraphicsmain.ag.bp.getHeight(),null);
     canvas.drawText("=Time+10sec",70+ AndroidGraphicsmain.ag.bp.getWidth(),190+4* AndroidGraphicsmain.ag.bp.getHeight(), paint);*/
   //  int x=10;
//	int y=50+info2.getHeight();
	
	 for (String line: s.split("\n"))
     {  
		 x=AndroidGraphicsmain.getScreenWidth()/2-(int)paint.measureText(line)/2;
		 if(line.matches("Melwin James") || line.matches("Clifford Rodriguies")|| line.matches("Family and Friends") )
		 {paint.setColor(Color.RED);}else{paint.setColor(Color.BLUE);}
			 
           canvas.drawText(line, x, y, paint); 
           y += -paint.ascent() + paint.descent();
     }

 
	canvas.drawBitmap(info2, null, rinfo2,null);
	
}




protected void delay() {
	try {
		Thread.sleep(5);
		  Log.d("GameboyActivity","sleeping a="+Integer.toString(a));
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
		if(rplay.contains((int)event.getX(),(int)event.getY()) ){
			AndroidAudio.playSound(2);
		fade = true;
		AndroidGraphicsmain.reinitializegame();
	//	play=false;
		
		}
		if(rplay2.contains((int)event.getX(),(int)event.getY()) ){
			//AndroidGraphicsmain.reinitializegame();
			AndroidGraphicsmain.state=AndroidGraphicsmain.GAME_SCREEN;
			 AndroidAudio.playSound(2);
		//	play=false;
			
			}
		if(rreload.contains((int)event.getX(),(int)event.getY()) ){
			AndroidGraphicsmain.reinitializegame();
			a=255; AndroidGraphicsmain.state=AndroidGraphicsmain.MAIN_MENU;
			fade=true;
			 AndroidAudio.playSound(2);
			
			}
		
		if(rinfo.contains((int)event.getX(),(int)event.getY()) ){
			prev_state=AndroidGraphicsmain.state;
			AndroidGraphicsmain.state=AndroidGraphicsmain.CREDITS;
			
			}
		
		if(ron.contains((int)event.getX(),(int)event.getY()) ){
			if(!sound){
				 bon= BitmapFactory.decodeResource(getResources(), R.drawable.on); 
				 AndroidAudio.PLAYMUSIC=true;
				 ad.resumeMusic();
				 sound=true;
			}else{
				 bon= BitmapFactory.decodeResource(getResources(), R.drawable.mute);
				AndroidAudio.PLAYMUSIC=false;
				ad.pauseMusic();
				 sound=false;
			}
			
			}
		return super.onTouchEvent(event);
		
	}



}