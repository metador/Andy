package com.silverline.gameboy.bubble;

import java.util.Random;

import android.util.Log;



class calculator {
	Bubble b;
	Random as;
	int rand;
	int speed;
	int n=2;
	 int temp=0;
	 AndroidGraphics ag;
	 float scale=1;
	public calculator(AndroidGraphics ag) {
		this.ag=ag;
		// TODO Auto-generated constructor stu
		scale= AndroidGraphicsmain.getScreenHeight()/720;
		if(scale<=1){scale=1;}
		ag.bp.getWidth();
		b=new Bubble(ag);
		as= new Random();
	
//	rand=as.nextInt(150)	;
	
	}

	
   void  calc(int touch_counter, Bubble bb){
	  
	   b=bb;
	   speed= (int)((as.nextInt(n)+5)*scale);
		rand=as.nextInt(150);
		
	   if (touch_counter >10){
		   
	   if ((rand%23)==0){
		   
		  Log.d("GameboyActivity","counter in calc="+touch_counter);
		   b.setBubble(speed, 1) ;   // red bubble
		  }else if ((rand%25)==0 && ag.currenttime<50){
			   
		   Log.d("GameboyActivity","counter in calc="+touch_counter);
			   b.setBubble(speed+3, 3) ;  // yellow
			  }else if ((rand%11)==0){
				   
				   Log.d("GameboyActivity","counter in calc="+touch_counter);
				   b.setBubble(speed, 2) ;   // bad blue
				  } else if ((rand%19)==0){
					   
					   Log.d("GameboyActivity","counter in calc="+touch_counter);
					   b.setBubble(speed, 4) ;  // black
					  } else if(true){
						   
						   Log.d("GameboyActivity","counter in calc="+touch_counter);
						   b.setBubble(speed, 0) ;  // normal blue
						  }
	   if((touch_counter-temp)>30 && n<=12){ n++; temp=touch_counter;}
	   
    }
   }
}
