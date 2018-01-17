package com.kumar.save_plus.kumar_lazy.canvas02;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by kumar_lazy on 7/12/2017.
 */

public class Animation001ActivityLayout extends View {
    Paint red_paintbrush_fill, blue_paintbrush_fill, green_paintbrush_fill;
    Paint red_paintbrush_stroke, blue_paintbrush_stroke, green_paintbrush_stroke;
    Path Mr_Triangle;
    Bitmap blueBall_bp;
    int blueBall_X, blueBall_Y;
    int dir_X, dir_Y;
    int blueBallWidth, blueBallHeight;

    public Animation001ActivityLayout(Context context) {
        super(context);
        setBackgroundResource(R.drawable.background_001);
        blueBall_X=310;
        blueBall_Y=620;

        dir_X=5;
        dir_Y=5;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

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

        /*
        Rect rect01= new Rect();
        rect01.set(120, 140, 350, 175);
        canvas.drawRect(rect01, green_paintbrush_fill);

        Rect rect02= new Rect();
        rect02.set(400, 140, 450, 175);
        canvas.drawRect(rect02, blue_paintbrush_stroke);

        canvas.drawCircle(400, 200, 100, red_paintbrush_stroke);
        */

        Mr_Triangle = new Path();

        Mr_Triangle.moveTo(canvas.getWidth()/2, canvas.getHeight()/2);
        Mr_Triangle.lineTo(canvas.getWidth()/1.2f, canvas.getHeight()/1.3f);
        Mr_Triangle.moveTo(canvas.getWidth()/1.2f, canvas.getHeight()/1.3f);
        Mr_Triangle.lineTo(canvas.getWidth()/5, canvas.getHeight()/1.3f);
        Mr_Triangle.moveTo(canvas.getWidth()/5, canvas.getHeight()/1.3f);
        Mr_Triangle.lineTo(canvas.getWidth()/2, canvas.getHeight()/2);

        canvas.drawPath(Mr_Triangle, green_paintbrush_stroke);

                              //first set of circles
        canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2, 70, blue_paintbrush_fill);
        canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2, 30, red_paintbrush_fill);
        canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2, 100, blue_paintbrush_stroke);

                             //second set of circle right most
        canvas.drawCircle(canvas.getWidth()/1.2f, canvas.getHeight()/1.3f, 70, blue_paintbrush_fill);
        canvas.drawCircle(canvas.getWidth()/1.2f, canvas.getHeight()/1.3f, 30, red_paintbrush_fill);
        canvas.drawCircle(canvas.getWidth()/1.2f, canvas.getHeight()/1.3f, 100, blue_paintbrush_stroke);

                            //second set of circle left most
        canvas.drawCircle(canvas.getWidth()/5, canvas.getHeight()/1.3f, 70, blue_paintbrush_fill);
        canvas.drawCircle(canvas.getWidth()/5, canvas.getHeight()/1.3f, 30, red_paintbrush_fill);
        canvas.drawCircle(canvas.getWidth()/5, canvas.getHeight()/1.3f, 100, blue_paintbrush_stroke);

        //setting bitmap here
        blueBall_bp= BitmapFactory.decodeResource(getResources(), R.drawable.blueball);

        //getting bounds of the bitmap
        BitmapFactory.Options options= new BitmapFactory.Options();
        options.inJustDecodeBounds= true;
        BitmapFactory.decodeResource(getResources(), R.drawable.blueball, options);

        //getting actual height and widths
        blueBallHeight= options.outHeight;
        blueBallWidth= options.outWidth;

        //setting conditions for x and y coordinate
        //minus blueBallWight two times for left and right side
        if(blueBall_X >= canvas.getWidth() - blueBallWidth- blueBallWidth){
            dir_X = -5;
        }

        if(blueBall_X <= 0){
            dir_X = 5;
        }

        //minus blueBallHeight two times for Top and bottom side
        if(blueBall_Y >= canvas.getHeight() - blueBallHeight -blueBallHeight ){
            dir_Y = -5;
        }

        if(blueBall_Y <= 0){
            dir_Y = 5;
        }

        // giving motion to bitmap
        blueBall_X  += dir_X ;
        blueBall_Y  += dir_Y ;

        canvas.drawBitmap(blueBall_bp, blueBall_X, blueBall_Y, null );

        //to start endless loop
        invalidate();

    }
}
