package leosin.leosinweather.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import leosin.leosinweather.R;
import leosin.leosinweather.ViewHolder.AqiWeatherInfoViewHolder;
import leosin.leosinweather.ViewHolder.DailyWeatherInfoViewHolder;
import leosin.leosinweather.ViewHolder.SuggestionWeatherInfoViewHolder;
import leosin.leosinweather.ViewHolder.SunWeatherInfoViewHolder;
import leosin.leosinweather.ViewHolder.WeeklyWeatherInfoViewHolder;
import leosin.leosinweather.ViewHolder.WindWeatherInfoViewHolder;
import leosin.leosinweather.bean.WeatherBean;
import leosin.leosinweather.view.fragment.WeatherFragment;

/**
 * Author: LeosinZhang
 * Time: 2016/12/15:10:26
 * Describle:
 */
public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity mMainActivity;
    private Context mContext;
    private WeatherBean mWeatherBean;
    private WeatherFragment mWeatherFragment;
    private RecyclerView mRecyclerView;

    TextView tempreture ;
    TextView tempInfo;

    private static final int TYPE_DAILY_INFO = 0; //未来24小时天气预报
    private static final int TYPE_AQI_INFO = 1; //空气污染指数
    private static final int TYPE_WEEKLY_INFO = 2; //未来10天天气预报
    private static final int TYPE_SUGGEST_INFO = 3; //生活建议
    private static final int TYPE_SUN_RISE_SET_INFO = 4; //日出日落
    private static final int TYPE_WIND_INFO = 5; //风力情况

    public MainAdapter(WeatherBean weatherBean,Activity activity,View view,
                       WeatherFragment weatherFragment, RecyclerView recyclerView) {
        this.mWeatherBean = weatherBean;
        this.mMainActivity = activity;
        this.mWeatherFragment = weatherFragment;
        this.mRecyclerView = recyclerView;
        tempreture = (TextView) view.findViewById(R.id.tv_custom_weather_baseinfo_tempreture);
        tempInfo = (TextView) view.findViewById(R.id.tv_custom_weather_baseinfo_weather);
        tempreture.setText(mWeatherBean.getResult().getTemp());
        tempInfo.setText(mWeatherBean.getResult().getWeather());

    }

    /**
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        switch (viewType) {
            case TYPE_DAILY_INFO:
                View viewDaily = LayoutInflater.from(mMainActivity).inflate(R.layout.weather_daily, parent, false);
                viewDaily.setBackgroundColor(mMainActivity.getResources().getColor(R.color.background));
                return new DailyWeatherInfoViewHolder(viewDaily,mWeatherBean,mWeatherFragment);
            case TYPE_WEEKLY_INFO:
                View viewWeekly = LayoutInflater.from(mMainActivity).inflate(R.layout.weather_weekly, parent, false);
                viewWeekly.setBackgroundColor(mMainActivity.getResources().getColor(R.color.background));
                return new WeeklyWeatherInfoViewHolder(viewWeekly,mWeatherBean);
            case TYPE_AQI_INFO:
                View viewAqi = LayoutInflater.from(mMainActivity).inflate(R.layout.weather_aqi, parent, false);
                viewAqi.setBackgroundColor(mMainActivity.getResources().getColor(R.color.background));
                return new AqiWeatherInfoViewHolder(viewAqi,mWeatherBean,mWeatherFragment);
            case TYPE_WIND_INFO:
                View viewWind = LayoutInflater.from(mMainActivity).inflate(R.layout.weather_wind, parent, false);
                viewWind.setBackgroundColor(mMainActivity.getResources().getColor(R.color.background));
                return new WindWeatherInfoViewHolder(viewWind,mWeatherBean);
            case TYPE_SUN_RISE_SET_INFO:
                View viewSun = LayoutInflater.from(mMainActivity).inflate(R.layout.weather_sun, parent, false);
                viewSun.setBackgroundColor(mMainActivity.getResources().getColor(R.color.background));
                return new SunWeatherInfoViewHolder(viewSun,mWeatherBean);
            case TYPE_SUGGEST_INFO:
                View viewSuggestion = LayoutInflater.from(mMainActivity).inflate(R.layout.weather_suggestion, parent, false);
                viewSuggestion.setBackgroundColor(mMainActivity.getResources().getColor(R.color.background));
                    return new SuggestionWeatherInfoViewHolder(viewSuggestion,mWeatherBean);
            default:
                break;
        }
        return null;
    }

    /**
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemType = getItemViewType(position);
        switch (itemType) {
            case TYPE_DAILY_INFO:
                tempreture.setText(mWeatherBean.getResult().getTemp());
                tempInfo.setText(mWeatherBean.getResult().getWeather());
                ((DailyWeatherInfoViewHolder) holder).bind();
                break;
            case TYPE_WEEKLY_INFO:
                ((WeeklyWeatherInfoViewHolder) holder).bind();
                break;
            case TYPE_AQI_INFO:
                ((AqiWeatherInfoViewHolder) holder).bind();
                break;
            case TYPE_WIND_INFO:
                ((WindWeatherInfoViewHolder) holder).bind();
                break;
            case TYPE_SUN_RISE_SET_INFO:
                ((SunWeatherInfoViewHolder) holder).bind();
                break;
            case TYPE_SUGGEST_INFO:
                ((SuggestionWeatherInfoViewHolder) holder).bind();
                break;
            default:
                break;
        }
    }


    /**
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mWeatherBean.getStatus() != null ? 6 : 0;
    }

    @Override
    public int getItemViewType(int position) {
        mRecyclerView.getRecycledViewPool().clear();
        return position;
    }





}
