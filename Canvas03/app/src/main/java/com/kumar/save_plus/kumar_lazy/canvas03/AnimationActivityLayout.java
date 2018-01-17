package com.kumar.save_plus.kumar_lazy.canvas03;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by kumar_lazy on 7/12/2017.
 */

public class AnimationActivityLayout  extends SurfaceView implements Runnable{

    Paint red_paintbrush_fill, blue_paintbrush_fill, green_paintbrush_fill;
    Paint red_paintbrush_stroke, blue_paintbrush_stroke, green_paintbrush_stroke;

    Path Mr_Square = new Path();
    Path d_Mr_Square= new Path();

    Thread thread=null;
    Boolean canDraw= false;

    Bitmap background;
    SurfaceHolder surfaceHolder;
    Canvas canvas;
    Bitmap black_queen;

    int blackQueen_X, blackQueen_Y;
    int circle_X, circle_Y;

    //cordinate for density pixel
    int d_blackQueen_X, d_blackQueen_Y;
    int d_circle_X, d_circle_Y;



    public AnimationActivityLayout(Context context) {
        super(context);

        surfaceHolder = getHolder();
        background= BitmapFactory.decodeResource(getResources(), R.drawable.background_001);
        black_queen=  BitmapFactory.decodeResource(getResources(), R.drawable.black_queen2);

        //pixel coordinat
        blackQueen_X = 650;
        blackQueen_Y = 130;

        circle_X = 130;
        circle_Y = 130;

        //density pixel coordinate
        d_blackQueen_X= toPxs(325);
        d_blackQueen_Y= toPxs(65);

        d_circle_X= toPxs(65);
        d_circle_Y= toPxs(65);



    }

    @Override
    public void run() {
        //calling method prepPaintBrushes
        prepPaintBrush();

        while(canDraw){
            //carry out some drawing
            //check if surface holder is valid
            if(!surfaceHolder.getSurface().isValid()){
                continue;
            }

            canvas= surfaceHolder.lockCanvas();
            queenMotion(10);
            circleMotion(10);

            d_queenMotion(5);
            d_circleMotion(5);


            canvas.drawBitmap(background, 0, 0, null);
            drawSquare(130, 130, 650, 650);
            canvas.drawBitmap(black_queen, blackQueen_X - (black_queen.getWidth()/2), blackQueen_Y - (black_queen.getHeight()/2), null);
            canvas.drawCircle(circle_X, circle_Y, 50, blue_paintbrush_fill);
            canvas.drawCircle(circle_X, circle_Y, 20, red_paintbrush_fill);
            canvas.drawCircle(circle_X, circle_Y, 70, green_paintbrush_stroke);

            // density pixel coordinate
            drawDensitySquare(65, 65, 325, 325);
            canvas.drawBitmap(black_queen, d_blackQueen_X - (black_queen.getWidth()/2), d_blackQueen_Y - (black_queen.getHeight()/2), null);
            canvas.drawCircle(d_circle_X, d_circle_Y, toPxs(50), blue_paintbrush_fill);
            canvas.drawCircle(d_circle_X, d_circle_Y, toPxs(20), red_paintbrush_fill);
            canvas.drawCircle(d_circle_X, d_circle_Y, toPxs(70), green_paintbrush_stroke);


            surfaceHolder.unlockCanvasAndPost(canvas);
        }

    }

    public void pause(){
        canDraw= false;

        //while is use check if thread is closed neatly
        while(true){
            try {
            //thread join is used  safely closed loop
                thread.join();
                break;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        thread=null;

    }

    public void resume(){
        canDraw= true;
        thread= new Thread(this);
        thread.start();

    }

    private void prepPaintBrush(){
        red_paintbrush_fill= new Paint();
        red_paintbrush_fill.setColor(Color.RED);
        red_paintbrush_fill.setStyle(Paint.Style.FILL);

        blue_paintbrush_fill= new Paint();
        blue_paintbrush_fill.setColor(Color.BLUE);
        blue_paintbrush_fill.setStyle(Paint.Style.FILL);

        green_paintbrush_fill= new Paint();
        green_paintbrush_fill.setColor(Color.GREEN);
        green_paintbrush_fill.setStyle(Paint.Style.FILL);

        red_paintbrush_stroke= new Paint();
        red_paintbrush_stroke.setColor(Color.RED);
        red_paintbrush_stroke.setStyle(Paint.Style.STROKE);
        red_paintbrush_stroke.setStrokeWidth(10);

        blue_paintbrush_stroke= new Paint();
        blue_paintbrush_stroke.setColor(Color.BLUE);
        blue_paintbrush_stroke.setStyle(Paint.Style.STROKE);
        blue_paintbrush_stroke.setStrokeWidth(10);

        green_paintbrush_stroke= new Paint();
        green_paintbrush_stroke.setColor(Color.GREEN);
        green_paintbrush_stroke.setStyle(Paint.Style.STROKE);
        green_paintbrush_stroke.setStrokeWidth(10);
    }

    private void drawSquare(int x1, int y1, int x2, int y2){

        Mr_Square.moveTo(x1, y1);
        Mr_Square.lineTo(x2, y1);
        Mr_Square.moveTo(x2, y1);
        Mr_Square.lineTo(x2, y2);
        Mr_Square.moveTo(x2, y2);
        Mr_Square.lineTo(x1, y2);
        Mr_Square.moveTo(x1, y2);
        Mr_Square.lineTo(x1, y1);

        this.canvas.drawPath(Mr_Square, red_paintbrush_stroke);

    }

    private void queenMotion(int speed){

        if(blackQueen_Y==130 && blackQueen_X < 650){
            blackQueen_X = blackQueen_X + speed + speed;
        }

        if(blackQueen_Y < 650 && blackQueen_X == 650){
            blackQueen_Y = blackQueen_Y + speed + speed;
        }

        if(blackQueen_Y==650 && blackQueen_X > 130){
            blackQueen_X = blackQueen_X - speed - speed;
        }

        if(blackQueen_Y > 130 && blackQueen_X == 130){
            blackQueen_Y = blackQueen_Y - speed - speed;
        }

    }

    private void circleMotion(int speed){

        if(circle_Y==130 && circle_X < 650){
            circle_X = circle_X + speed + speed;
        }

        if(circle_Y < 650 && circle_X == 650){
            circle_Y = circle_Y + speed + speed;
        }

        if(circle_Y==650 && circle_X > 130){
            circle_X = circle_X - speed - speed;
        }

        if(circle_Y > 130 && circle_X == 130){
            circle_Y = circle_Y - speed - speed;
        }

    }

    private int toPxs(int dps){
        return (int) (dps * getResources().getDisplayMetrics().density);
    }

    private void drawDensitySquare(int x3, int y3, int x4, int y4){

        int xdp1= toPxs(x3);
        int ydp1= toPxs(y3);
        int xdp2= toPxs(x4);
        int ydp2= toPxs(y4);


        d_Mr_Square.moveTo(xdp1, ydp1);
        d_Mr_Square.lineTo(xdp2, ydp1);
        d_Mr_Square.moveTo(xdp2, ydp1);
        d_Mr_Square.lineTo(xdp2, ydp2);
        d_Mr_Square.moveTo(xdp2, ydp2);
        d_Mr_Square.lineTo(xdp1, ydp2);
        d_Mr_Square.moveTo(xdp1, ydp2);
        d_Mr_Square.lineTo(xdp1, ydp1);

        this.canvas.drawPath(d_Mr_Square, green_paintbrush_stroke);

    }

    private void d_queenMotion(int speed){

        if(d_blackQueen_Y==toPxs(65) && d_blackQueen_X < toPxs(325)){
            d_blackQueen_X = d_blackQueen_X + toPxs(speed) + toPxs(speed);
        }

        if(d_blackQueen_Y < toPxs(325) && d_blackQueen_X == toPxs(325)){
            d_blackQueen_Y = d_blackQueen_Y + toPxs(speed) + toPxs(speed);
        }

        if(d_blackQueen_Y==toPxs(325) && d_blackQueen_X > toPxs(65)){
            d_blackQueen_X = d_blackQueen_X - toPxs(speed) - toPxs(speed);
        }

        if(d_blackQueen_Y > toPxs(65) && d_blackQueen_X == toPxs(65)){
            d_blackQueen_Y = d_blackQueen_Y - toPxs(speed) - toPxs(speed);
        }

    }

    private void d_circleMotion(int speed){

        if(d_circle_Y== toPxs(65) && d_circle_X < toPxs(325)){
            d_circle_X = d_circle_X + toPxs(speed) + toPxs(speed);
        }

        if(d_circle_Y < toPxs(325) && d_circle_X == toPxs(325)){
            d_circle_Y = d_circle_Y + toPxs(speed) + toPxs(speed);
        }

        if(d_circle_Y==toPxs(325) && d_circle_X > toPxs(65)){
            d_circle_X = d_circle_X - toPxs(speed) - toPxs(speed);
        }

        if(d_circle_Y > toPxs(65) && d_circle_X == toPxs(65)){
            d_circle_Y = d_circle_Y - toPxs(speed) - toPxs(speed);
        }

    }

}
