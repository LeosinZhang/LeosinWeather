package leosin.leosinweather.view.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.orhanobut.logger.Logger;

import leosin.leosinweather.utils.Const;

/**
 * Author: LeosinZhang
 * Time: 2017/1/19:9:05
 * Describle:
 */

public class DrawWindFanWeatherView extends View {
    private Paint mPaint;
    private Path path;
    RotateAnimation mAnimation;
    //自定义View区域的宽度
    private int mViewWidth;
    //自定义View的高度
    private int mViewHeight;

    //决定当前绘制风扇的大小
    private float rate = 1;

    //画布的中心X轴坐标
    float centreXpos;
    //画布的中心Y轴坐标
    float centreYpos;

    //风扇第一个扇叶顶点X轴坐标
    float centreFanXpos;
    //风扇第一个扇叶顶点Y轴坐标
    float centreFanYpos;
    //风扇最上边顶点X轴坐标，三阶贝塞尔终点X坐标
    float Point_Xpos;
    //风扇最上边顶点Y轴坐标，三阶贝塞尔终点Y坐标
    float Point_Ypos;
    //风扇左边控制点X轴坐标，二阶贝塞尔控制点X坐标
    float controlLeftXpos;
    //风扇左边控制点Y轴坐标，二阶贝塞尔控制点Y坐标
    float controlLeftYpos;
    //风扇右边第1个控制点X轴坐标，三阶贝塞尔第1个控制点X坐标
    float controlRightOneXpos;
    //风扇右边第2个控制点Y轴坐标，三阶贝塞尔第2个控制点Y坐标
    float controlRightOneYpos;
    //风扇右边第2个控制点X轴坐标，三阶贝塞尔第2个控制点X坐标
    float controlRightTwoXpos;
    //风扇右边第2个控制点Y轴坐标，三阶贝塞尔第2个控制点Y坐标
    float controlRightTwoYpos;

    //https://github.com/yinyiliang/Windmill
    public DrawWindFanWeatherView(Context context) {
        super(context);
        init();
    }

    public DrawWindFanWeatherView(Context context, AttributeSet attr) {
        super(context, attr);
        init();
    }

    public DrawWindFanWeatherView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
        init();
    }

    public void setRate(int Rate) {
        if (Rate == Const.WINDFAN_BIG) {
            rate = 1;
        } else if (Rate == Const.WINDFAN_SMALL) {
            rate = 1.5f;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        float radius = 6 / rate;
        //drawCircle(float cx, float cy, float radius, Paint paint)
        canvas.drawCircle(centreXpos, centreYpos, radius, mPaint);

        path.moveTo(centreFanXpos, centreFanYpos);
        //三阶贝塞尔曲线 cubicTo(float x1, float y1, float x2, float y2, float x3, float y3)
        path.cubicTo(controlRightOneXpos, controlRightOneYpos,
                controlRightTwoXpos, controlRightTwoYpos,
                Point_Xpos, Point_Ypos);
        //二阶贝塞尔曲线 quadTo(float x1, float y1, float x2, float y2)
        path.quadTo(controlLeftXpos, controlLeftYpos, centreFanXpos, centreFanYpos);
        canvas.drawPath(path, mPaint);

        //将已绘制的扇叶旋转120° 后面参数是将该店坐标作为中心点旋转
        canvas.rotate(120, centreXpos, centreYpos);
        canvas.drawPath(path, mPaint);

        //将已绘制的扇叶旋转240° 后面参数是将该店坐标作为中心点旋转
        canvas.rotate(120, centreXpos, centreYpos);
        canvas.drawPath(path, mPaint);

        invalidate();
    }

    private void init() {
        //X轴坐标向右偏移0.5倍父控件宽度，Y轴坐标向下偏移0.5倍自身宽度，然后围绕该点向右旋转90度
        /*pivotXType 和 pivotXValue分别表示旋转中心在X轴方向相对原点（即控件左上角）的偏移类型和偏移量
        pivotXType有三个可选取值：Animation.ABSOLUTE、Animation.RELATIVE_TO_SELF 和 Animation.RELATIVE_TO_PARENT
        分别表示绝对偏移、相对自身偏移和相对父控件偏移
        当取Animation.ABSOLUTE时，pivotXValue是一个绝对数值，否则pivotXValue是一个相对数值（取值为1表示100%）。
        pivotYType 和 pivotYValue：分别表示旋转中心在Y轴方向相对原点的偏移类型和偏移量
        具体含义同pivotXType 和 pivotXValue。*/

        if (mPaint == null)
            mPaint = new Paint();
        if (path == null)
            path = new Path();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);//防锯齿
        mPaint.setStyle(Paint.Style.FILL); //设置画笔为实心
        mPaint.setStrokeWidth(3); //设置线宽

        if (mAnimation == null)
            mAnimation = new RotateAnimation(0, 360,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);

        mAnimation.setRepeatCount(-1); //设置为无限重复
        mAnimation.setInterpolator(new LinearInterpolator());//匀速
        mAnimation.setFillAfter(true);
    }


    public void startAnim() {
        stopAnim();
        mAnimation.setDuration(3000);//设置速度
        startAnimation(mAnimation);
    }

    public void stopAnim() {
        clearAnimation();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        mViewHeight = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        //风扇中心
        centreXpos = mViewWidth / 2;
        centreYpos = mViewHeight / 2;

        //第一个扇叶下方顶点
        centreFanXpos = centreXpos;
        centreFanYpos = centreYpos - 8 / rate;

        Point_Xpos = centreFanXpos;
        Point_Ypos = centreFanYpos - 70 / rate; //左边二阶贝塞尔曲线起点，即右边三阶贝塞尔曲线终点
        controlLeftXpos = centreFanXpos - 6 / rate;
        controlLeftYpos = centreFanYpos - 40 / rate; //左边二阶贝塞尔曲线控制点
        controlRightOneXpos = centreFanXpos + 6 / rate;
        controlRightOneYpos = centreFanYpos - 10 / rate; // 右边三阶贝塞尔曲线第一个控制点
        controlRightTwoXpos = centreFanXpos + 6 / rate;
        controlRightTwoYpos = centreFanYpos - 20 / rate; // 右边三阶贝塞尔曲线第二个控制点

        setMeasuredDimension(mViewWidth, mViewHeight);
    }
}
