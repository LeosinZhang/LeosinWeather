package leosin.leosinweather.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import leosin.leosinweather.App;
import leosin.leosinweather.R;
import leosin.leosinweather.bean.WeatherBean;
import leosin.leosinweather.compoment.ImageLoader;
import leosin.leosinweather.utils.SharedPreferenceUtil;

/**
 * Author: LeosinZhang
 * Time: 2016/12/22:9:54
 * Describle:
 */
public class WeeklyWeatherInfoViewHolder extends RecyclerView.ViewHolder {
    private LinearLayout WeeklyWeatherLayout;
    private WeatherBean mWeatherBean;

    private TextView[] Week;
    private TextView[] WeatherInfo;
    private TextView[] tempreture;
    private ImageView[] weatherIcon;

    public WeeklyWeatherInfoViewHolder(View view, WeatherBean weather) {
        super(view);
        this.mWeatherBean = weather;
        init();
        WeeklyWeatherLayout = (LinearLayout) view.findViewById(R.id.custom_weather_weekly_cardView);
        for (int i = 0; i < mWeatherBean.result.getDaily().size(); i++) {
            View itemView = view.inflate(App.context, R.layout.weather_weekly_item, null);
            Week[i] = (TextView) itemView.findViewById(R.id.weekly_item_week);
            WeatherInfo[i] = (TextView) itemView.findViewById(R.id.weekly_item_weather);
            tempreture[i] = (TextView) itemView.findViewById(R.id.weekly_item_temp);
            weatherIcon[i] = (ImageView) itemView.findViewById(R.id.weekly_item_icon);
            WeeklyWeatherLayout.addView(itemView);
        }
    }


    public void bind() {
        try {
            for (int i = 0; i < mWeatherBean.result.getDaily().size(); i++) {
                Week[i].setText(mWeatherBean.result.getDaily().get(i).getWeek());
                WeatherInfo[i].setText(mWeatherBean.result.getDaily().get(i).getDay().getWeather());
                tempreture[i].setText(
                        mWeatherBean.result.getDaily().get(i).getNight().getTemplow() +
                                " - " +
                                mWeatherBean.result.getDaily().get(i).getDay().getTemphigh() +
                                "°");
                ImageLoader.load(itemView.getContext(),
                        SharedPreferenceUtil.getInstance().getInt(mWeatherBean.result.getDaily().get(i).getDay().getImg(),
                                R.mipmap.icon_99),
                        weatherIcon[i]);
            }
        } catch (Exception e) {
        }
    }

    private void init() {
        Week = new TextView[mWeatherBean.result.getDaily().size()];
        WeatherInfo = new TextView[mWeatherBean.result.getDaily().size()];
        tempreture = new TextView[mWeatherBean.result.getDaily().size()];
        weatherIcon = new ImageView[mWeatherBean.result.getDaily().size()];
    }

}
