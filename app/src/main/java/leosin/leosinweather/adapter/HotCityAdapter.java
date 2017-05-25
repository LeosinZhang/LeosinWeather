package leosin.leosinweather.adapter;

import android.app.IntentService;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import leosin.leosinweather.R;
import leosin.leosinweather.view.activity.SearchCityActivity;

/**
 * Author: LeosinZhang
 * Time: 2017/5/24 21:42
 * function:
 */

public class HotCityAdapter extends RecyclerView.Adapter<HotCityAdapter.ViewHolder> {
    List<String> cityList;
    SearchCityActivity mSearchCityActivity;

    public HotCityAdapter(List<String> list, SearchCityActivity searchCityActivity) {
        this.cityList = list;
        mSearchCityActivity = searchCityActivity;
    }


    @Override
    public HotCityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(mSearchCityActivity).inflate(R.layout.layout_hot_city_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_hotCity.setText(cityList.get(position));
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_layout_hotCity)
        TextView tv_hotCity;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            //set item view listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View itemView) {
                    //Logger.d("点击到Adapter的第" + getAdapterPosition() + "个item");
                    int position = getAdapterPosition();
                    mOnItemClickListener.onItemClick(itemView, position);
                }
            });
        }

    }


    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public interface OnItemClickListener
    {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener)
    {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
