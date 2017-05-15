package leosin.leosinweather.ViewHolder;

import android.graphics.Color;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import leosin.leosinweather.R;
import leosin.leosinweather.bean.WeatherBean;
import leosin.leosinweather.view.customView.DrawSunWeatherView;

/**
 * Created by Administrator on 2017/1/26.
 */

public class SunWeatherInfoViewHolder extends RecyclerView.ViewHolder{
    private WeatherBean mWeatherBean;
    private String sunRaise;
    private String sunSet;

    @BindView(R.id.custom_weather_sun_view)
    DrawSunWeatherView drawSunWeatherView;

    public SunWeatherInfoViewHolder(View view,WeatherBean weatherBean){
        super(view);
        this.mWeatherBean = weatherBean;
        ButterKnife.bind(this,view);
    }

    public void bind(){
        String sunRaise = mWeatherBean.getResult().getDaily().get(0).getSunrise();
        String sunSet = mWeatherBean.getResult().getDaily().get(0).getSunset();
        drawSunWeatherView.setBackgroundColor(Color.TRANSPARENT);
        drawSunWeatherView.setTime(sunRaise,sunSet);
    }
}
