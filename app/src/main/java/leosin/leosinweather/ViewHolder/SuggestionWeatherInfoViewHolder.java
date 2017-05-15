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
 * Time: 2017/1/24:10:15
 * Describle:
 */

public class SuggestionWeatherInfoViewHolder extends RecyclerView.ViewHolder {
    private WeatherBean mWeatherBean;
    private LinearLayout SuggestionWeatherLayout;

    private ImageView[] mImageView;
    private TextView[] tvType;
    private TextView[] tvBrife;
    private TextView[] tvDetails;


    public SuggestionWeatherInfoViewHolder(View view, WeatherBean weatherBean) {
        super(view);
        this.mWeatherBean = weatherBean;
        init();
        SuggestionWeatherLayout = (LinearLayout) view.findViewById(R.id.custom_weather_suggestion_cardView);

        for (int i = 0; i < mWeatherBean.getResult().getIndex().size(); i++) {
            View itemView = view.inflate(App.context, R.layout.weather_suggestion_item, null);
            mImageView[i] = (ImageView) itemView.findViewById(R.id.custom_weather_suggestion_img);
            tvType[i] = (TextView) itemView.findViewById(R.id.custom_weather_suggestion_type);
            tvBrife[i] = (TextView) itemView.findViewById(R.id.custom_weather_suggestion_brief);
            tvDetails[i] = (TextView) itemView.findViewById(R.id.custom_weather_suggestion_details);
            SuggestionWeatherLayout.addView(itemView);
        }

    }

    public void bind() {
        try {
            for (int i = 0; i < mWeatherBean.result.getIndex().size(); i++) {
                tvType[i].setText(mWeatherBean.result.getIndex().get(i).getIname());
                tvBrife[i].setText(mWeatherBean.result.getIndex().get(i).getIvalue());
                tvDetails[i].setText(mWeatherBean.result.getIndex().get(i).getDetail());
                int position = 100 + i;
                String Index = position + "";
                ImageLoader.load(itemView.getContext(),
                        SharedPreferenceUtil.getInstance().getInt(Index,
                                R.mipmap.unknow_img),
                        mImageView[i]);
            }
        } catch (Exception e) {
        }
    }

    private void init() {
        tvType = new TextView[mWeatherBean.getResult().getIndex().size()];
        tvBrife = new TextView[mWeatherBean.getResult().getIndex().size()];
        tvDetails = new TextView[mWeatherBean.getResult().getIndex().size()];
        mImageView = new ImageView[mWeatherBean.getResult().getIndex().size()];
    }

}
