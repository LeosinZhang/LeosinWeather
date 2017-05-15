package leosin.leosinweather.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import leosin.leosinweather.R;
import leosin.leosinweather.bean.WeatherBean;
import leosin.leosinweather.utils.Blurred.BlurredView;
import leosin.leosinweather.utils.Const;
import leosin.leosinweather.utils.RxUtil.RetrofitMethods;
import leosin.leosinweather.view.activity.MainActivity;
import leosin.leosinweather.view.customView.DrawAqiWeatherView;
import leosin.leosinweather.view.customView.DrawDailyWeatherView;
import leosin.leosinweather.view.customView.ToolbarHeadDetail;
import leosin.leosinweather.view.customView.ToolbarHeadSimple;

/**
 * Created by Administrator on 2016/11/25.
 */
public class WeatherFragment extends BaseFragment {
    private MainActivity mMainActivity;
    private RetrofitMethods mRetrofitMethods;
    private WeatherBean mWeatherBean;
    private DrawDailyWeatherView mDrawDailyWeatherView;
    private DrawAqiWeatherView mDrawAqiWeatherView;
    private Context mContext;
    private View view;

    private static String BUNDLE_CITY = "";
    private String City;
    private int AQI;
    private String quality;

    private static final int DRAW_LINE = 0; //绘制24小时天气折线
    private static final int DRAW_AQI_CIRCLE = 1; //绘制AQI
    private int X_pos; //绘制24小时天气折线x坐标
    private int Angle; //角度递增绘制AQI圆环

    @BindView(R.id.viewpager_toolbar_detail)
    ToolbarHeadDetail mToolbarHeadDetail;
    @BindView(R.id.viewpager_toolbar_simple)
    ToolbarHeadSimple mToolbarHeadSimple;
    @BindView(R.id.custom_weather_blurredview)
    BlurredView mBlurredView;
    @BindView(R.id.custom_weather_app_bar)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.iv_custom_toolbar_head_detail_navigation)
    ImageView Img_Back_Detail;
    @BindView(R.id.tv_custom_toolbar_head_detail_city)
    TextView Tex_City_Detail;
    @BindView(R.id.tv_custom_toolbar_head_detail_build)
    TextView Tex_Street_Detail;
    @BindView(R.id.iv_custom_toolbar_head_detail_add)
    ImageView Img_Add_Detail;

    @BindView(R.id.img_custom_toolbar_head_simple_navi)
    ImageView Img_Back_Simple;
    @BindView(R.id.iv_custom_toolbar_head_simple_temp)
    ImageView Img_Weather_Simple;
    @BindView(R.id.tv_custom_toolbar_head_simple_temp)
    TextView Tex_Temperature_Simple;
    @BindView(R.id.iv_custom_toolbar_head_simple_add)
    ImageView Img_Add_Simple;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mMainActivity = MainActivity.getInstance();
        mRetrofitMethods = RetrofitMethods.getInstance();
        mContext = getContext();
        view = inflater.inflate(R.layout.viewpager_layout, container, false);
        ButterKnife.bind(this, view);
        InitData();
        Bundle bundle = getArguments();
        if (bundle != null) {
            City = bundle.getString(BUNDLE_CITY);
        }
        return view;
    }

/*    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }*/


    public static WeatherFragment getInstance(String city) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_CITY, city);
        WeatherFragment weatherFragment = new WeatherFragment();
        weatherFragment.setArguments(bundle);
        return weatherFragment;
    }

    private void InitData() {
        mToolbarHeadDetail.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        mToolbarHeadSimple.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        setAppBarLayoutLisenler();
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DRAW_LINE: {
                    X_pos = msg.arg1;
                    mDrawDailyWeatherView.setBackgroundColor(getResources().getColor(R.color.transparent));
                    mDrawDailyWeatherView.setHourlyBean(mWeatherBean, X_pos);
                }
                break;

                case DRAW_AQI_CIRCLE: {
                    Angle = msg.arg1;
                    mDrawAqiWeatherView.setBackgroundColor(getResources().getColor(R.color.transparent));
                    mDrawAqiWeatherView.setAqiBean(AQI, Angle, quality);
                }
                break;

                default:
                    break;
            }
        }
    };

    private class DrawLineThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < mWeatherBean.getResult().getHourly().size(); i++) {
                Message message = new Message();
                message.what = DRAW_LINE;
                message.arg1 = i;
                mHandler.sendMessage(message);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class DrawCircleThread implements Runnable {
        @Override
        public void run() {
            for (int i = 195; i != 165; i += 5) {
                Message message = new Message();
                message.what = DRAW_AQI_CIRCLE;
                message.arg1 = i;
                mHandler.sendMessage(message);
                if (i >= 360) {
                    i = 0;
                }

                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void startDrawLine(DrawDailyWeatherView drawDailyWeatherView, WeatherBean weatherBean) {
        this.mDrawDailyWeatherView = drawDailyWeatherView;
        this.mWeatherBean = weatherBean;
        new Thread(new DrawLineThread()).start();
    }

    public void startDrawCircle(DrawAqiWeatherView drawAqiWeatherView, WeatherBean weatherBean) {
        this.mDrawAqiWeatherView = drawAqiWeatherView;
        this.AQI = Integer.parseInt(weatherBean.getResult().getAqi().getAqi());
        this.quality = weatherBean.getResult().getAqi().getQuality();
        new Thread(new DrawCircleThread()).start();
    }

    private void setAppBarLayoutLisenler() {
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    //Toolbar展开
                    mToolbarHeadDetail.setVisibility(View.VISIBLE);
                    mToolbarHeadSimple.setVisibility(View.GONE);
                    setToolbar1Alpha(255);
                    Intent intent = new Intent();
                    intent.setAction(Const.BROADCAST_ACTION_SWIPEREFRESH_ENABLE);
                    mContext.sendBroadcast(intent);
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    //mMainActivity.mSwipeRefreshWidget.setEnabled(false);
                    Intent intent = new Intent();
                    intent.setAction(Const.BROADCAST_ACTION_SWIPEREFRESH_DISABLE);
                    mContext.sendBroadcast(intent);

                    //Toolbar收起
                    mToolbarHeadDetail.setVisibility(View.GONE);
                    mToolbarHeadSimple.setVisibility(View.VISIBLE);
                    setToolbar2Alpha(255);
                } else {
                    int alpha = 255 - Math.abs(verticalOffset) - 150;
                    int mOffset = Math.abs(verticalOffset) / 10;
                    if (mOffset > 100)
                        mOffset = 100;
                    mBlurredView.setBlurredLevel(mOffset);
                    if (alpha <= 0) {
                        //先判断是否可见，再决定是否收缩toolbar
                        if (mToolbarHeadDetail.getVisibility() == View.VISIBLE) {
                            //mMainActivity.mSwipeRefreshWidget.setEnabled(false);
                            Intent intent = new Intent();
                            intent.setAction(Const.BROADCAST_ACTION_SWIPEREFRESH_DISABLE);
                            mContext.sendBroadcast(intent);

                            mToolbarHeadDetail.setVisibility(View.GONE);
                            mToolbarHeadSimple.setVisibility(View.VISIBLE);
                            setToolbar2Alpha(Math.abs(verticalOffset));
                        }
                    } else {
                        //先判断是否可见，再决定是否张开toolbar
                        if (mToolbarHeadSimple.getVisibility() == View.VISIBLE) {
                            //mMainActivity.mSwipeRefreshWidget.setEnabled(true);
                            Intent intent = new Intent();
                            intent.setAction(Const.BROADCAST_ACTION_SWIPEREFRESH_ENABLE);
                            mContext.sendBroadcast(intent);

                            mToolbarHeadDetail.setVisibility(View.VISIBLE);
                            mToolbarHeadSimple.setVisibility(View.GONE);
                            setToolbar1Alpha(alpha);
                        }
                    }
                }
            }
        });

    }


    //设置展开时各控件的透明度
    public void setToolbar1Alpha(int alpha) {
        Img_Back_Detail.getDrawable().setAlpha(alpha);
        Tex_City_Detail.setTextColor(Color.argb(alpha, 255, 255, 255));
        Img_Add_Detail.getDrawable().setAlpha(alpha);
        Tex_Street_Detail.setTextColor(Color.argb(alpha, 255, 255, 255));
    }

    //设置闭合时各控件的透明度
    public void setToolbar2Alpha(int alpha) {
        Img_Back_Simple.getDrawable().setAlpha(alpha);
        Img_Add_Simple.getDrawable().setAlpha(alpha);
        Img_Weather_Simple.getDrawable().setAlpha(alpha);
        Tex_Temperature_Simple.setTextColor(Color.argb(alpha, 255, 255, 255));
    }

    @Override
    public void fetchData() {
        mRetrofitMethods.getWeatherInfo(City, this.getActivity(), view, this);
    }
}
