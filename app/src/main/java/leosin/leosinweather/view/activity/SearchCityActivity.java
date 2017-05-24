package leosin.leosinweather.view.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import leosin.leosinweather.R;

/**
 * Author: LeosinZhang
 * Time: 2017/5/24 17:13
 * Describle: for add ViewPager. serach & select city
 */
public class SearchCityActivity extends BaseActivity{

    @BindView(R.id.recyclerView_activity_addViewPager_hotCity)
    RecyclerView mRecyclerView;
    @BindView(R.id.edit_activity_addViewPager_search)
    EditText editSearchCity;
    @BindView(R.id.img_activity_addViewPager_clear)
    ImageView imgClearText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_viewpager);
        ButterKnife.bind(this);
        clearText();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.img_activity_addViewPager_clear)
    public void clearText(){
        editSearchCity.setText("");
    }

}
