package leosin.leosinweather.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import leosin.leosinweather.R;
import leosin.leosinweather.bean.WeatherBean;
import leosin.leosinweather.view.customView.DrawAqiWeatherView;
import leosin.leosinweather.view.fragment.WeatherFragment;

/**
 * Author: LeosinZhang
 * Time: 2017/1/16:10:58
 * Describle:
 */

public class AqiWeatherInfoViewHolder extends RecyclerView.ViewHolder {
    private WeatherBean mWeatherBean;
    private WeatherFragment mWeatherFragment;

    @BindView(R.id.custom_weather_aqi_view)
    DrawAqiWeatherView mDrawAqiWeatherView;
    @BindView(R.id.custom_weather_aqi_pm10_text)
    TextView textPM10;
    @BindView(R.id.custom_weather_aqi_pm25_text)
    TextView textPM25;
    @BindView(R.id.custom_weather_aqi_no2_text)
    TextView textNO2;
    @BindView(R.id.custom_weather_aqi_so2_text)
    TextView textSO2;
    @BindView(R.id.custom_weather_aqi_o3_text)
    TextView textO3;
    @BindView(R.id.custom_weather_aqi_co_text)
    TextView textCO;

    public AqiWeatherInfoViewHolder(View view, WeatherBean weatherBean,WeatherFragment weatherFragment) {
        super(view);
        this.mWeatherBean = weatherBean;
        this.mWeatherFragment = weatherFragment;
        ButterKnife.bind(this,view);
    }

    public void bind(){
        textPM10.setText(mWeatherBean.getResult().getAqi().getPm10());
        textPM25.setText(mWeatherBean.getResult().getAqi().getPm2_5());
        textNO2.setText(mWeatherBean.getResult().getAqi().getNo2());
        textSO2.setText(mWeatherBean.getResult().getAqi().getSo2());
        textO3.setText(mWeatherBean.getResult().getAqi().getO3());
        textCO.setText(mWeatherBean.getResult().getAqi().getCo());

        mWeatherFragment.startDrawCircle(mDrawAqiWeatherView,mWeatherBean);
    }
}
