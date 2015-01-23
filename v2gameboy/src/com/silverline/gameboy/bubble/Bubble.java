package com.silverline.gameboy.bubble;


import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class Bubble extends Canvas  {
 int x=100,y=100;
//int id=0;
int speed =3;
Bitmap br;
AndroidGraphics ag;
static Bubble touchedbb;
Rect s;
int type =0;
  public Bubble(int x, int y,AndroidGraphics ag) {
	// TODO Auto-generated constructor stub

	  // values of x and y are assinged in android graphics
	  this.ag=ag;
	//  id++;
	  br=ag.bp;
	   Log.d("GameboyActivity","bubble");
}
  public Bubble(AndroidGraphics ag) {this.ag=ag;br=ag.bp;}
 
 Bubble getBubble(){return this;}

 Bubble setBubble(int speed , int type){
	 this.type=type;
     switch (type) {
	case 1:   // red bubble
		br =ag.bred;
		break;
    case 2:   // yellow bubble
    	 br=ag.bblue;
		break;
    case 3: // blackbubble
    	br=ag.byellow;
	break;
   case 4: //  dark blue
	   br = ag.bblack;
	break;
   
   case 0:
		br=ag.bmain;
		break;
		
    }
	 
    this.speed=speed;
	 return this;
 }

void drawBubble(Bitmap br, Canvas canvas){
	s = new Rect((int) (this.x),(int)(this.y),(int)( (this.x+.95*br.getWidth())),(int)( this.y+(.9*br.getHeight())));    /// convert to density independent pixel
	
	  canvas.drawBitmap(br,null,s, null);
	 
 }
}
