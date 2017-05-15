package leosin.leosinweather.view.customView;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import leosin.leosinweather.R;

/**
 * Author: LeosinZhang
 * Time: 2017/1/16:11:20
 * Describle:
 */

public class DrawAqiWeatherView extends View {
    private Paint mPaint;
    private Canvas mCanvas;

    //自定义View区域的宽度
    private int viewWidth;
    //自定义View区域的高度
    private int viewHeight;
    //绘制点的圆心X坐标
    private int Circle_Xpos;
    //绘制点的圆心Y坐标
    private int Circle_Ypos;
    //绘制点的圆的半径
    private int Circle_Radius;

    //当前AQI值
    private int AQI;
    //当前天气污染情况
    private String WeatherQuality;
    //当前需要绘制的角度
    private int Angle;
    //用于存放待绘制的点位
    private List<Map<String, Integer>> mListPoint = new ArrayList<Map<String, Integer>>();
    //绘制起点X坐标
    private String START_Xpos = "start_Xpos";
    //绘制起点Y坐标
    private String START_Ypos = "start_Ypos";
    //绘制终点X坐标
    private String END_Xpos = "end_Xpos";
    //绘制终点Y坐标
    private String END_Ypos = "end_Ypos";


    public DrawAqiWeatherView(Context context) {
        super(context);
        mPaint = new Paint();
    }

    public DrawAqiWeatherView(Context context, AttributeSet attrs){
        super(context,attrs);
        mPaint = new Paint();
    }

    public DrawAqiWeatherView(Context context,AttributeSet attrs,int defstyle){
        super(context,attrs,defstyle);
        mPaint = new Paint();
    }

    public void setAqiBean(int Aqi, int angle, String quality){
        this.AQI = Aqi;
        this.Angle = angle;
        this.WeatherQuality = quality;

        int endPoint_Radius = Circle_Radius - 25;//30为圆环每天直线长度 即圆弧宽度

        int start_Xpos = Circle_Xpos + (int)(Circle_Radius * (Math.sin(Math.toRadians(angle))) );
        int start_Ypos = Circle_Ypos - (int)(Circle_Radius * (Math.cos(Math.toRadians(angle)))) ;
        int end_Xpos = Circle_Xpos + (int)(endPoint_Radius * (Math.sin(Math.toRadians(angle))) );
        int end_Ypos = Circle_Ypos - (int)(endPoint_Radius * (Math.cos(Math.toRadians(angle))) );

        Map<String,Integer> point = new HashMap<String,Integer>();
        point.put(START_Xpos,start_Xpos);
        point.put(START_Ypos,start_Ypos);
        point.put(END_Xpos,end_Xpos);
        point.put(END_Ypos,end_Ypos);
        mListPoint.add(point);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(getResources().getColor(android.R.color.white)); //设置画笔颜色
        LightingColorFilter filter = new LightingColorFilter(Color.WHITE, Color.WHITE);
        mPaint.setColorFilter(filter);
        mPaint.setAntiAlias(true);//防锯齿
        mPaint.setStyle(Paint.Style.FILL); //设置画笔为实心
        mPaint.setStrokeWidth(3); //设置线宽
        mPaint.setTextSize(100); //设置字体大小

        int AQI_Xpos,Text_Xpos;
        int AQI_Ypos = Circle_Ypos+20;
        int Text_Ypos = Circle_Ypos + 60;
        if(AQI<100){
            AQI_Xpos = Circle_Xpos - 50;
            Text_Xpos = Circle_Xpos - 10;
        }else {
            AQI_Xpos = Circle_Xpos - 80;
            Text_Xpos = Circle_Xpos - 50;
        }
        //drawText(String text, float x, float y, Paint paint)
        canvas.drawText(""+AQI,AQI_Xpos,AQI_Ypos,mPaint);
        mPaint.setTextSize(30); //设置字体大小
        canvas.drawText(""+WeatherQuality,Text_Xpos,Text_Ypos,mPaint);


        if(0 < AQI && AQI <= 50){
            filter = new LightingColorFilter(Color.GREEN, Color.GREEN);
        }else if(50 < AQI && AQI <= 100){
            filter = new LightingColorFilter(Color.YELLOW, Color.YELLOW);
        }else if(100 < AQI && AQI <= 150){
            filter = new LightingColorFilter(getResources().getColor(R.color.orange), getResources().getColor(R.color.orange));
        }else if(150 < AQI && AQI <= 200){
            filter = new LightingColorFilter(Color.RED, Color.RED);
        }else if(200 < AQI && AQI <= 300){
            filter = new LightingColorFilter(getResources().getColor(R.color.purple), getResources().getColor(R.color.purple));
        }else if(300 < AQI && AQI <= 500){
            filter = new LightingColorFilter(getResources().getColor(R.color.bordeaux), getResources().getColor(R.color.bordeaux));
        }
        mPaint.setColorFilter(filter);

        for(int i=0; i<mListPoint.size(); i++){
            int start_Xpos = mListPoint.get(i).get(START_Xpos);
            int start_Ypos = mListPoint.get(i).get(START_Ypos);
            int end_Xpos = mListPoint.get(i).get(END_Xpos);
            int end_Ypos = mListPoint.get(i).get(END_Ypos);
            //drawLine(float startX, float startY, float stopX, float stopY, Paint paint)
            canvas.drawLine(start_Xpos,start_Ypos,end_Xpos,end_Ypos,mPaint);
        }

        invalidate();//相当于调用onDraw方法
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        viewWidth = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        viewHeight = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        Circle_Xpos = (viewWidth - 40)/2;
        Circle_Ypos = (viewHeight - 40)/2;

        if(Circle_Xpos > Circle_Ypos){
            Circle_Radius = Circle_Ypos;
        }else {
            Circle_Radius = Circle_Xpos;
        }

        Circle_Xpos += 40;
        Circle_Ypos += 20;
        setMeasuredDimension(viewWidth, viewHeight);
    }




}
