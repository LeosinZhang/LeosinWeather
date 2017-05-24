package leosin.leosinweather.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import leosin.leosinweather.R;
import leosin.leosinweather.adapter.HotCityAdapter;

/**
 * Author: LeosinZhang
 * Time: 2017/5/24 17:13
 * Describe: for add ViewPager. search & select city
 */
public class SearchCityActivity extends BaseActivity {
    private HotCityAdapter hotCityAdapter;
    private SearchCityActivity searchCityActivity = this;
    private Context context;
    @BindView(R.id.recyclerView_activity_addViewPager_hotCity)
    RecyclerView mRecyclerView;
    @BindView(R.id.edit_activity_addViewPager_search)
    EditText editSearchCity;

    ArrayList<String> list = new ArrayList<String>() {
        {
            add("北京"); add("成都"); add("重庆"); add("广州");
            add("杭州"); add("南京"); add("上海"); add("深圳");
            add("天津"); add("武汉"); add("西安"); add("郑州");
            add("昆明"); add("沈阳"); add("哈尔滨"); add("长沙");
            add("石家庄"); add("苏州"); add("无锡"); add("济南");
            add("大连"); add("厦门"); add("南昌"); add("长春");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_viewpager);
        ButterKnife.bind(this);
        context = this;
        clearText();
        initRecyclerView();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.img_activity_addViewPager_clear)
    public void clearText() {
        editSearchCity.setText("");
    }


    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        hotCityAdapter = new HotCityAdapter(list,searchCityActivity);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(hotCityAdapter);
        hotCityAdapter.setOnItemClickListener(new HotCityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Logger.d("点击位置结果 ->" + position );

            }

        });
    }

}
