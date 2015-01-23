package com.silverline.gameboy.bubble;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class AndroidTouch implements OnTouchListener {

 AndroidTouch(final AndroidGraphics ag){
	
	
	ag.setOnTouchListener(new OnTouchListener() {
		
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
 /*    try {
		this.wait(5);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	Rect r=new Rect();
	for(int i=0;i<=4;i++){
	r=ag.getBitmapLocation(i);
	if(r.contains(((int)event.getX()) ,(int)event.getY()+3))
	{ag.touch=true ;
	Bubble.touchedbb=ag.b[i];
	break;}
	}
			return true;
		}
	});
	}
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
}
