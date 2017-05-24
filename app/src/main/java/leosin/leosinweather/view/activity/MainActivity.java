package leosin.leosinweather.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import leosin.leosinweather.R;
import leosin.leosinweather.utils.Const;
import leosin.leosinweather.utils.RxUtil.RetrofitMethods;
import leosin.leosinweather.utils.SharedPreferenceUtil;
import leosin.leosinweather.utils.SnackBarUtil;
import leosin.leosinweather.utils.ToastUtil;
import leosin.leosinweather.utils.systemUI;
import leosin.leosinweather.utils.titleBar.StatusBarUtil;
import leosin.leosinweather.view.fragment.WeatherFragment;

/**
 * Created by Administrator on 2016/12/5.
 */
public class MainActivity extends BaseFragmentActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static MainActivity mMainActivity = null;
    private WeatherFragment currentFragment;
    public static Object sCurrentThread = new Object();
    private RetrofitMethods mRetrofitMethods;
    private SharedPreferenceUtil mSharedPreferenceUtil;
    private MyViewPagerAdapter mMyViewPagerAdapter;

    private Context mContext = this;
    private BroadcastReceiver mBroadcastReceiver;
    private Snackbar mSnackbar;

    private int mAlpha = StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA;
    private int currentIndex = 0;// 当前页卡编号
    public static String localCity;

    List<WeatherFragment> fragmentViews = new ArrayList<WeatherFragment>();// ViewPager页面列表
    ArrayList<String> cityList = new ArrayList<String>(); //City List

    @BindView(R.id.vPager)
    ViewPager viewPager;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.swipe_refresh_widget)
    public SwipeRefreshLayout mSwipeRefreshWidget;
    @BindView(R.id.layout)
    ViewGroup layout;


    // http://android-studio.cn.uptodown.com/windows
    // http://stay4it.com/course/3/lesson/list


    public static MainActivity getInstance() {
        if (mMainActivity == null) {
            mMainActivity = new MainActivity();
        }
        return mMainActivity;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("MainActivity onCreate");
        setContentView(R.layout.viewpager_weather);
        ButterKnife.bind(this);
        mMainActivity = this;
        mSharedPreferenceUtil = SharedPreferenceUtil.getInstance();
        mNavigationView.setNavigationItemSelectedListener(this);
        InitData();
        InitIcon();
        initSwipeRefreshWidget();
        InitViewPager();
    }


    private void InitData() {
        StatusBarUtil.setTranslucent(MainActivity.this);
        StatusBarUtil.setTranslucentForDrawerLayout(MainActivity.this, mDrawerLayout, mAlpha);


        mSharedPreferenceUtil = SharedPreferenceUtil.getInstance();
        //mSharedPreferenceUtil.clear();
        cityList = mSharedPreferenceUtil.loadArray();
        if (cityList.size() == 0) {
            mRetrofitMethods = RetrofitMethods.getInstance();
            Logger.d("UI线程运行");
            mRetrofitMethods.getLocation();

            synchronized (sCurrentThread) {
                try {
                    Logger.d("UI线程阻塞");
                    sCurrentThread.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Logger.d("UI线程继续");
            cityList.add(localCity);
            mSharedPreferenceUtil.saveArray(cityList);
        }
    }

    private void InitViewPager() {
        for (String city : cityList) {
            WeatherFragment weatherFragment = new WeatherFragment();
            weatherFragment.newInstance(city);
            fragmentViews.add(weatherFragment);
        }


        mMyViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), fragmentViews);
        viewPager.setAdapter(mMyViewPagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }


    private void initSwipeRefreshWidget() {
        // 设置进度条的颜色主题，最多能设置四种，加载颜色是循环播放的，只要没有完成刷新就会一直循环.蓝色、绿色、橙色、红色
        mSwipeRefreshWidget.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        mSwipeRefreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                Logger.d("监听到下拉刷新");

                //数据重新加载完成后，提示数据发生改变，并且设置现在不在刷新
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        currentFragment.fetchData();
                        mSwipeRefreshWidget.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }

    public void AddViewPager(String city) {
        WeatherFragment weatherFragment = new WeatherFragment();
        weatherFragment.newInstance(city);
        fragmentViews.add(weatherFragment);

        mMyViewPagerAdapter.notifyDataSetChanged();
        //viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), fragmentViews));
        viewPager.setCurrentItem(fragmentViews.size()); //添加之后显示当前添加的ViewPager
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
        cityList.add(city);
        Logger.d("添加页:" + currentIndex + "\n" + "余下个数" + cityList.size());
        mSharedPreferenceUtil.saveArray(cityList);
    }


    private void DeletViewPager() {
        if (cityList.size() > 1) {
            cityList.remove(currentIndex);
            Logger.d("删除页" + currentIndex + "\n" + "余下个数" + cityList.size());

            fragmentViews.remove(currentIndex);
            mMyViewPagerAdapter.notifyDataSetChanged();
            //viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), fragmentViews));
            if (currentIndex - 1 < 0)
                currentIndex += 1;
            viewPager.setCurrentItem(currentIndex - 1);
            viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
            mSharedPreferenceUtil.saveArray(cityList);
        }
    }


    public class MyViewPagerAdapter extends FragmentPagerAdapter {
        private List<WeatherFragment> fragmentList;

        public MyViewPagerAdapter(FragmentManager fm, List<WeatherFragment> List) {
            super(fm);
            this.fragmentList = List;
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public Fragment getItem(int position) {
            currentFragment = fragmentList.get(position);
            return fragmentList.get(position);
        }
    }


    /**
     * Author: LeosinZhang
     * Time: 2016/12/8:15:12
     * Describle:
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        public void onPageScrollStateChanged(int arg0) {
            if (mSwipeRefreshWidget != null) {
                mSwipeRefreshWidget.setEnabled(arg0 == ViewPager.SCROLL_STATE_IDLE);
            }
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        //在这里做更新页面的处理
        public void onPageSelected(int arg0) {
            currentIndex = viewPager.getCurrentItem();
            String str = "您选择了" + viewPager.getCurrentItem() + "页卡";
            ToastUtil toastUtil = new ToastUtil();
            toastUtil.showToast(MainActivity.this, str).setToastBackground(Color.WHITE, R.drawable.toast).show();
        }
    }


    public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            switch (intent.getAction()) {
                case Const.BROADCAST_ACTION_OPEN_NAVI:
                    openNavi();
                    break;
                case Const.BROADCAST_ACTION_ADD_VIEWPAGER:
                    AddViewPager("苍溪");
                    break;
                case Const.BROADCAST_ACTION_SWIPEREFRESH_ENABLE:
                    mSwipeRefreshWidget.setEnabled(true);
                    break;
                case Const.BROADCAST_ACTION_SWIPEREFRESH_DISABLE:
                    mSwipeRefreshWidget.setEnabled(false);
                    break;
                case Const.BROADCAST_ACTION_REQUEST_NETWORK_DOWN:
                    mSwipeRefreshWidget.setEnabled(false);
                    break;
                default:
                    break;
            }
        }
    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            closeNavi();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        systemUI.hideSystemUI(this);
        mBroadcastReceiver = new MyBroadcastReceiver();
        //动态注册广播事件
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Const.BROADCAST_ACTION_OPEN_NAVI);
        intentFilter.addAction(Const.BROADCAST_ACTION_ADD_VIEWPAGER);
        intentFilter.addAction(Const.BROADCAST_ACTION_SHOW_WEATHER);
        intentFilter.addAction(Const.BROADCAST_ACTION_SWIPEREFRESH_ENABLE);
        intentFilter.addAction(Const.BROADCAST_ACTION_SWIPEREFRESH_DISABLE);
        registerReceiver(mBroadcastReceiver, intentFilter);

        View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener
                (new View.OnSystemUiVisibilityChangeListener() {
                    @Override
                    public void onSystemUiVisibilityChange(int visibility) {
                        if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                            // TODO: The system bars are visible. Make any desired
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    systemUI.hideSystemUI(mContext);
                                }
                            }, 2000);

                        }
                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void openNavi() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    public void closeNavi() {
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    /**
     * Called when an item in the navigation menu is selected.
     *
     * @param item The selected item
     * @return true to display the item as the selected item
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            DeletViewPager();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    private void checkNetwork() {
        SnackBarUtil.setSnackbarColor(mSnackbar, R.color.white, R.color.colorAccent);
        mSnackbar = Snackbar.make(layout, "网络未连接，请检查网络设置", Snackbar.LENGTH_LONG).setAction("点击前往", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ACTION_AIRPLANE_MODE_SETTINGS  无线WIFI
                //ACTION_SETTINGS 系统设置
                Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS);//打开设置无线和网络
                startActivity(intent);
            }
        });
        mSnackbar.show();
    }


    private void InitIcon() {
        mSharedPreferenceUtil.putInt("0", R.mipmap.icon_0);
        mSharedPreferenceUtil.putInt("1", R.mipmap.icon_1);
        mSharedPreferenceUtil.putInt("2", R.mipmap.icon_2);
        mSharedPreferenceUtil.putInt("3", R.mipmap.icon_3);
        mSharedPreferenceUtil.putInt("4", R.mipmap.icon_3);
        mSharedPreferenceUtil.putInt("5", R.mipmap.icon_5);
        mSharedPreferenceUtil.putInt("6", R.mipmap.icon_6);
        mSharedPreferenceUtil.putInt("7", R.mipmap.icon_7);
        mSharedPreferenceUtil.putInt("8", R.mipmap.icon_8);
        mSharedPreferenceUtil.putInt("9", R.mipmap.icon_9);
        mSharedPreferenceUtil.putInt("10", R.mipmap.icon_10);
        mSharedPreferenceUtil.putInt("11", R.mipmap.icon_12);
        mSharedPreferenceUtil.putInt("12", R.mipmap.icon_12);
        mSharedPreferenceUtil.putInt("13", R.mipmap.icon_15);
        mSharedPreferenceUtil.putInt("14", R.mipmap.icon_14);
        mSharedPreferenceUtil.putInt("15", R.mipmap.icon_15);
        mSharedPreferenceUtil.putInt("16", R.mipmap.icon_16);
        mSharedPreferenceUtil.putInt("17", R.mipmap.icon_17);
        mSharedPreferenceUtil.putInt("18", R.mipmap.icon_18);
        mSharedPreferenceUtil.putInt("19", R.mipmap.icon_6);
        mSharedPreferenceUtil.putInt("20", R.mipmap.icon_30);
        mSharedPreferenceUtil.putInt("21", R.mipmap.icon_7);
        mSharedPreferenceUtil.putInt("22", R.mipmap.icon_8);
        mSharedPreferenceUtil.putInt("23", R.mipmap.icon_9);
        mSharedPreferenceUtil.putInt("24", R.mipmap.icon_10);
        mSharedPreferenceUtil.putInt("25", R.mipmap.icon_12);
        mSharedPreferenceUtil.putInt("26", R.mipmap.icon_14);
        mSharedPreferenceUtil.putInt("27", R.mipmap.icon_15);
        mSharedPreferenceUtil.putInt("28", R.mipmap.icon_16);
        mSharedPreferenceUtil.putInt("29", R.mipmap.icon_29);
        mSharedPreferenceUtil.putInt("30", R.mipmap.icon_30);
        mSharedPreferenceUtil.putInt("31", R.mipmap.icon_30);
        mSharedPreferenceUtil.putInt("32", R.mipmap.icon_32);
        mSharedPreferenceUtil.putInt("49", R.mipmap.icon_32);
        mSharedPreferenceUtil.putInt("53", R.mipmap.icon_53);
        mSharedPreferenceUtil.putInt("54", R.mipmap.icon_53);
        mSharedPreferenceUtil.putInt("55", R.mipmap.icon_53);
        mSharedPreferenceUtil.putInt("56", R.mipmap.icon_53);
        mSharedPreferenceUtil.putInt("57", R.mipmap.icon_32);
        mSharedPreferenceUtil.putInt("58", R.mipmap.icon_32);
        mSharedPreferenceUtil.putInt("99", R.mipmap.icon_99);
        mSharedPreferenceUtil.putInt("301", R.mipmap.icon_3);
        mSharedPreferenceUtil.putInt("302", R.mipmap.icon_15);


        mSharedPreferenceUtil.putInt("100", R.mipmap.air_conditioner);
        mSharedPreferenceUtil.putInt("101", R.mipmap.sport);
        mSharedPreferenceUtil.putInt("102", R.mipmap.rays);
        mSharedPreferenceUtil.putInt("103", R.mipmap.cold);
        mSharedPreferenceUtil.putInt("104", R.mipmap.carclean);
        mSharedPreferenceUtil.putInt("105", R.mipmap.fouls);
        mSharedPreferenceUtil.putInt("106", R.mipmap.dress);
    }

}
