package com.silverline.gameboy.bubble;

import java.util.Random;

import com.silverline.gameboy.bubble.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.*;
import android.graphics.Paint.Style;
import android.util.Log;

import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class AndroidGraphics extends SurfaceView implements  SurfaceHolder.Callback {
	
	public gameLoop gs;
	Bitmap backg =  BitmapFactory.decodeResource(getResources(),(R.drawable.bathroom));
	Bitmap   bmain= BitmapFactory.decodeResource(getResources(), R.drawable.bb4);
	Bitmap   bp= BitmapFactory.decodeResource(getResources(), R.drawable.bb4);
	Bitmap   bred= BitmapFactory.decodeResource(getResources(), R.drawable.red);
    Bitmap   bblack= BitmapFactory.decodeResource(getResources(), R.drawable.black);
	Bitmap   byellow= BitmapFactory.decodeResource(getResources(), R.drawable.yellow);
	Bitmap   bblue= BitmapFactory.decodeResource(getResources(), R.drawable.blue);
	Bitmap   bclock= BitmapFactory.decodeResource(getResources(), R.drawable.clock);
	Bitmap   btowel= BitmapFactory.decodeResource(getResources(), R.drawable.towel);
	 Bitmap   breload= BitmapFactory.decodeResource(getResources(), R.drawable.reload);
	 calculator calc ;
	 Rect  brect, clockrec, towelrect, rreload;
	 int best =0;
     
     Context context =null;
//	float x1,y1;
	 static boolean touch =false;
	 Bubble[] b=new Bubble[5] ;
	 Random r=null;
	 int as=0;
	 int counter=0;
	 int touch_count=0;
	//SharedPreferences settings;
	 int nottouched=0;
	 Paint paint, paint2,paint3;
	 SharedPreferences settings;
	 long begintime, systemtime=0,timediff=0;
	 int currenttime,full_time =100;
	 boolean intialrun =true;
	 int temp =0;
	 int time=0;
	float scale=1;
	 
	public AndroidGraphics(Context context) {
		super(context);
		this.context=context;
		// TODO Auto-generated constructor stub
		Log.d("GameboyActivity","android graphics is object is created");
		calc = new calculator(this);
	
		 
		setFocusable(true);
		settings = context.getSharedPreferences("counter", 0); 
		best= settings.getInt("counter", 0);
		Log.d("GameboyActivity",Integer.toString((int)AndroidGraphicsmain.getScreenWidth()));
		if(AndroidGraphicsmain.getScreenWidth() >1){ // since the surface is not created yet the Android graphics gives an error. so wen we hav to reinitalaze the towel rect for the next game we hav to use the condition
			 towelrect=new Rect((int)(0.01*AndroidGraphicsmain.getScreenWidth()),-AndroidGraphicsmain.getScreenHeight(),(int)(0.99*AndroidGraphicsmain.getScreenWidth()),(int)(.9*AndroidGraphicsmain.getScreenHeight()-AndroidGraphicsmain.getScreenHeight()));
		}
	     Log.d("GameboyActivity",Integer.toString(counter));
		 paint =new Paint();
         paint.setColor(Color.WHITE);
		 paint.setFlags(Paint.FAKE_BOLD_TEXT_FLAG |Paint.LINEAR_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
		 paint.setStyle(Style.FILL);
		 paint.setTypeface(Typeface.DEFAULT_BOLD);
         paint.setTextSize(35);
         paint2 =new Paint();
       paint2.setColor(Color.WHITE);
       paint2.setFlags(Paint.FAKE_BOLD_TEXT_FLAG |Paint.LINEAR_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
       paint2.setStyle(Style.FILL);
       paint2.setTypeface(Typeface.SANS_SERIF);
       paint3 =new Paint();
       paint3.setColor(Color.WHITE);
       paint3.setFlags(Paint.FAKE_BOLD_TEXT_FLAG |Paint.LINEAR_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
       paint3.setStyle(Style.FILL);
       paint3.setTypeface(Typeface.SANS_SERIF);
        Log.d("GameboyActivity"," graphic intiallezed");
	}


	
public void surfaceChanged(SurfaceHolder holder, int format, int width,
		int height) {
	// TODO Auto-generated method stub
	SharedPreferences.Editor editor =settings.edit(); 
    editor.putInt("temp", counter);
	}


public void surfaceCreated(SurfaceHolder holder) {
	// TODO Auto-generated method stub
	calc = new calculator(this);
	 Log.d("GameboyActivity","staring surface create");
	 intialrun=true;
	 if(AndroidGraphicsmain.getScreenWidth()>=backg.getWidth()){
			brect = new Rect(0,0,(int)(AndroidGraphicsmain.getScreenWidth()) ,(int)(AndroidGraphicsmain.getScreenHeight()+40));
		 }else{ 
			 brect = new Rect(0,0,(int)(backg.getWidth()) ,(int)(AndroidGraphicsmain.getScreenHeight()+50));
		 }
	
	   scale=AndroidGraphicsmain.getScreenHeight()/720;
	   if(scale<1){scale=1;}
	
	 systemtime=0;timediff=0;time=0;
	 paint.setTextSize(35*scale);
	 
	 clockrec= new Rect((int)(AndroidGraphicsmain.getScreenWidth()-paint.measureText("XXXX")-((int)paint.descent()-(int)paint.ascent())-10),(int) (.05*AndroidGraphicsmain.getScreenHeight()+paint.ascent()),
			 (int)(AndroidGraphicsmain.getScreenWidth()-paint.measureText("XXXX")-5), (int)(.05*AndroidGraphicsmain.getScreenHeight())+(int)paint.descent()+5); 
	
	 
	
	 
	 //towelrect=new Rect((int)(0.1*AndroidGraphicsmain.getScreenWidth()),0,(int)(0.8*+AndroidGraphicsmain.getScreenWidth()) ,(int)(.8*AndroidGraphicsmain.getScreenHeight())+AndroidGraphicsmain.getScreenHeight());
	 towelrect=new Rect((int)(0.01*AndroidGraphicsmain.getScreenWidth()),-AndroidGraphicsmain.getScreenHeight()
			           ,(int)(0.99*AndroidGraphicsmain.getScreenWidth()),(int)(.95*AndroidGraphicsmain.getScreenHeight()-AndroidGraphicsmain.getScreenHeight()));
		
	 temp=0;
	 for(int i=0;i<=4;i++){     // assingin intail values to bubbles
		 
	r = new Random();
	as = r.nextInt(AndroidGraphicsmain.getScreenWidth()-bp.getWidth());
	b[i]= new Bubble(this);
	b[i].x=as; 
   	b[i].y=(int)(r.nextInt(AndroidGraphicsmain.getScreenWidth())+AndroidGraphicsmain.getScreenHeight());
	
	//b[i].speed=4+r.nextInt(9);
	}
	// intializing 
	 Log.d("GameboyActivity","bubble created1");
     calc.n=2; 
   full_time=100;currenttime=100;
   Log.d("GameboyActivity","bubble wight n height"+Integer.toString((int)(bred.getWidth()))+" "+Integer.toString((int)(bp.getHeight())));
   scale=AndroidGraphicsmain.getScreenHeight()/720;
if(scale<1){scale=1;}
}

public void surfaceDestroyed(SurfaceHolder holder) {
	
	// TODO Auto-generated method stub
	if(counter > best){
	SharedPreferences.Editor editor =
		settings.edit(); 
	   editor.putInt("counter", counter);
		Log.d("GameboyActivity",Integer.toString(counter));
			editor.commit();
	}
	 backg.recycle(); bblack.recycle(); bblue.recycle(); bmain.recycle();bp.recycle();	
	 byellow.recycle();  bred.recycle();bclock.recycle();
btowel.recycle();bp.recycle();

}


  public void update(){
	  if(currenttime<1){ // wen games stops
	
		
		if (temp<(.9*AndroidGraphicsmain.getScreenHeight())) {
			towelrect.bottom +=30;
			towelrect.top +=30;
			temp+=30;
			if(temp<(.9*AndroidGraphicsmain.getScreenHeight())){
				rreload = new Rect((int)((AndroidGraphicsmain.getScreenWidth())/2-breload.getWidth()/2),
						((int)towelrect.bottom)+25 ,
						((int)(AndroidGraphicsmain.getScreenWidth())/2+breload.getWidth()/2) ,
						((int)towelrect.bottom+25 +breload.getHeight())) ;
			}	
		}
    	
	  }else{
		  
	  r = new Random();
	  Log.d("GameboyActivity","random peaki=".concat(Integer.toString(calc.n)));
	  
		
	if(!intialrun)	{
		if((System.currentTimeMillis()-systemtime)>=999)
		{timediff=System.currentTimeMillis()-systemtime ;
		time++;systemtime=System.currentTimeMillis();
		}
		//systemtime=System.currentTimeMillis();
	//	currenttime=full_time-(int)(System.currentTimeMillis()-begintime-timediff)/1000;}  // the clock
		currenttime=full_time-time;}
	
	if(touch){                                        // when toched
		  AndroidAudio.playSound(1);
			touch =false;
		    Bubble bb=Bubble.touchedbb;
		    as = r.nextInt(AndroidGraphicsmain.getScreenWidth()-bp.getWidth());
			bb.x=as; bb.y=(int) (AndroidGraphicsmain.getScreenHeight());
			touch_count++;
			Log.d("GameboyActivity","type=".concat(Integer.toString(bb.type)));
			
		switch (bb.type) {
		case 1:
			counter=counter+10;
			break;
         case 2:
        	 counter=counter-10;
        	 break;
        	 
         case 3:
        	 if(currenttime>=90)
        	 {full_time=full_time+(100-currenttime);}
        	 else{
        		full_time=full_time+10;
        	 }
            break;
          case 4: 
        	  full_time=full_time-10;
        	  break;
            // stop game
		default:
			counter++;
			break;
		}
			calc.calc(touch_count,bb);
	    		}
	  
	  for(int i=0;i<=4;i++){
		  
//	Log.d("GameboyActivity","running  i=".concat(Integer.toString(i)).concat("  y= ").concat(Float.toString(b[i].y)));
	
	if(b[i].y<-bp.getHeight())  // if bubble not touched
{ if(b[i].type == 4 || b[i].type == 1 || b[i].type == 2 )
{ nottouched++;}
as = r.nextInt(AndroidGraphicsmain.getScreenWidth()-bp.getWidth());
		calc.calc(touch_count, b[i]);
 b[i].y=AndroidGraphicsmain.getScreenHeight();
 b[i].x=as;
  
 
}
   b[i].y-=b[i].speed;	
   
   
     // initialzation of clock
   if(intialrun ) // start clock when bubble is seen
   { if(b[i].y>AndroidGraphicsmain.getScreenHeight() )
   { 
	   begintime=System.currentTimeMillis(); intialrun=false;
	   systemtime=System.currentTimeMillis();
	   currenttime=full_time-(int)(System.currentTimeMillis()-begintime)/1000;
   } 
 }
}
	  
	  
  }
  
  }
  int a=0;
  boolean expand =true;
  @SuppressLint("WrongCall")
 void render(Canvas canvas){
	  canvas.drawBitmap(backg,null, brect, null);
  //  canvas.drawBitmap(bp,x1,y1,null);
	 paint.set(paint);
	 paint.setTextSize((int)(35*scale));
	 canvas.drawText(Integer.toString(counter), 10,(int)(.05*AndroidGraphicsmain.getScreenHeight()),paint);
	 paint.set(paint);
	 paint.setTextSize((int)(25*scale));
	 canvas.drawText("Best :"+Integer.toString(best), 10, (int)(.05*AndroidGraphicsmain.getScreenHeight()+40),paint);
	 paint.setTextSize((int)(35*scale));
	// canvas.drawText(Integer.toString(nottouched), AndroidGraphicsmain.getScreenWidth()-(int)paint.measureText("10X"), 50,paint);
	 
	 canvas.drawBitmap(bclock,null, clockrec, null);
	 paint.setTextSize((int)(35*scale));
	 if(currenttime<10){if( expand)
	 {
	  a++;
	  paint.setTextSize((int)((35+a)*scale));
	  if(a==5){ expand=false;}
	 }else{a--;
	  paint.setTextSize((int)((35+a)*scale));
	  if(a==0){ expand=true;}}
	 }
	 canvas.drawText(Integer.toString((int)currenttime), AndroidGraphicsmain.getScreenWidth()-(int)paint.measureText("XXXX"), (int)(.05*AndroidGraphicsmain.getScreenHeight()),paint); 
    for(int i=0;i<=4;i++)
    b[i].drawBubble(b[i].br,canvas);
    Log.d("GameboyActivity","bubble drawn");
    if(towelrect.bottom>-1){
    	
    canvas.drawBitmap(btowel,null, towelrect, null);
   
    if(temp>(.87*AndroidGraphicsmain.getScreenHeight())){
      canvas.drawBitmap(breload,null,rreload,null);
   
      String score = "Your score is ";
      paint.setTextSize((int)(30*scale));
     // paint.setColor(android.graphics.Color.WHITE);
      canvas.drawText(score, AndroidGraphicsmain.getScreenWidth()/2-(int)paint.measureText(score)/2, towelrect.bottom/2-(-paint2.ascent()),paint); 		    
     // canvas.drawText("Your best score is", AndroidGraphicsmain.getScreenWidth()/2-(int)paint2.measureText("Your Best Score is")/2, towelrect.bottom/2+(int)paint2.(Integer.toString((int)counter))/2,paint2); 
       paint2.setTextSize(100*scale);
      canvas.drawText(Integer.toString((int)counter), AndroidGraphicsmain.getScreenWidth()/2-(int)paint2.measureText(Integer.toString((int)counter))/2, towelrect.bottom/2,paint2); 
      paint.setTextSize((int)(30*scale));
      // paint.setColor(android.graphics.Color.WHITE);
      if(counter-best>=0){
      	int temp =counter-best;
       canvas.drawText("You Win By", AndroidGraphicsmain.getScreenWidth()/2-(int)paint.measureText("You Win By")/2, towelrect.bottom/2 + paint2.descent()-paint.ascent(),paint); 		    
      // canvas.drawText("Your best score is", AndroidGraphicsmain.getScreenWidth()/2-(int)paint2.measureText("Your Best Score is")/2, towelrect.bottom/2+(int)paint2.(Integer.toString((int)counter))/2,paint2); 
        paint3.setTextSize(100*scale);paint3.setColor(android.graphics.Color.GREEN);
       canvas.drawText(Integer.toString((int)temp), AndroidGraphicsmain.getScreenWidth()/2-(int)paint2.measureText(Integer.toString((int)best-counter))/2,towelrect.bottom/2 +(-paint2.ascent() )+2*(-paint.ascent()) + paint.descent(),paint3); 
      
       
      
      }else{
      	paint.setTextSize((int)(30*scale));
      	   // paint.setColor(android.graphics.Color.WHITE);
      	    canvas.drawText("You Lose By", AndroidGraphicsmain.getScreenWidth()/2-(int)paint.measureText("You lose by")/2, towelrect.bottom/2 + paint2.descent()-paint.ascent(),paint); 		    
      	   // canvas.drawText("Your best score is", AndroidGraphicsmain.getScreenWidth()/2-(int)paint2.measureText("Your Best Score is")/2, towelrect.bottom/2+(int)paint2.(Integer.toString((int)counter))/2,paint2); 
      	     paint3.setTextSize(100*scale);paint3.setColor(android.graphics.Color.RED);
      	    canvas.drawText(Integer.toString((int)best-counter), AndroidGraphicsmain.getScreenWidth()/2-(int)paint2.measureText(Integer.toString((int)best-counter))/2,towelrect.bottom/2 -paint2.ascent()+2*(-paint.ascent()) + paint.descent(),paint3); 
      	  }
    }
  } }
public void onDraw(Canvas canvas){	
}

public Rect getBitmapLocation(int i){
//	Rect r =new Rect((int)b[i].x,(int)b[i].y,(int)(b[i].x+bp.getWidth()),(int) b[i].y+bp.getHeight());
	Rect r =new Rect((int)b[i].x,(int)b[i].y,(int)(b[i].x+bred.getWidth()),(int) b[i].y+bp.getWidth());
	Log.d("GameboyActivity","x1 cor"+Integer.toString((int)(b[i].x ))+" "+Integer.toString((int)(b[i].y )) );
	Log.d("GameboyActivity","x2 cor"+Integer.toString((int)(bred.getWidth()))+" "+Integer.toString((int)(+bp.getHeight())));
	return r;
	}

@Override
public boolean onTouchEvent(MotionEvent event) {
	// TODO Auto-generated method stub
	Rect r=new Rect();
	
	if(temp>(.86*AndroidGraphicsmain.getScreenHeight())){
		
		if(counter-best>=0){
			SharedPreferences.Editor editor =settings.edit(); 
 		   editor.putInt("counter", counter);
 			Log.d("GameboyActivity",Integer.toString(counter));
 				editor.commit();
 				best=counter;}
		if(rreload.contains(((int)event.getX()) ,(int)event.getY()+3)){
			AndroidGraphicsmain.reinitializegame();
			AndroidAudio.playSound(2);
			
			}
	}

	for(int i=0;i<=4;i++){
	r=this.getBitmapLocation(i);
	if(r.contains((int)event.getX() ,(int)event.getY()+3))
	{this.touch=true ;
	Bubble.touchedbb=this.b[i];
	r=null;
	break;
	}
	}
	return true;
	}


}

