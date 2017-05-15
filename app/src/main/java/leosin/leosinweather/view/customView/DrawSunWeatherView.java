package leosin.leosinweather.view.customView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.orhanobut.logger.Logger;

import java.util.Calendar;

import leosin.leosinweather.R;
import leosin.leosinweather.utils.SharedPreferenceUtil;

/**
 * Created by Administrator on 2017/1/26.
 */

public class DrawSunWeatherView extends View{
    //设置画笔
    private Paint mPaint;
    private Paint textPaint;
    //日出的时间
    private String up;
    private float sunRaise;
    //日落的时间
    private String down;
    private float sunSet;
    //当前的时间   确定太阳的位置
    private float nowTime;
    //根据当前时间确定位置比例
    private float scale;
    //画布的宽度
    private float viewHeight;
    //画布的高度
    private float viewWidth;
    //圆的半径
    private float Radius;
    //圆心的X轴坐标
    private float Circle_Xpos;
    //圆心的Y轴坐标
    private float Circle_Ypos;
    //距离边缘



    public DrawSunWeatherView(Context context){
        super(context);
        init();
    }

    public DrawSunWeatherView(Context context, AttributeSet attr){
        super(context,attr);
        init();
    }

    public DrawSunWeatherView(Context context, AttributeSet attr, int defStyle){
        super(context,attr,defStyle);
        init();
    }

    public void setTime(String raise, String set){
        this.up = raise;
        this.down = set;

        String upTime[] = up.split(":");
        sunRaise = Integer.parseInt(upTime[0]);

        String downTime[] = down.split(":");
        sunSet = Integer.parseInt(downTime[0]);

        Calendar c = Calendar.getInstance();
        nowTime = c.get(Calendar.HOUR_OF_DAY);

        scale = (nowTime-sunRaise)/(sunSet - sunRaise);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(getResources().getColor(android.R.color.white)); //设置画笔颜色
        LightingColorFilter filter = new LightingColorFilter(Color.WHITE, Color.WHITE);
        mPaint.setColorFilter(filter);
        mPaint.setAntiAlias(true);//防锯齿
        mPaint.setStyle(Paint.Style.STROKE); //设置画笔为空心
        mPaint.setStrokeWidth(3); //设置线宽

        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(25); //设置字体大小
        textPaint.setColor(Color.WHITE);

        canvas.drawLine(50,viewHeight,viewWidth,viewHeight,mPaint);

        float tan = 100/Radius;
        float degress =  (float) Math.toDegrees (Math.atan (tan)) ;
        float startDegress = 182 + degress;
        float stopDegress = 90 - degress;
        int drawNum = (int)(stopDegress/2); //每2°画一个圆弧 整体效果是虚线的半圆弧线 看看需要画多少个2°圆弧

        RectF oval = new RectF(50,50,viewWidth,(viewHeight+100)*2);
        for(int i = 0;i<drawNum;i++){
            canvas.drawArc(oval,startDegress,2,false,mPaint); //数字为扫描的度数
            startDegress += 4; //上面计算的是1/2的弧度 这里每次跳2° 也刚好画完一个完整圆弧
        }
        canvas.drawText("日出："+ "\n" + up,20,viewHeight + 50,textPaint);
        canvas.drawText("日落："+ "\n" + down,viewWidth - 150,viewHeight + 50,textPaint);

        float sunXpos = (viewWidth-50)*scale + 50;
        float Xdis = Math.abs(Circle_Xpos - sunXpos);
        float sunYpos = (float) Math.sqrt(Radius*Radius - Xdis*Xdis) - 100; //100是底部高度 10是太阳定点到中心距离

        filter = new LightingColorFilter(Color.YELLOW, Color.YELLOW);
        mPaint.setColorFilter(filter);

        int bitmapID = SharedPreferenceUtil.getInstance().getInt("0", R.mipmap.icon_99);
        Bitmap sunBitmap = BitmapFactory.decodeResource(getResources(), bitmapID);

        canvas.drawBitmap(sunBitmap,
                sunXpos,
                sunYpos,
                mPaint);
        Logger.d("sunXpos" + sunXpos + "\n" + "sunYpos" + sunYpos);
    }

    private void init() {
        mPaint = new Paint();
        textPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = getDefaultSize(getSuggestedMinimumWidth(),widthMeasureSpec);
        viewHeight = getDefaultSize(getSuggestedMinimumHeight(),heightMeasureSpec);
        Logger.d("viewHeight: " + viewHeight + "\n" + "viewWidth: " + viewWidth);

        Circle_Xpos = viewWidth / 2 ;
        Circle_Ypos = viewHeight;

        viewWidth  -= 50;
        viewHeight -= 100;
        Radius = viewWidth / 2 - 50;
    }
}
