package processing.test.digitalclock;

/* autogenerated by Processing revision 1286 on 2023-01-11 */
import processing.core.*;
import processing.data.*;
import processing.event.*;
import processing.opengl.*;

import java.util.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class digitalclock extends PApplet {



PImage moon,sun,background1,background2;
DigitalClock digitalClock;
float halfWide, halfHeight;
int plantSize;
int textColor;



 public void setup() {
  /* size commented out by preprocessor */;
  frameRate(1);
  
  textColor = 255;
  
  halfWide = width/2;
  halfHeight = height/2;
  digitalClock = new DigitalClock(40, width/2, height/2); 
  plantSize = (width+height)/(5*2);
  
  
  moon = loadImage("moon.png");
  sun = loadImage("sunset.png");
  
  
  background1 = loadImage("gra1.png");
  background2 = loadImage("gra2.png");
  
  
  
  
}

 public void draw() {
  // 실제 시간 
  digitalClock.getTime();
  // 테스트용 빠른 시간
  //digitalClock.getTestTime();
  
  digitalClock.display();
}


class DigitalClock extends Clock {
  int fontSize;
  float x, y;


  DigitalClock(int _fontSize, float _x, float _y) {
    fontSize = _fontSize;
    x = _x;
    y = _y;
    
  }

   public void getTime() {
    super.getTime();
  }
  


   public void display() {
    
    drawBackground();
    fill(244,164,96);
    noStroke();
    ellipse(width/2, height, width*1.3f,height*0.9f);
    
    drawSky();
  
    
    
    fill(textColor);
    textSize(width/5);
    textAlign(RIGHT);
    text (nf(h,2),x,y);
    
    textSize(width/10);
    textAlign(LEFT);
    text(":"+nf(m,2) , x, width/2);
    
    textAlign(RIGHT);
    textSize(width/20);
    text( month + " " + String.valueOf(day()) , x-15, y+50);
    
    textSize(width/20);
    textAlign(LEFT);
    text(String.valueOf(dayOfWeek) , width/2 + 20, y+50);
    
    //text(cal.DATE, x +30, y + 150);
    
  } 
  
   public void drawBackground(){
    if( 0 <= h && h < 6){
      image(background2, 0, height*(h*-1) ,width, height*6);
      textColor = 255;
    }
    else if( 6 <= h && h < 12){
      image(background1, 0, height*(6-h) ,width, height*6);
      textColor = 0;
    }
    else if( 12 <= h && h < 18){
      image(background1, 0, height*(h-17) ,width, height*6);
      textColor = 0;
    }
    else{
      image(background2, 0, height*(h-23) ,width, height*6);
      textColor = 255;
    }
  }
  
   public void drawSky(){
      // 1시간에 30도, 1분에 30/60 = 0.5도 -> 2분에 1도
    float ts = radians(h*30 + m/2 - 90);
    float x = (halfWide * 0.7f) * cos(ts)+halfWide-plantSize/2;
    float y = (halfHeight * 0.7f) * sin(ts)+halfHeight-plantSize/2;
    //noFill();
    //circle(halfWide,halfHeight,(halfWide * 0.7)*2);
    
    if( 6 <= h && h < 18) image(sun, x, y, plantSize*0.8f, plantSize*0.8f);
    else image(moon, x, y,plantSize,plantSize );
    
}
  
}

class Clock {
  int h, m;
  Calendar cal;
  String dayOfWeek, month;
  
  public Clock(){
    cal = Calendar.getInstance();
    cal.set(year(),month(),day(), hour(), minute());
    dayOfWeek = getDayOfWeek();
    month = getDayOfMonth();
    h = hour();
    m = minute();
  }

   public void getTime() {
    int preHour = h;
    h = hour();
    m = minute();
    if(preHour == 23 && (h == 0 || h == 24)) dayOfWeek = getDayOfWeek();
  }
  
   public void getTestTime(){
      int preHour = h;
      if(m != 0) m = 0; else m = 30;
      if(m == 0) h = (h+1)%24;
      if(preHour == 23 && (h == 0 || h == 24)) dayOfWeek = getDayOfWeek();
  }
  
   public String getDayOfWeek(){
    switch (cal.get(Calendar.DAY_OF_WEEK)) {
      case 1:
          return  "Sun";
      case 2:
          return "Mon";
      case 3:
          return "Tue";
      case 4:
          return "Wed";
      case 5:
          return "Thu";
      case 6:
          return "Fri";
      default :
          return "Sat";
    }
  }

 public String getDayOfMonth(){
    switch (month()) {
      case 1:
          return  "Jan";
      case 2:
          return "Feb";
      case 3:
          return "Mar";
      case 4:
          return "Apr";
      case 5:
          return "May";
      case 6:
          return "Jun";
      case 7 :
          return "Jul";
      case 8 :
          return "Aug";
      case 9 :
          return "Sep";
     case 10 :
          return "Oct";
     case 11:
           return "Nov";
     default:
           return "Dec";
         
    }
  }

}


  public void settings() { fullScreen(); }

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "digitalclock" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
