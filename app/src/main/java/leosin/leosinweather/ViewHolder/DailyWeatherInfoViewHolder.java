package leosin.leosinweather.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import leosin.leosinweather.R;
import leosin.leosinweather.bean.WeatherBean;
import leosin.leosinweather.view.customView.DrawDailyWeatherView;
import leosin.leosinweather.view.fragment.WeatherFragment;

/**
 * Author: LeosinZhang
 * Time: 2017/1/10:11:07
 * Describle:
 */

public class DailyWeatherInfoViewHolder extends RecyclerView.ViewHolder {
    private WeatherBean mWeatherBean;
    private WeatherFragment mWeatherFragment;

    @BindView(R.id.custom_weather_daily_chart)
    DrawDailyWeatherView mDrawDailyWeatherView;

    public DailyWeatherInfoViewHolder(View view, WeatherBean weather, WeatherFragment weatherFragment) {
        super(view);
        this.mWeatherBean = weather;
        this.mWeatherFragment = weatherFragment;
        ButterKnife.bind(this, view);
    }

    public void bind() {
        Logger.d(" Daily bind");
        mWeatherFragment.startDrawLine(mDrawDailyWeatherView,mWeatherBean);
    }

}
