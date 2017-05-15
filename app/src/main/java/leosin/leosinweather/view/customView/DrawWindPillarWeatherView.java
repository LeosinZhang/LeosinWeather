package leosin.leosinweather.view.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import leosin.leosinweather.utils.Const;

/**
 * Created by Administrator on 2017/1/21.
 */

public class DrawWindPillarWeatherView extends View{
    private Paint mPaint;
    //决定当前绘制风扇的大小
    private float rate = 1;

    //画布的中心X轴坐标
    float centreXpos;
    //画布的中心Y轴坐标
    float centreYpos;

    public DrawWindPillarWeatherView(Context context){
        super(context);
        mPaint = new Paint();
    }

    public DrawWindPillarWeatherView(Context context, AttributeSet attrs){
        super(context,attrs);
        mPaint = new Paint();
    }

    public DrawWindPillarWeatherView(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        mPaint = new Paint();
    }

    public void setRate(int Rate){
        if(Rate == Const.WINDFAN_BIG){
            rate = 1;
        }else if(Rate == Const.WINDFAN_SMALL){
            rate = 1.5f;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(getResources().getColor(android.R.color.white)); //设置画笔颜色
        LightingColorFilter filter = new LightingColorFilter(Color.WHITE, Color.WHITE);
        mPaint.setColorFilter(filter);
        mPaint.setAntiAlias(true);//防锯齿
        mPaint.setAlpha(100);//设置透明度
        mPaint.setStyle(Paint.Style.FILL); //设置画笔为实心
        mPaint.setStrokeWidth(1); //设置线宽


        Path path = new Path();
        //逆时针画线
        path.moveTo(centreXpos+5/rate,centreYpos+100/rate);
        path.lineTo(centreXpos+2/rate,centreYpos+8);
        path.lineTo(centreXpos-2/rate,centreYpos+8);
        path.lineTo(centreXpos-5/rate,centreYpos+100/rate);
        canvas.drawPath(path,mPaint);
        canvas.drawCircle(centreXpos,(centreYpos+100/rate),5/rate,mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int viewWidth = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int viewHeight = getDefaultSize(getSuggestedMinimumHeight(),heightMeasureSpec);

        centreXpos = viewWidth / 2;
        centreYpos = viewHeight / 2;
        setMeasuredDimension(viewWidth, viewHeight);
    }
}
