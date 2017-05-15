package leosin.leosinweather.view.customView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import leosin.leosinweather.R;
import leosin.leosinweather.bean.WeatherBean;
import leosin.leosinweather.utils.SharedPreferenceUtil;

/**
 * Author: LeosinZhang
 * Time: 2017/1/10:16:46
 * Describle:
 */

public class DrawDailyWeatherView extends View {
    private WeatherBean.ResultBean mHourlyBean;
    //用于存放待绘制的折线点位
    private List<Map<String, Integer>> mListPoint = new ArrayList<Map<String, Integer>>();
    //用于存放待绘制的折线点位时间值
    private List<Map<String, String>> mListTime = new ArrayList<Map<String, String>>();
    //纵坐标KEY值
    private String X_KEY = "Xpos";
    //纵坐标KEY值
    private String Y_KEY = "Ypos";
    //对应横坐标的天气图标
    private String temp_Img = "temp_image";
    //对应横坐标的时间
    private String Time = "time";
    //画笔
    private Paint mPaint = null;
    //温度最大值
    private int MaxValue;
    //温度最小值
    private int MinValue;
    //小时天气节点数量
    private int HourNum = 0;
    //24h气温温差
    private int tempDif = 0;
    //折线每格高度单位
    private float hightUnit;
    //用于定位的基准温度值
    private int baseTemp;
    //横向ScrollView长度
    private int viewWidth;
    //天气折线区域高度
    private int chartHeight = 300;
    //判断是否已经计算过温差等参数
    private boolean hasRunCalculate = false;

    public DrawDailyWeatherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawDailyWeatherView(Context context) {
        super(context);
        init();
    }

    public DrawDailyWeatherView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void setHourlyBean(WeatherBean hourlyBean, int xPos) {
        this.mHourlyBean = hourlyBean.getResult();
        //Logger.d("第" + xPos + "个数据");
        if (!mHourlyBean.getHourly().isEmpty()) {
            if (!hasRunCalculate) {
                hasRunCalculate = !hasRunCalculate;
                HourNum = mHourlyBean.getHourly().size();

                for (int i = 0; i < HourNum; i++) {
                    int value = Integer.parseInt(mHourlyBean.getHourly().get(i).getTemp());
                    if (i == 0) {
                        MaxValue = value;
                        MinValue = MaxValue;
                    } else {

                        if (value > MaxValue)
                            MaxValue = value;
                        if (value < MinValue)
                            MinValue = value;
                    }
                }
                tempDif = MaxValue - MinValue;
                hightUnit = (chartHeight - 100) / tempDif;
                baseTemp = MinValue;
            }
            Map<String, Integer> temp = new HashMap<String, Integer>();
            Map<String, String> time = new HashMap<String, String>();

            if(mListPoint.size() >= 24){
                mListPoint.clear();
                mListTime.clear();
            }
            temp.put(X_KEY, 50 + 100 * xPos);
            temp.put(Y_KEY, Integer.parseInt(mHourlyBean.getHourly().get(xPos).getTemp()));
            temp.put(temp_Img, Integer.parseInt(mHourlyBean.getHourly().get(xPos).getImg()));
            mListPoint.add(temp);
            time.put(Time, mHourlyBean.getHourly().get(xPos).getTime());
            mListTime.add(time);
        }
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.white)); //设置画笔颜色
        mPaint.setAntiAlias(true);//防锯齿
        mPaint.setStyle(Paint.Style.FILL); //设置画笔为实心
        mPaint.setStrokeWidth(3); //设置空心线宽
        mPaint.setTextSize(25);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < mListPoint.size(); i++) {
            //画折线drawLine(float startX, float startY, float stopX, float stopY, Paint paint)
            if (i < mListPoint.size() - 1) {
                canvas.drawLine(mListPoint.get(i).get(X_KEY),
                        chartHeight - (mListPoint.get(i).get(Y_KEY) - baseTemp) * hightUnit,
                        mListPoint.get(i + 1).get(X_KEY),
                        chartHeight - (mListPoint.get(i + 1).get(Y_KEY) - baseTemp) * hightUnit,
                        mPaint);
            }

            //画圆点 drawCircle(float cx, float cy, float radius, Paint paint)
            canvas.drawCircle(mListPoint.get(i).get(X_KEY),
                    chartHeight - (mListPoint.get(i).get(Y_KEY) - baseTemp) * hightUnit,
                    4,
                    mPaint);

            //折线上方文字描述drawText(String text, float x, float y, Paint paint)
            canvas.drawText(mListPoint.get(i).get(Y_KEY) + "°",
                    mListPoint.get(i).get(X_KEY) - 10,
                    chartHeight - (mListPoint.get(i).get(Y_KEY) - baseTemp) * hightUnit - 20,
                    mPaint);

            //折线下方天气图标 drawBitmap(Bitmap bitmap, float left, float top, Paint paint)
            String tempImg = Integer.toString(mListPoint.get(i).get(temp_Img));
            int bitmapID = SharedPreferenceUtil.getInstance().getInt(tempImg, R.mipmap.icon_99);
            Bitmap iconBitmap = BitmapFactory.decodeResource(getResources(), bitmapID);
            canvas.drawBitmap(iconBitmap,
                    mListPoint.get(i).get(X_KEY) - 20,
                    chartHeight + 40,
                    mPaint);

            //折线下方天气时刻描述 drawText(String text, float x, float y, Paint paint)
            canvas.drawText(mListTime.get(i).get(Time),
                    mListPoint.get(i).get(X_KEY) - 20,
                    chartHeight + 120,
                    mPaint);
        }
        invalidate();//相当于调用onDraw方法
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int unitWidth = 100;
        if (HourNum != 0) {
            //根据数据条数设置宽度
            viewWidth = unitWidth * HourNum + 20;
        } else {
            //通过调用onMeasure源码中的方法进行获得宽度，
            viewWidth = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        }
        setMeasuredDimension(viewWidth, heightMeasureSpec);
    }
}
