package leosin.leosinweather.ViewHolder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import leosin.leosinweather.R;
import leosin.leosinweather.bean.WeatherBean;
import leosin.leosinweather.utils.Const;
import leosin.leosinweather.view.customView.DrawWindFanWeatherView;
import leosin.leosinweather.view.customView.DrawWindPillarWeatherView;

/**
 * Author: LeosinZhang
 * Time: 2017/1/19:11:41
 * Describle:
 */

public class WindWeatherInfoViewHolder extends RecyclerView.ViewHolder{
    private WeatherBean mWeatherBean;

    @BindView(R.id.custom_weather_wind_view_big)
    DrawWindFanWeatherView mWindWeatherViewBig;
    @BindView(R.id.custom_weather_pillar_view_big)
    DrawWindPillarWeatherView mDrawWindPillarWeatherViewBig;
    @BindView(R.id.custom_weather_wind_view_small)
    DrawWindFanWeatherView mWindWeatherViewSmall;
    @BindView(R.id.custom_weather_wind_pillar_view_small)
    DrawWindPillarWeatherView mDrawWindPillarWeatherViewSmall;
    @BindView(R.id.custom_weather_wind_direct_text)
    TextView windDirect;
    @BindView(R.id.custom_weather_wind_speed_text)
    TextView windSpeed;
    @BindView(R.id.custom_weather_wind_power_text)
    TextView windPower;


    public WindWeatherInfoViewHolder(View view, WeatherBean weatherBean){
        super(view);
        this.mWeatherBean = weatherBean;
        ButterKnife.bind(this,view);
    }

    public void bind(){
        windDirect.setText(mWeatherBean.getResult().getWinddirect());
        windSpeed.setText(mWeatherBean.getResult().getWindspeed());
        windPower.setText(mWeatherBean.getResult().getWindpower());

        //绘制大风扇
        mWindWeatherViewBig.setBackgroundColor(Color.TRANSPARENT);
        mWindWeatherViewBig.setRate(Const.WINDFAN_BIG);
        mWindWeatherViewBig.startAnim();

        //绘制小风扇
        mWindWeatherViewSmall.setBackgroundColor(Color.TRANSPARENT);
        mWindWeatherViewSmall.setRate(Const.WINDFAN_SMALL);
        mWindWeatherViewSmall.startAnim();

        //绘制大风扇支柱
        mDrawWindPillarWeatherViewBig.setRate(Const.WINDFAN_BIG);
        //绘制小风扇支柱
        mDrawWindPillarWeatherViewSmall.setRate(Const.WINDFAN_SMALL);
    }

}
