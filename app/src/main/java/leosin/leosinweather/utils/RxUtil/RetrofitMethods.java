package leosin.leosinweather.utils.RxUtil;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;

import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;

import leosin.leosinweather.App;
import leosin.leosinweather.R;
import leosin.leosinweather.SQLite.cityDbManager;
import leosin.leosinweather.bean.CityBean;
import leosin.leosinweather.bean.LocationBean;
import leosin.leosinweather.bean.WeatherBean;
import leosin.leosinweather.retrofit2.converter.gson.CustomConverterFactory;
import leosin.leosinweather.utils.Const;
import leosin.leosinweather.utils.ToastUtil;
import leosin.leosinweather.view.activity.MainActivity;
import leosin.leosinweather.view.fragment.WeatherFragment;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static leosin.leosinweather.view.activity.MainActivity.sCurrentThread;

/**
 * Author: LeosinZhang
 * Time: 2016/12/20:12:05
 * Describle:封装的Retrofit方法
 */
public class RetrofitMethods {
    private Retrofit weatherRetrofit;
    private Retrofit locationRetrofit;
    private Retrofit geoCoderRetrofit;
    private WeatherInterface mWeatherInterface;
    private LocationInterface mLocationInterface;
    private CityInterface mCityInterface;

    //构造方法私有
    public RetrofitMethods() {
    }


    /**
     * 请求天气
     */
    public void startWeatherService() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(Const.DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        weatherRetrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                //.addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(CustomConverterFactory.create()) //用自定义的类来解析Gson，以免result为空的异常
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Const.JISU_WEATHER_URL)
                .build();
        mWeatherInterface = weatherRetrofit.create(WeatherInterface.class);
    }

    /**
     * 请求地理位置
     */
    public void startLocationService() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(Const.DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        locationRetrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                //.addConverterFactory(CustomConverterFactory.create()) //用自定义的类来解析Gson，以免result为空的异常
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Const.BAIDU_API_URL)
                .build();
        mLocationInterface = locationRetrofit.create(LocationInterface.class);
    }

    /**
     * 请求地理位置
     */
    public void startCityService() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(Const.DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        locationRetrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                //.addConverterFactory(CustomConverterFactory.create()) //用自定义的类来解析Gson，以免result为空的异常
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Const.JISU_WEATHER_URL)
                .build();
        mCityInterface = locationRetrofit.create(CityInterface.class);
    }


    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final RetrofitMethods INSTANCE = new RetrofitMethods();
    }

    //获取单例
    public static RetrofitMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 用于获取所有天气信息
     *
     * @param city 获取城市
     */
    public void getWeatherInfo(String city) {
        startWeatherService();
        new Thread() {
            @Override
            public void run() {
                super.run();
                mWeatherInterface.getWeatherInfo(Const.JISU_WEATHER_KEY, city)
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<WeatherBean>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                                ToastUtil toastUtil = new ToastUtil();
                                toastUtil.showToast(App.context, e.getMessage()).setToastBackground(Color.WHITE, R.drawable.toast).show();
                                // 判断是否为无网络链接 或其他错误
                                if (e.getMessage() == "") {

                                }
                            }

                            @Override
                            public void onNext(WeatherBean bean) {
                                WeatherFragment weatherFragment = WeatherFragment.getInstance();
                                Handler handler = weatherFragment.getHandler();
                                Message message = new Message();
                                message.what = weatherFragment.REQUEST_NETWORK_SUCESS;
                                message.obj = bean;
                                handler.sendMessage(message);
                            }
                        });
            }
        }.start();

    }


    /**
     * 获取当前城市
     */
    public void getLocation() {
        startLocationService();
        mLocationInterface.getLocationInfo(Const.BAIDU_WEB_API_KEY)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<LocationBean>() {
                    @Override
                    public void onCompleted() {
                        Logger.d("RetrofitMethods onComplted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d("RetrofitMethods onError");
                        ToastUtil toastUtil = new ToastUtil();
                        toastUtil.showToast(App.context, e.getMessage()).setToastBackground(Color.WHITE, R.drawable.toast).show();
                        Logger.d("e.getMessage() ： " + e.getMessage());
                        // 判断是否为无网络链接 或其他错误
                        if (e.getMessage() == "") {
                        }
                    }

                    @Override
                    public void onNext(LocationBean bean) {
                        Logger.d("RetrofitMethods onNext");
                        if (bean != null) {
                            if (bean.getStatus() == 0) {
                                String city = bean.getContent().getAddress_detail().getCity();
                                MainActivity.localCity = city;
                                synchronized (sCurrentThread) {
                                    try {
                                        Logger.d("UI线程释放");
                                        sCurrentThread.notifyAll();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                            }
                        }
                    }
                });
    }


    /**
     * 用于获取城市
     */
    public void getCityInfo(cityDbManager mCityDbManager) {
        startCityService();
        new Thread() {
            @Override
            public void run() {
                super.run();
                mCityInterface.getCityInfo(Const.JISU_WEATHER_KEY)
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<CityBean>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                                ToastUtil toastUtil = new ToastUtil();
                                toastUtil.showToast(App.context, e.getMessage()).setToastBackground(Color.WHITE, R.drawable.toast).show();
                                // 判断是否为无网络链接 或其他错误
                                if (e.getMessage() == "") {

                                }
                            }

                            @Override
                            public void onNext(CityBean bean) {
                                if (bean == null)
                                    return;
                                if ("0".equals(bean.getStatus())) {
                                    int size = bean.getResult().size();
                                    List<CityBean.ResultBean> list = bean.getResult();
                                    new Thread() {
                                        @Override
                                        public void run() {
                                            for (int i = 0; i < size; i++) {
                                                int cityID = Integer.parseInt(list.get(i).getCityid());
                                                int parentID = Integer.parseInt(list.get(i).getParentid());
                                                String city = list.get(i).getCity();
                                                mCityDbManager.add(cityID, parentID, city);
                                            }
                                        }
                                    }.start();

                                    mCityDbManager.closeDB();
                                }
                            }
                        });
            }
        }.start();

    }

}
