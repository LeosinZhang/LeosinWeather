package leosin.leosinweather.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import leosin.leosinweather.R;
import leosin.leosinweather.view.activity.SearchCityActivity;

/**
 * Author: LeosinZhang
 * Time: 2017/5/25 16:12
 * Describle:
 */
public class SearchCityResultAdapter extends RecyclerView.Adapter<SearchCityResultAdapter.ViewHolder> {
    List<String> cityList;
    SearchCityActivity mSearchCityActivity;

    public SearchCityResultAdapter(List<String> list, SearchCityActivity searchCityActivity) {
        this.cityList = list;
        mSearchCityActivity = searchCityActivity;
    }


    @Override
    public SearchCityResultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SearchCityResultAdapter.ViewHolder holder = new SearchCityResultAdapter.ViewHolder(LayoutInflater.from(mSearchCityActivity).inflate(R.layout.layout_search_city_result_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(SearchCityResultAdapter.ViewHolder holder, int position) {
        holder.tv_cityResult.setText(cityList.get(position));
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_layout_search_cityResult)
        TextView tv_cityResult;

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

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private SearchCityResultAdapter.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(SearchCityResultAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
