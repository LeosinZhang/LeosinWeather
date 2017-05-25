package leosin.leosinweather.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import leosin.leosinweather.R;
import leosin.leosinweather.SQLite.cityDbManager;
import leosin.leosinweather.adapter.HotCityAdapter;
import leosin.leosinweather.adapter.SearchCityResultAdapter;
import leosin.leosinweather.utils.RecycleViewDivider;
import leosin.leosinweather.utils.RxUtil.RetrofitMethods;

/**
 * Author: LeosinZhang
 * Time: 2017/5/24 17:13
 * Describe: for add ViewPager. search & select city
 */
public class SearchCityActivity extends BaseActivity {
    private MainActivity mMainActivity;
    private HotCityAdapter hotCityAdapter;
    private SearchCityResultAdapter mSearchCityResultAdapter;
    private SearchCityActivity searchCityActivity = this;
    private RetrofitMethods mRetrofitMethods;
    private cityDbManager mCityDbManager;
    private Context context;
    private List<String> searchCityList;
    @BindView(R.id.recyclerView_activity_addViewPager_hotCity)
    RecyclerView hotcityRecyclerView;
    @BindView(R.id.recyclerView_activity_addViewPager_searchCity_List)
    RecyclerView searchCityRecyclerView;
    @BindView(R.id.tv_activity_addViewPager_NoResult)
    TextView noResultCity;
    @BindView(R.id.edit_activity_addViewPager_search)
    EditText editSearchCity;

    ArrayList<String> list = new ArrayList<String>() {
        {
/*            add("北京"); add("成都"); add("重庆"); add("广州");
            add("杭州"); add("南京"); add("上海"); add("深圳");
            add("天津"); add("武汉"); add("西安"); add("郑州");
            add("昆明"); add("沈阳"); add("哈尔滨"); add("长沙");
            add("石家庄"); add("苏州"); add("无锡"); add("济南");
            add("大连"); add("厦门"); add("南昌"); add("长春");*/
            add("北京");
            add("成都");
            add("重庆");
            add("广州");
            add("杭州");
            add("南京");
            add("上海");
            add("深圳");
            add("天津");
            add("武汉");
            add("西安");
            add("郑州");
            add("昆明");
            add("沈阳");
            add("哈尔滨");
            add("长沙");
            add("石家庄");
            add("苏州");
            add("无锡");
            add("济南");
            add("大连");
            add("厦门");
            add("南昌");
            add("长春");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_viewpager);
        ButterKnife.bind(this);
        context = this;
        mMainActivity = MainActivity.getInstance();
        mCityDbManager = new cityDbManager(context);
        if (!mCityDbManager.isExistData()) {
            mRetrofitMethods = RetrofitMethods.getInstance();
            mRetrofitMethods.getCityInfo(mCityDbManager);
        }
        clearText();
        initHotCityRecyclerView();
        initSearchView();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.img_activity_addViewPager_clear)
    public void clearText() {
        editSearchCity.setText("");
    }

    @OnClick(R.id.img_activity_addViewPager_back)
    public void finishActivity() {
        finish();
    }


    /**
     * 初始化RecyclerView
     */
    private void initHotCityRecyclerView() {
        hotCityAdapter = new HotCityAdapter(list, searchCityActivity);
        hotcityRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        hotcityRecyclerView.setAdapter(hotCityAdapter);
        hotCityAdapter.setOnItemClickListener(new HotCityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Logger.d("点击位置结果 ->" + position);
                mMainActivity.AddViewPager(list.get(position));
                finish();
            }

        });
    }

    /**
     * 初始化RecyclerView
     */
    private void initSearchCityRecysfclerView(List<String> searchList) {
        mSearchCityResultAdapter = new SearchCityResultAdapter(searchList, searchCityActivity);
        searchCityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchCityRecyclerView.setAdapter(mSearchCityResultAdapter);
        //添加分割线
        searchCityRecyclerView.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.darkGray)));
        mSearchCityResultAdapter.setOnItemClickListener(new SearchCityResultAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Logger.d("点击位置结果 ->" + position);
                mMainActivity.AddViewPager(searchList.get(position));
                finish();
            }

        });
    }

    private void initSearchView() {
        editSearchCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String city = s.toString();

                if (city.equals("")) { //无字符串  显示默认热门城市
                    hotcityRecyclerView.setVisibility(View.VISIBLE);
                    noResultCity.setVisibility(View.GONE);
                    searchCityRecyclerView.setVisibility(View.GONE);
                } else { //显示搜寻结果
                    searchCityList = mCityDbManager.queryCity(city);
                    if (searchCityList.size() == 0) { //无搜索城市
                        noResultCity.setVisibility(View.VISIBLE);
                        hotcityRecyclerView.setVisibility(View.GONE);
                        searchCityRecyclerView.setVisibility(View.GONE);
                    } else {
                        searchCityRecyclerView.setVisibility(View.VISIBLE);
                        noResultCity.setVisibility(View.GONE);
                        hotcityRecyclerView.setVisibility(View.GONE);
                        initSearchCityRecysfclerView(searchCityList);
                    }

                }
            }
        });
    }

}
